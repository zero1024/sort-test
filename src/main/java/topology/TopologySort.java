package topology;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Perekhod Oleg
 */
public class TopologySort<T extends TopologySort.Node> {

    //----------------------API-------------------------//

    public interface Node {
        public List<String> getDependencyList();

        public String getName();
    }

    public void sort(T[] array) {
        Map<String, T> map = Stream.of(array).collect(Collectors.toMap(Node::getName, n -> n));
        sort(map, new Array<>(array));
    }


    //----------------------Внутренняя логика----------------------------//

    private static <T extends Node> void sort(Map<String, T> map, Array<T> result) {
        Map<String, Color> colors = new HashMap<>();
        for (String name : map.keySet()) {
            check(name, map, colors, result);
        }
    }

    private static <T extends Node> void check(String name, Map<String, T> map, Map<String, Color> colors, Array<T> result) {

        T t = map.get(name);
        Color color = color(name, colors);

        if (color.equals(Color.GREY)) {
            throw new IllegalStateException("cycle!");
        } else if (color.equals(Color.WHITE)) {
            colors.put(name, Color.GREY);
            for (String child : t.getDependencyList()) {
                check(child, map, colors, result);
            }
            result.add(t);
            colors.put(name, Color.BLACK);
        }

    }


    private static Color color(String name, Map<String, Color> colors) {
        return colors.containsKey(name) ? colors.get(name) : Color.WHITE;
    }

    //-------------------Вспомогательные классы алгоритма---------------------//

    private enum Color {
        BLACK, GREY, WHITE
    }

    private static class Array<T extends Node> {
        private T[] array;
        private int idx = 0;

        private Array(T[] array) {
            this.array = array;
        }

        private void add(T t) {
            array[idx++] = t;
        }

    }


}

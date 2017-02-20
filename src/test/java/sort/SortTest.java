package sort;

import org.junit.Test;
import tree.UnbalancedBiTree;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.Arrays.copyOf;

/**
 * @author Perekhod Oleg
 */
public class SortTest {

    @Test
    public void sort() throws Exception {
        Random random = new Random();
        List<Integer[]> arrays = Stream
                .generate(() -> Stream.generate(random::nextInt).limit(100 + random.nextInt(500)).toArray(Integer[]::new))
                .limit(20)
                .collect(Collectors.toList());

        for (Sort<Integer> sort : sorts()) {
            for (Integer[] array : arrays) {
                assert asList(sort(sort, array)).equals(expectedList(array));
            }
        }

    }

    private static List<Integer> expectedList(Integer[] array) {
        List<Integer> expectedList = asList(array);
        Collections.sort(expectedList);
        return expectedList;
    }

    private static Integer[] sort(Sort<Integer> sort, Integer[] array) {
        Integer[] copyArray = copyOf(array, array.length);
        sort.sort(copyArray);
        return copyArray;
    }


    private List<Sort<Integer>> sorts() {
        return asList(new QuickSort<>(), new BubbleSort<>(), new UnbalancedBiTreeSort<>(), new UnbalancedBiTreeSort<Integer>(() -> new UnbalancedBiTree<>(true)));
    }

}

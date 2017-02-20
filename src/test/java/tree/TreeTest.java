package tree;

import org.junit.Test;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * @author Perekhod Oleg
 */
public class TreeTest {

    @Test
    public void iterate() throws Exception {

        actualTest(() -> new UnbalancedBiTree<>(true));
        actualTest(() -> new UnbalancedBiTree<>(false));


    }

    private static void actualTest(Supplier<UnbalancedBiTree<Integer>> supplier) {
        Random random = new Random();

        for (int i = 0; i < 100; i++) {
            UnbalancedBiTree<Integer> tree = supplier.get();
            //1. создаем коллекцию случайных чисел
            List<Integer> list = Stream.generate(random::nextInt).limit(1000).collect(toList());
            //2. наполняем
            list.forEach(tree::add);
            //3. проверяем
            PriorityQueue<Integer> queue = new PriorityQueue<>(list);
            for (Integer integer : tree) {
                assert integer.equals(queue.poll());
            }
        }
    }
}

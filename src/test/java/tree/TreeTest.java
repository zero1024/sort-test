package tree;

import org.junit.Test;

import java.util.Random;
import java.util.stream.Stream;

/**
 * @author Perekhod Oleg
 */
public class TreeTest {

    @Test
    public void iterate() throws Exception {

        Random random = new Random();

        for (int i = 0; i < 100; i++) {
            BiTree<Integer> tree = new BiTree<>();
            Stream.generate(random::nextInt).limit(1000).forEach(tree::add);
            Integer current = Integer.MIN_VALUE;
            for (Integer integer : tree) {
                assert integer >= current;
                current = integer;
            }
        }


        for (int i = 0; i < 100; i++) {
            BiTree<Integer> tree = new BiTree<>(true);
            Stream.generate(random::nextInt).limit(1000).forEach(tree::add);
            Integer current = Integer.MIN_VALUE;
            for (Integer integer : tree) {
                assert integer >= current;
                current = integer;
            }
        }


    }
}

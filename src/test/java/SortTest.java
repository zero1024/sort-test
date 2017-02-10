import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * @author Perekhod Oleg
 */
public class SortTest {

    @Test
    public void sort() throws Exception {
        List<Integer> intList = asList(111, 0, 1, 2, 3, 4, 5, 6, 7, 10, 111, 101, 1, 2, 5, 111, 101, 102);
        for (Sort<Integer> sort : sorts()) {
            for (int i = 0; i < 100; i++) {
                Collections.shuffle(intList);
                Integer[] array = new Integer[intList.size()];
                sort.sort(intList.toArray(array));
                assert Arrays.equals(array, new Integer[]{0, 1, 1, 2, 2, 3, 4, 5, 5, 6, 7, 10, 101, 101, 102, 111, 111, 111});
            }
        }

    }


    private List<Sort<Integer>> sorts() {
        return asList(new QuickSort<Integer>(), new BubbleSort<>());
    }

}

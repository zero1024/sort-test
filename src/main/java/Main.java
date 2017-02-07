import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * @author Perekhod Oleg
 */
public class Main {

    public static void main(String[] args) {
        List<Integer> ints = asList(111, 0, 1, 2, 3, 4, 5, 6, 7, 10, 111, 101, 1, 2, 5, 111);
        Sort<Integer> sort = new QuickSort<>();
        for (int i = 0; i < 10; i++) {
            Collections.shuffle(ints);
            Integer[] array = new Integer[ints.size()];
            sort.sort(ints.toArray(array));
            System.out.println(new ArrayList<>(asList(array)));
        }

    }


}

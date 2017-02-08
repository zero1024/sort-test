/**
 * @author Perekhod Oleg
 */
public class MergeSort<T extends Comparable> implements Sort<T> {

    @Override
    public void sort(T[] array, int begin, int end) {

        int len = (end - begin) + 1;

        if (len == 1 || len == 0) {
            return;
        }

        if (len == 2) {
            if (array[begin].compareTo(array[end]) == 1) {
                T tmp = array[begin];
                array[begin] = array[end];
                array[end] = tmp;
            }
            return;
        }

        int base = (end - begin) / 2;

        sort(array, begin, base);
        sort(array, base + 1, end);

    }
}

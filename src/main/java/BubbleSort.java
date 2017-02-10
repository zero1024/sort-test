/**
 * @author Perekhod Oleg
 */
public class BubbleSort<T extends Comparable> implements Sort<T> {
    @Override
    public void sort(T[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j].compareTo(array[j + 1]) == 1) {
                    T tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                }
            }
        }
    }
}

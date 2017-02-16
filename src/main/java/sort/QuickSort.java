package sort;

/**
 * @author Perekhod Oleg
 */
public class QuickSort<T extends Comparable> implements Sort<T> {

    @Override
    public void sort(T[] array) {
        sort(array, 0, array.length - 1);
    }

    private static <T extends Comparable> void sort(T[] array, int begin, int end) {
        //1. база рекурсии - 0 или 1
        int len = (end - begin) + 1;
        if (len == 0 || len == 1) {
            return;
        }


        //2. база рекурсии - 2
        if (len == 2) {
            if (array[begin].compareTo(array[end]) == 1) {
                T tmp = array[begin];
                array[begin] = array[end];
                array[end] = tmp;
            }
            return;
        }


        //3. первоначальный индексы и база
        int base = begin + (len / 2);
        int i1 = begin;
        int i2 = end;


        //4. цикл
        while (i1 < i2) {

            while (i1 < base && (array[i1].compareTo(array[base]) == -1 || array[i1].compareTo(array[base]) == 0)) {
                i1++;
            }

            while (i2 > base && (array[i2].compareTo(array[base]) == 1 || array[i2].compareTo(array[base]) == 0)) {
                i2--;
            }

            if (i1 < i2) {
                T tmp = array[i1];
                array[i1] = array[i2];
                array[i2] = tmp;

                if (i1 == base) {
                    base = i2;
                } else if (i2 == base) {
                    base = i1;
                }
            }

        }

        //5. рекурсия
        sort(array, begin, base);
        sort(array, base + 1, end);


    }


}

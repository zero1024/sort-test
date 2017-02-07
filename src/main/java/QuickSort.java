/**
 * @author Perekhod Oleg
 */
class QuickSort {

    static void sort(Comparable[] ints) {
        sort(ints, 0, ints.length - 1);
    }

    private static void sort(Comparable[] array, int begin, int end) {
        int len = (end - begin) + 1;
        if (len == 0 || len == 1) {
            return;
        }

        if (len == 2) {
            if (array[begin].compareTo(array[end]) == 1) {
                Comparable tmp = array[begin];
                array[begin] = array[end];
                array[end] = tmp;
            }
            return;
        }


        int base = begin + (len / 2);
        int i1 = begin;
        int i2 = end;


        while (true) {

            boolean leftIsBase = i1 == base;
            boolean rightIsBase = i2 == base;
            boolean leftIsReady = array[i1].compareTo(array[base]) == 1;
            boolean rightIsReady = array[i2].compareTo(array[base]) == -1;

            if (leftIsBase && rightIsBase) {
                break;
            }

            if ((leftIsReady && rightIsReady) || (leftIsReady && rightIsBase) || (rightIsReady && leftIsBase)) {
                Comparable tmp = array[i1];
                array[i1] = array[i2];
                array[i2] = tmp;

                if (leftIsBase) {
                    base = i2;
                } else if (rightIsBase) {
                    base = i1;
                }
                continue;
            }

            if (!leftIsReady && !leftIsBase) {
                i1++;
            }
            if (!rightIsReady && !rightIsBase) {
                i2--;
            }


        }

        sort(array, begin, base);
        sort(array, base + 1, end);


    }


}

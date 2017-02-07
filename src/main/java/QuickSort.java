/**
 * @author Perekhod Oleg
 */
class QuickSort<T extends Comparable> implements Sort<T> {

    public void sort(T[] array, int begin, int end) {
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
        while (true) {

            boolean leftIsBase = i1 == base;
            boolean rightIsBase = i2 == base;
            boolean leftIsReady = array[i1].compareTo(array[base]) == 1;
            boolean rightIsReady = array[i2].compareTo(array[base]) == -1;

            //оба индексы долшли до базы
            if (leftIsBase && rightIsBase) {
                break;
            }

            //обмен
            if ((leftIsReady && rightIsReady) || (leftIsReady && rightIsBase) || (rightIsReady && leftIsBase)) {
                T tmp = array[i1];
                array[i1] = array[i2];
                array[i2] = tmp;

                if (leftIsBase) {
                    base = i2;
                } else if (rightIsBase) {
                    base = i1;
                }
                continue;
            }

            //смещение индексов
            if (!leftIsReady && !leftIsBase) {
                i1++;
            }
            if (!rightIsReady && !rightIsBase) {
                i2--;
            }


        }

        //5. рекурсия
        sort(array, begin, base);
        sort(array, base + 1, end);


    }


}

/**
 * @author Perekhod Oleg
 */
public interface Sort<T extends Comparable> {

    default void sort(T[] ints){
        sort(ints, 0, ints.length - 1);
    }

     void sort(T[] array, int begin, int end);

}

package sort;

import tree.BiTree;

/**
 * @author Perekhod Oleg
 */
public class UnbalancedBiTreeSort<T extends Comparable> implements Sort<T> {

    @Override
    public void sort(T[] array) {
        BiTree<T> tree = new BiTree<>();
        for (T t : array) {
            tree.add(t);
        }
        tree.toArray(array);
    }
}

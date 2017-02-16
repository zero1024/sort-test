package sort;

import tree.BiTree;

import java.util.function.Supplier;

/**
 * @author Perekhod Oleg
 */
public class UnbalancedBiTreeSort<T extends Comparable> implements Sort<T> {

    private Supplier<BiTree<T>> biTreeSupplier;

    public UnbalancedBiTreeSort() {
        this(BiTree::new);
    }

    public UnbalancedBiTreeSort(Supplier<BiTree<T>> biTreeSupplier) {
        this.biTreeSupplier = biTreeSupplier;
    }

    @Override
    public void sort(T[] array) {
        BiTree<T> tree = biTreeSupplier.get();
        for (T t : array) {
            tree.add(t);
        }
        tree.toArray(array);
    }

}

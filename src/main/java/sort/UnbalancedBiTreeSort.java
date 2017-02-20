package sort;

import tree.UnbalancedBiTree;

import java.util.function.Supplier;

/**
 * @author Perekhod Oleg
 */
public class UnbalancedBiTreeSort<T extends Comparable> implements Sort<T> {

    private Supplier<UnbalancedBiTree<T>> biTreeSupplier;

    public UnbalancedBiTreeSort() {
        this(UnbalancedBiTree::new);
    }

    public UnbalancedBiTreeSort(Supplier<UnbalancedBiTree<T>> biTreeSupplier) {
        this.biTreeSupplier = biTreeSupplier;
    }

    @Override
    public void sort(T[] array) {
        UnbalancedBiTree<T> tree = biTreeSupplier.get();
        for (T t : array) {
            tree.add(t);
        }
        tree.toArray(array);
    }

}

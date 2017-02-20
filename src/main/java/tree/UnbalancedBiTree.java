package tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author Perekhod Oleg
 */
@SuppressWarnings("unchecked")
public class UnbalancedBiTree<T extends Comparable> implements Iterable<T> {

    private Node<T> root;
    private boolean useImperativeIterator;

    public UnbalancedBiTree() {
        this(false);
    }

    public UnbalancedBiTree(boolean useImperativeIterator) {
        this.useImperativeIterator = useImperativeIterator;
    }

    //----------Публичное API----------------//

    public void toArray(T[] array) {
        int i = 0;
        for (T t : this) {
            array[i++] = t;
        }
    }

    @Override
    public Iterator<T> iterator() {
        if (useImperativeIterator) {
            return new ImperativeIterator<>(root);
        } else {
            return new OopIterator<T>(root);
        }
    }

    public UnbalancedBiTree<T> add(T t) {
        if (root == null) {
            root = new Node<>();
            root.key = t;
        } else {
            root.add(t);
        }
        return this;
    }


    //-----------------Внутренние классы-------------------//

    static class Node<T extends Comparable> {
        private Node<T> left;
        private Node<T> right;
        private Node<T> parent;
        private T key;

        private boolean isParentOnLeftSide() {
            return parent.right == this;
        }

        private void visit(Consumer<T> consumer) {
            if (left != null) {
                left.visit(consumer);
            }
            consumer.accept(key);
            if (right != null) {
                right.visit(consumer);
            }
        }

        private void add(T t) {
            if (t.compareTo(key) == 1) {
                if (right == null) {
                    right = new Node<>();
                    right.key = t;
                    right.parent = this;
                } else {
                    right.add(t);
                }
            } else if (t.compareTo(key) == -1 || t.compareTo(key) == 0) {
                if (left == null) {
                    left = new Node<>();
                    left.key = t;
                    left.parent = this;
                } else {
                    left.add(t);
                }
            }
        }


    }


    //итераторы
    private static class OopIterator<T extends Comparable> implements Iterator<T> {

        private Iterator<T> iterator;

        OopIterator(Node<T> root) {
            List<T> list = new ArrayList<>();
            root.visit(list::add);
            this.iterator = list.iterator();
        }


        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        @Override
        public T next() {
            return iterator.next();
        }
    }

    private static class ImperativeIterator<T extends Comparable> implements Iterator<T> {

        private Node<T> current;

        ImperativeIterator(Node<T> root) {
            this.current = left(root);
        }

        private Node<T> left(Node<T> root) {
            Node<T> current = root;
            if (current != null) {
                while (current.left != null) {
                    current = current.left;
                }
            }
            return current;
        }


        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new IllegalStateException();
            }

            Node<T> res = current;

            Node<T> tmp = res;
            if (tmp.right != null) {
                tmp = left(tmp.right);
            } else {
                while (tmp.parent != null && tmp.isParentOnLeftSide()) {
                    tmp = tmp.parent;
                }
                if (tmp.parent == null) {
                    tmp = null;
                } else {
                    tmp = tmp.parent;
                }
            }
            this.current = tmp;

            return res.key;
        }
    }


}

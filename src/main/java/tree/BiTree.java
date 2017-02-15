package tree;

import java.util.Iterator;

/**
 * @author Perekhod Oleg
 */
public class BiTree<T extends Comparable> implements Iterable<T> {

    private Node<T> root;

    @Override
    public Iterator<T> iterator() {
        return new TreeIterator<>(root);
    }

    public BiTree<T> add(T t) {
        if (root == null) {
            root = new Node<T>();
            root.key = t;
        } else {
            root.add(t);
        }
        return this;
    }


    static class Node<T extends Comparable> {
        private Node<T> left;
        private Node<T> right;
        private Node<T> parent;
        private T key;

        private boolean isParentOnLeftSide() {
            return parent.right == this;
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
                    left.add(key);
                }
            }
        }


    }

    private static class TreeIterator<T extends Comparable> implements Iterator<T> {

        private Node<T> current;

        TreeIterator(Node<T> root) {
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

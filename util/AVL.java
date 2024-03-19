package util;
import java.util.Collection;
import java.util.NoSuchElementException;

class AVLNode<T extends Comparable<? super T>> {
    private T data;
    private AVLNode<T> left;
    private AVLNode<T> right;
    private int height;
    private int balanceFactor;

    public AVLNode(T data) {
        this.data = data;
    }


    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public AVLNode<T> getLeft() {
        return left;
    }

    public void setLeft(AVLNode<T> left) {
        this.left = left;
    }

    public AVLNode<T> getRight() {
        return right;
    }

    public void setRight(AVLNode<T> right) {
        this.right = right;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getBalanceFactor() {
        return balanceFactor;
    }

    public void setBalanceFactor(int balanceFactor) {
        this.balanceFactor = balanceFactor;
    }

    @Override
    public String toString() {
        return String.format("AVLNode{%s (height %d, bf %d)}",
                data.toString(), height, balanceFactor);
    }
}

public class AVL<T extends Comparable<? super T>> {

    private AVLNode<T> root;
    private int size;

    public AVL() {
    }

    /**
     * Constructs a new AVL.
     * @param data the data to add to the tree
     * @throws java.lang.IllegalArgumentException if data or any element in data is null
     */
    public AVL(Collection<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("data cannot be null");
        }
        for (T datum : data) {
            add(datum);
        }
    }

    /**
     * Adds the element to the tree.
     *
     * @param data the data to add
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("data cannot be null");
        }
        root = addHelper(root, data);

    }

    /**
     * recursive add
     * 
     * @param node search
     * @param data to add
     * @return updated node
     */
    private AVLNode<T> addHelper(AVLNode<T> node, T data) {

        // insert normally
        if (node == null) {
            // update size in method as we know not duplicate
            size++;
            return new AVLNode<T>(data);
        }
        if (node.getData().compareTo(data) > 0) {
            node.setLeft(addHelper(node.getLeft(), data));
        } else if (node.getData().compareTo(data) < 0) {
            node.setRight(addHelper(node.getRight(), data));
        } else {
            return node;
        }

        node.setHeight(max(getHeightQ(node.getLeft()), getHeightQ(node.getRight())) + 1);
        node.setBalanceFactor(getBalanceQ(node));

        // LL
        if (node.getBalanceFactor() > 1 && data.compareTo(node.getLeft().getData()) < 0) {
            return rightRotate(node);
        }

        // RR
        if (node.getBalanceFactor() < -1 && data.compareTo(node.getRight().getData()) > 0) {
            return leftRotate(node);
        }

        // LR
        if (node.getBalanceFactor() > 1 && data.compareTo(node.getLeft().getData()) > 0) {
            node.setLeft(leftRotate(node.getLeft()));
            return rightRotate(node);
        }

        // RL
        if (node.getBalanceFactor() < -1 && data.compareTo(node.getRight().getData()) < 0) {
            node.setRight(rightRotate(node.getRight()));
            return leftRotate(node);
        }

        /* return the (unchanged) node pointer */
        return node;

    }

    /**
     * Gets the height while resolving nulls
     * 
     * @param node any
     * @return height
     */
    private int getHeightQ(AVLNode<T> node) {
        if (node == null) {
            return -1;
        }
        return node.getHeight();
    }

    /**
     * Gets the balance while resolving nulls
     * 
     * @param node any
     * @return balance factor
     */
    private int getBalanceQ(AVLNode<T> node) {
        if (node == null) {
            return -1;
        }

        return getHeightQ(node.getLeft()) - getHeightQ(node.getRight());
    }

    /**
     * rotate right
     * 
     * @param n node
     * @return resolved rotate
     */
    private AVLNode<T> rightRotate(AVLNode<T> n) {
        AVLNode<T> nLeft = n.getLeft();

        // Perform rotation
        n.setLeft(nLeft.getRight());
        nLeft.setRight(n);

        // Update heights and balance factor
        n.setHeight(max(getHeightQ(n.getLeft()), getHeightQ(n.getRight())) + 1);
        n.setBalanceFactor(getBalanceQ(n));
        nLeft.setHeight(max(getHeightQ(nLeft.getLeft()), getHeightQ(nLeft.getRight())) + 1);
        nLeft.setBalanceFactor(getBalanceQ(nLeft));
        // Return new n
        return nLeft;
    }

    /**
     * rotate left
     * 
     * @param n node
     * @return resolved rotate
     */
    private AVLNode<T> leftRotate(AVLNode<T> n) {
        AVLNode<T> nRight = n.getRight();

        // Perform rotation
        n.setRight(nRight.getLeft());
        nRight.setLeft(n);

        // Update heights and balance factor
        n.setHeight(max(getHeightQ(n.getLeft()), getHeightQ(n.getRight())) + 1);
        n.setBalanceFactor(getBalanceQ(n));
        nRight.setHeight(max(getHeightQ(nRight.getLeft()), getHeightQ(nRight.getRight())) + 1);
        nRight.setBalanceFactor(getBalanceQ(nRight));
        // Return new n
        return nRight;
    }

    /**
     * Removes and returns the element from the tree matching the given parameter.
     *
     * @param data the data to remove
     * @return the data that was removed
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if the data is not found
     */
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("data cannot be null");
        }

        // stores the removed node
        AVLNode<T> ret = new AVLNode<>(null);
        root = removeHelper(root, data, ret);
        size--;
        return ret.getData();
    }

    /**
     * remove helper
     * 
     * @param n     node
     * @param data  to search
     * @param store removed data
     * @return resolved node
     */
    private AVLNode<T> removeHelper(AVLNode<T> n, T data, AVLNode<T> store) {

        if (n == null) {
            throw new NoSuchElementException("Data is not in the tree");
        }

        // traverse
        if (n.getData().compareTo(data) > 0) {
            n.setLeft(removeHelper(n.getLeft(), data, store));
        } else if (n.getData().compareTo(data) < 0) {
            n.setRight(removeHelper(n.getRight(), data, store));
        } else {
            // n is the node we want to delete
            // need to do this to not change reference
            if (store.getData() == null) {
                // sometimes store could be updated twice in degenerate tree
                store.setData(n.getData());
            }

            // one child
            if (n.getLeft() == null) {
                return n.getRight();
            } else if (n.getRight() == null) {
                return n.getLeft();
            }

            // well now we need to find predecessor because two children
            n.setData(predecessor(n.getLeft()).getData());
            // now remove old node
            n.setLeft(removeHelper(n.getLeft(), n.getData(), store));
        }

        n.setHeight(max(getHeightQ(n.getLeft()), getHeightQ(n.getRight())) + 1);
        n.setBalanceFactor(getBalanceQ(n));

        // LR
        if (n.getBalanceFactor() > 1 && n.getLeft().getBalanceFactor() < 0) {
            n.setLeft(leftRotate(n.getLeft()));
            return rightRotate(n);
        } else if (n.getBalanceFactor() > 1) {
            // LL
            return rightRotate(n);
        }

        // RL
        if (n.getBalanceFactor() < -1 && n.getRight().getBalanceFactor() > 0) {
            n.setRight(rightRotate(n.getRight()));
            return leftRotate(n);
        } else if (n.getBalanceFactor() < -1) {
            // RR
            return leftRotate(n);
        }

        return n;
    }

    /**
     * Returns the element from the tree matching the given parameter.
     *
     * @param data the data to search for in the tree
     * @return the data in the tree equal to the parameter
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if the data is not in the tree
     */
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException("data cannot be null");
        }

        return getHelper(root, data).getData();
    }

    /**
     * get recursive
     * 
     * @param node any
     * @param data to check
     * @return node containing data
     */
    private AVLNode<T> getHelper(AVLNode<T> node, T data) {
        if (node == null) {
            // update size in method as we know not duplicate
            throw new NoSuchElementException("Data is not in the tree");
        }
        if (node.getData().compareTo(data) > 0) {
            return getHelper(node.getLeft(), data);
        } else if (node.getData().compareTo(data) < 0) {
            return getHelper(node.getRight(), data);
        }

        return node;

    }

    /**
     * Returns whether or not data matching the given parameter is contained within
     * the tree.
     *
     * @param data the data to search for in the tree.
     * @return true if the parameter is contained within the tree, false otherwise
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public boolean contains(T data) {
        if (data == null) {
            throw new IllegalArgumentException("data cannot be null");
        }

        return containsHelper(root, data);
    }

    /**
     * contains recursive
     * 
     * @param node any
     * @param data to search
     * @return if it contains
     */
    private boolean containsHelper(AVLNode<T> node, T data) {
        if (node == null) {
            // update size in method as we know not duplicate
            return false;
        }
        if (node.getData().compareTo(data) > 0) {
            return containsHelper(node.getLeft(), data);
        } else if (node.getData().compareTo(data) < 0) {
            return containsHelper(node.getRight(), data);
        }

        return true;
    }

    /**
     * Returns the height of the root of the tree.
     *
     * @return the height of the root of the tree, -1 if the tree is empty
     */
    public int height() {
        // -1 if tree is empty
        if (size == 0) {
            return -1;
        }
        return root.getHeight();
    }

    /**
     * Clears the tree.
     *
     * Clears all data and resets the size.
     */
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * The predecessor is the largest node that is smaller than the current data.
     * @param data the data to find the predecessor of
     * @return the predecessor of data. If there is no smaller data than the one
     *         given, return null.
     * @throws java.lang.IllegalArgumentException if the data is null
     * @throws java.util.NoSuchElementException   if the data is not in the tree
     */
    public T predecessor(T data) {
        if (data == null) {
            throw new IllegalArgumentException("data cannot be null");
        }
        // get node while keeping track of parent.
        AVLNode<T> parent = new AVLNode<>(null);
        // not very java-like, but we need recursion + no returning list
        AVLNode<T> node = predecessorGet(root, data, parent);
        AVLNode<T> possible = predecessor(node.getLeft());
        if (possible.getData() == null) {
            return parent.getData();
        }
        return possible.getData();

    }

    /**
     * Gets the node we need to search while storing parent
     * 
     * @param node   current
     * @param data   to search
     * @param parent storing parent - C style because of limitations of assignment
     * @return predecessor
     */
    private AVLNode<T> predecessorGet(AVLNode<T> node, T data, AVLNode<T> parent) {
        if (node == null) {
            // update size in method as we know not duplicate
            throw new NoSuchElementException("Data is not in the tree");
        }

        if (node.getData().compareTo(data) > 0) {
            return predecessorGet(node.getLeft(), data, parent);
        } else if (node.getData().compareTo(data) < 0) {
            parent.setData(node.getData()); // not very java-like
            return predecessorGet(node.getRight(), data, parent);
        }
        return node;

    }

    /**
     * Simple way of searching for bottom predecessor
     * 
     * @param node to search
     * @return predecessor of node assuming
     */
    private AVLNode<T> predecessor(AVLNode<T> node) {
        if (node == null) {
            return new AVLNode<T>(null);
        }
        if (node.getRight() == null) {
            return node;
        }

        return predecessor(node.getRight());
    }

    /**
     * Returns the data in the deepest node. If there is more than one node with the
     * same deepest depth, return the rightmost (i.e. largest) node with the deepest
     * depth.
     * @return the data in the maximum deepest node or null if the tree is empty
     */
    public T maxDeepestNode() {
        // search from Right to left post order?
        // breaks deepest, as it expects not null
        if (size == 0) {
            return null;
        }
        return deepest(root).getData();

    }

    /**
     * gives the deepest node
     * 
     * @param node or root
     * @return deepest node
     */
    private AVLNode<T> deepest(AVLNode<T> node) {
        if (node.getLeft() == null && node.getRight() == null) {
            return node;
        }
        // we can go for balance factor?
        if (node.getBalanceFactor() > 0) {
            // I think node is in left
            return deepest(node.getLeft());
        }
        return deepest(node.getRight());

    }

    /**
     * Max function but I don't know if I can use Math.max
     * 
     * @param a num1
     * @param b num2
     * @return Math.max(a, b)
     */
    private int max(int a, int b) {
        return a > b ? a : b;
    }

    /**
     * Returns the root of the tree.
     *
     * @return the root of the tree
     */
    public AVLNode<T> getRoot() {
        return root;
    }

    /**
     * Returns the size of the tree.
     *
     * @return the size of the tree
     */
    public int size() {
        return size;
    }

}

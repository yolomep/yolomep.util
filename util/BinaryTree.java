package util;

/**
 * Implementatino of binary tree.
 * Simplier version of CS 1332 BST
 */
class BinaryNode<T> {
    T data;
    BinaryNode<T> left;
    BinaryNode<T> right;

    public BinaryNode() {
        left = null;
        right = null;
    }

    public BinaryNode(T data) {
        this();
        this.data = data;
    }
}

public class BinaryTree<T extends Comparable<T>> {
    BinaryNode<T> root;

    BinaryTree() {
        root = null;
    }

    BinaryTree(BinaryNode<T> X) {
        root = X;
    }

    // search a value t in the subtree whose root is X
    public BinaryNode<T> search(BinaryNode<T> X, T t) {
        if (X == null)
            return null;
        else if (X.data.compareTo(t) == 0)
            return X;
        else if (X.data.compareTo(t) > 0)
            return search(X.left, t);
        else
            return search(X.right, t);
    }

    // insert a value t to the existing subtree whose root is X
    public BinaryNode<T> insert(BinaryNode<T> X, T t) {
        if (X == null)
            X = new BinaryNode<T>(t);
        else {
            if (t.compareTo(X.data) < 0)
                X.left = insert(X.left, t);
            else if (t.compareTo(X.data) > 0)
                X.right = insert(X.right, t);
        }
        return X;
    }

    // delete a value t from the subtree whose rooted is X
    public BinaryNode<T> delete(BinaryNode<T> X, T t) {
        if (X == null)
            return null;
        else if (X.data.compareTo(t) > 0)
            X.left = delete(X.left, t);
        else if (X.data.compareTo(t) < 0)
            X.right = delete(X.right, t);
        else {
            if (X.left != null && X.right != null) { // with two children
                BinaryNode<T> minNode = findMin(X.right);
                X.data = minNode.data;
                X.right = delete(X.right, minNode.data);
            } else if (X.left != null) // only left child exists
                X = X.left;
            else if (X.right != null) // only right child exists
                X = X.right;
            else // x has no child
                X = null;
        }
        return X;
    }

    // find the node with the minimum value in the subtree whose root is X
    public BinaryNode<T> findMin(BinaryNode<T> X) {
        if (X.left == null)
            return X;
        else
            return findMin(X.left);
    }

    // find the node with the maximum value in the subtree whose root is X
    public BinaryNode<T> findMax(BinaryNode<T> X) {
        if (X.right == null)
            return X;
        else
            return findMax(X.right);
    }
}
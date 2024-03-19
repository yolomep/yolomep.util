/**
 * 
 */
package util;
import java.util.*;
//import java.io.*;
/**

 * @author Yolomep
 *
 */
class Node <T> {
	T data;
	List<T> children;
	
	public Node() {
		children = new ArrayList<>();
	}
	
	public Node(T data) {
		this();
		this.data = data;
	}
}

public class GenericTree <T> {
	private Node <T> root;
	//private Map<T, Integer> dist;
	GenericTree() {
        root = null;
    }
	
    GenericTree(Node <T> X) {
        root = X;
    }
    
    public Node<T> getRoot(){
    	return root;
    }
    
    public Node < T > insert(Node <T> X, T t) {
        if (X == null)
            X = new Node <T> (t);
        else {
            
        }
        return X;
    }
	
    public boolean isForest() {
    	return true; //all trees are forests
    }
    
    //idk why i write this
    public boolean isTree() {
    	return true; //this is a tree
    }
}

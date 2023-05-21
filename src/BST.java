import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class BST<K extends Comparable<K>, V extends Comparable<V>> implements Iterable<K> {
    private Node root; // Root node of the BST
    private int size; // Number of nodes in the BST

    public BST() {
        root = null;
        size = 0;
    }

    // Node class representing a single node in the tree
    private class Node {
        private K key;
        private V value;
        private Node left, right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = right = null;
        }
    }

    // Insert a key-value pair into the tree
    public void put(K key, V value) {
        if (key == null)
            throw new IllegalArgumentException("Key cannot be null.");

        root = putRecursive(root, key, value);
    }

    // Recursive helper method to insert a node into the tree
    private Node putRecursive(Node node, K key, V value) {
        if (node == null) {
            size++;
            return new Node(key, value);
        }

        int comparison = key.compareTo(node.key);
        if (comparison < 0)
            node.left = putRecursive(node.left, key, value);
        else if (comparison > 0)
            node.right = putRecursive(node.right, key, value);
        else
            node.value = value;

        return node;
    }

    // Retrieve the value associated with a given key from the tree
    public V get(K key) {
        if (key == null)
            throw new IllegalArgumentException("Key cannot be null.");

        return getRecursive(root, key);
    }

    // Recursive helper method to search for a node with the given key in the tree
    private V getRecursive(Node node, K key) {
        if (node == null)
            return null;

        int comparison = key.compareTo(node.key);
        if (comparison < 0)
            return getRecursive(node.left, key);
        else if (comparison > 0)
            return getRecursive(node.right, key);
        else
            return node.value;
    }

    // Delete a node with the given key from the tree
    public void delete(K key) {
        if (key == null)
            throw new IllegalArgumentException("Key cannot be null.");

        root = deleteRecursive(root, key);
    }

    // Recursive helper method to delete a node with the given key from the tree
    private Node deleteRecursive(Node node, K key) {
        if (node == null)
            return null;

        int comparison = key.compareTo(node.key);
        if (comparison < 0)
            node.left = deleteRecursive(node.left, key);
        else if (comparison > 0)
            node.right = deleteRecursive(node.right, key);
        else {
            if (node.left == null)
                return node.right;
            if (node.right == null)
                return node.left;

            Node successor = findSuccessor(node.right);
            node.key = successor.key;
            node.value = successor.value;
            node.right = deleteRecursive(node.right, successor.key);
        }

        return node;
    }

    // Find the minimum key in the subtree rooted at the given node
    private Node findSuccessor(Node node) {
        while (node.left != null)
            node = node.left;
        return node;
    }

    // Custom iterator to perform in-order traversal of the tree
    @Override
    public Iterator<K> iterator() {
        return new BSTIterator();
    }

    // Inner class representing the iterator
    private class BSTIterator implements Iterator<K> {
        private Node current; // Current node during iteration
        private Stack<Node> stack; // Stack to track the nodes during traversal

        public BSTIterator() {
            current = root;
            stack = new Stack<>();

            // Initialize the stack by pushing all left nodes from the root
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public K next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Node node = stack.pop();
            K key = node.key;
            current = node.right;

            // Push all left nodes of the right child of the popped node onto the stack
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            return key;
        }
    }

    // Check if the tree contains a node with the given value
    public boolean containsValue(V value) {
        return containsValueRecursive(root, value);
    }

    // Recursive helper method to check if a node with the given value exists in the tree
    private boolean containsValueRecursive(Node node, V value) {
        if (node == null) {
            return false;
        } else if (value.compareTo(node.value) == 0) {
            return true;
        } else {
            return containsValueRecursive(node.left, value) || containsValueRecursive(node.right, value);
        }
    }

    // Get the number of nodes in the tree
    public int size() {
        return calculateSize(root);
    }

    // Recursive helper method to calculate the number of nodes in the tree
    private int calculateSize(Node node) {
        if (node == null) {
            return 0;
        }
        return 1 + calculateSize(node.left) + calculateSize(node.right);
    }
}

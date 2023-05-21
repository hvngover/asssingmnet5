
https://github.com/hvngover/asssingmnet5/assets/109821023/23f1283e-9894-4e82-8caf-7c8b970975e1


# BinarySearchTree



## Methods

**```put(K key, V value):```**

This method is used to insert a key-value pair into the binary search tree. It takes a key and a value as parameters and inserts them into the appropriate position in the tree based on the key's ordering.

```java 
public void put(K key, V value) {
        if (key == null)
            throw new IllegalArgumentException("Key cannot be null.");

        root = putRecursive(root, key, value);
    }
```

**```get(K key):```**

This method is used to retrieve the value associated with a given key from the binary search tree. It takes a key as a parameter and searches for the key in the tree. If found, it returns the corresponding value; otherwise, it returns null.

```java
public V get(K key) {
        if (key == null)
            throw new IllegalArgumentException("Key cannot be null.");

        return getRecursive(root, key);
    }
```

**```delete(K key):```**

This method is used to delete a node with the given key from the binary search tree. It takes a key as a parameter and removes the corresponding node from the tree while maintaining the binary search tree property.

```java
public void delete(K key) {
        if (key == null)
            throw new IllegalArgumentException("Key cannot be null.");

        root = deleteRecursive(root, key);
    }
```

**```findSuccessor(Node node):```**

This is a helper method used by the delete method. It finds the node with the minimum key in the subtree rooted at the given node. It is used to find the successor of a node during deletion.

```java
private Node findSuccessor(Node node) {
        while (node.left != null)
            node = node.left;
        return node;
    }
```

**```iterator():```**

This method returns an iterator that allows iterating over the keys in the binary search tree in ascending order. It implements the Iterable interface and provides the necessary methods for iteration.

```java
public Iterator<K> iterator() {
        return new BSTIterator();
    }
```

**```BSTIterator:```**

This is an inner class that implements the Iterator interface. It is used by the iterator() method to provide the functionality for iterating over the keys in the binary search tree. It performs an in-order traversal of the tree using a stack to keep track of the nodes.

```java
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
```

**```containsValue(V value):```**
This method checks if the binary search tree contains a node with the given value. It performs a recursive search through the tree to find a node with the specified value.

```java
public boolean containsValue(V value) {
        return containsValueRecursive(root, value);
    }
```

**```size():```**

This method returns the number of nodes in the binary search tree. It calculates the size recursively by counting the nodes in the left and right subtrees of each node.

```java
public int size() {
        return calculateSize(root);
    }
```

**```calculateSize(Node node):```**

This method is a recursive helper method that calculates the size of a subtree in a binary search tree. It counts the number of nodes in the subtree by recursively adding 1 for each node and the sizes of its left and right subtrees. If the subtree is empty (node is null), it returns 0. The method is used by the size() method to calculate the total size of the entire binary search tree.

```java 
private int calculateSize(Node node) {
        if (node == null) {
            return 0;
        }
        return 1 + calculateSize(node.left) + calculateSize(node.right);
    }
```

## Usage

```java 

// Add some key-value pairs to the hash table
bst.put(23, "Michael Jordan");
bst.put(24, "Kobe Bryant");
bst.put(3, "Allen Iverson");

```

### Output

```java
BST size: 3
Iterating over the BST:
Key: 3, Value: Allen Iverson
Key: 23, Value: Michael Jordan
Key: 24, Value: Kobe Bryant
BST contains value 'Michael Jordan': true
Deleted key 24
Iterating over the updated BST:
Key: 3, Value: Allen Iverson
Key: 23, Value: Michael Jordan
```









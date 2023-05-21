public class Main {
    public static void main(String[] args) {
        BST<String, Integer> bst = new BST<>();
        bst.put("apple", 5);
        bst.put("banana", 3);
        bst.put("orange", 7);

        System.out.println("BST size: " + bst.size());

        // Print all key-value pairs using iterator
        System.out.println("Iterating over the BST:");
        for (String key : bst) {
            System.out.println("Key: " + key + ", Value: " + bst.get(key));
        }

        // Check if the tree contains a specific value
        int valueToFind = 3;
        System.out.println("BST contains value '" + valueToFind + "': " + bst.containsValue(valueToFind));

        // Delete a key-value pair
        String keyToDelete = "banana";
        bst.delete(keyToDelete);
        System.out.println("Deleted key " + keyToDelete);

        // Print all key-value pairs again to verify deletion
        System.out.println("Iterating over the updated BST:");
        for (String key : bst) {
            System.out.println("Key: " + key + ", Value: " + bst.get(key));
        }
    }
}

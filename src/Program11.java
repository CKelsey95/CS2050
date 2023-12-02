import java.io.*;

class Node {
    String data; // holds the key
    Node parent; // pointer to the parent
    Node left; // pointer to left child
    Node right; // pointer to right child
    int color; // 1 . Red, 0 . Black
    int count = 0; // counter for each subsequent instance of the same word
}
public class Program11 {
    private Node root;
    private Node TNULL;
    public final int RED = 1;
    public final int BLACK = 0;
    private int totalwordCount = 0;
    private int hashTableSize;
    private Node[] hashTable;
    static String algoCount = "First";

    public Program11(String file) {
        this.hashTableSize = 89231;
        this.hashTable = new Node[hashTableSize];
    }
    private static int customHash(String key, int tableSize) {
        key = key.toLowerCase(); // Convert to lowercase to ensure case-insensitivity
        int hash = 0;

        for (int i = 0; i < key.length(); i++) {
            hash = (hash * 31 + key.charAt(i)) % tableSize;
        }

        return hash;
    }
    private int findNextAvailableSlot(Node[] hashTable, int index) {
        int i = 1;
        while (hashTable[index] != null) {
            index = (index + i) % hashTable.length;
            i++;
        }
        return index;
    }
    private void fixInsert(Node k) {
        Node u;
        while (k.parent != null && k.parent.color == RED) {
            if (k.parent == k.parent.parent.right) {
                u = k.parent.parent.left; // uncle
                if (u != null && u.color == RED) {
                    // case 3.1
                    u.color = BLACK;
                    k.parent.color = BLACK;
                    k.parent.parent.color = RED;
                    k = k.parent.parent;
                } else {
                    if (k == k.parent.left) {
                        // case 3.2.2
                        k = k.parent;
                        rightRotate(k);
                    }
                    // case 3.2.1
                    k.parent.color = BLACK;
                    k.parent.parent.color = RED;
                    leftRotate(k.parent.parent);
                }
            } else {
                u = k.parent.parent.right; // uncle

                if (u != null && u.color == RED) {
                    // mirror case 3.1
                    u.color = BLACK;
                    k.parent.color = BLACK;
                    k.parent.parent.color = RED;
                    k = k.parent.parent;
                } else {
                    if (k == k.parent.right) {
                        // mirror case 3.2.2
                        k = k.parent;
                        leftRotate(k);
                    }
                    // mirror case 3.2.1
                    k.parent.color = BLACK;
                    k.parent.parent.color = RED;
                    rightRotate(k.parent.parent);
                }
            }
            if (k == root) {
                break;
            }
        }
        root.color = BLACK;
    }
    public void insertWithHash(String key, Node[] hashTable) {
        int hash = customHash(key, hashTable.length);
        int index = hash;

        if (hashTable[index] != null && hashTable[index].data.equals(key)) {
            // Word is already in the hash table, increment the counter
            hashTable[index].count++;
            totalwordCount++;
        } else {
            // Word is not in the hash table, insert it
            int nextIndex = findNextAvailableSlot(hashTable, index);

            Node newNode = new Node();
            newNode.data = key;
            newNode.count = 1;
            hashTable[nextIndex] = newNode;

            // Insert the hashed value into the Red-Black tree
            insert(key, key);

            totalwordCount++;
        }
    }
    public void rightRotate(Node x) {
        Node y = x.left;
        x.left = y.right;
        if (y.right != TNULL) {
            y.right.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            this.root = y;
        } else if (x == x.parent.right) {
            x.parent.right = y;
        } else {
            x.parent.left = y;
        }
        y.right = x;
        x.parent = y;
    }
    public void leftRotate(Node x) {
        Node y = x.right;
        x.right = y.left;
        if (y.left != TNULL) {
            y.left.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            this.root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }
        y.left = x;
        x.parent = y;
    }
    public Node searchTree(String k) {
        return searchTreeHelper(this.root, k);
    }
    private Node searchTreeHelper(Node node, String key) {
        // base case
        if (node == TNULL || key.equals(node.data)) {
            return node;
            // search left subtree
        } else if (key.compareTo(node.data) < 0) {
            return searchTreeHelper(node.left, key);
        } else if (key.compareTo(node.data) > 0) {
            // search right subtree
            return searchTreeHelper(node.right, key);
        }
        return node;
    }
    // insert the key to the tree in its appropriate position, iterates wordcount on insertion
    public void insert(String key, String s) {
        Node existingNode = searchTree(key);
        if (existingNode != TNULL) {
            existingNode.count++; // Increment the count if the word already exists
        } else {
            // Ordinary Binary Search Insertion
            Node node = new Node();
            node.parent = null;
            node.data = key;
            node.left = TNULL;
            node.right = TNULL;
            node.color = RED; // new node must be red
            node.count = 1; // Initialize the count for a new word

            Node y = null;
            Node x = this.root;

            while (x != TNULL) {
                y = x;
                if (node.data.compareTo(x.data) < 0) {
                    x = x.left;
                } else {
                    x = x.right;
                }
            }
            // y is parent of x
            node.parent = y;
            if (y == null) {
                root = node;
            } else if (node.data.compareTo(y.data) < 0) {
                y.left = node;
            } else {
                y.right = node;
            }

            // if new node is a root node, simply return
            if (node.parent == null){
                node.color = BLACK;
                return;
            }

            // if the grandparent is null, simply return
            if (node.parent.parent == null) {
                return;
            }

            // Fix the tree
            fixInsert(node);
            totalwordCount++;
        }
    }
    public Node getRoot(){
        return this.root;
    }

    private static double calculateLoadFactor(Node[] hashTable) {
        int uniqueWordCount = 0;

        for (Node node : hashTable) {
            if (node != null && node.count > 0) {
                uniqueWordCount++;
            }
        }
        return (double) uniqueWordCount / hashTable.length;
    }
    static void readFromFile(Program11 bst, Node[] hashTable) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("dracula.txt"));
            String input;
            while ((input = reader.readLine()) != null) {
                String[] splitInput = input.split("[^a-zA-Z]+");  // removes bad characters
                for (String word : splitInput) {
                    String cleanString = word.toLowerCase();
                    bst.insertWithHash(cleanString, hashTable);
                    bst.totalwordCount++;
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error While Reading File");
        }
    }
    private static void writeResultsToFile(int tableSize, double loadFactor, Node[] hashTable) {
        try (BufferedWriter resultWriter = new BufferedWriter(new FileWriter("results.txt", true))) {
            resultWriter.write("hash value * 31, + ASCII value of character,then % table size \n");
            resultWriter.write("Hash Algorithm: " + algoCount + "\n");
            resultWriter.write("Hash Table Size: " + tableSize + "\n");
            resultWriter.write("Load Factor: " + loadFactor + "\n");
            resultWriter.write("\n"); // newline between results
        } catch (IOException e) {
            System.out.println("Error while writing results.txt");
        }
    }
    public static void main(String[] args) throws IOException {
        Program11 bst = new Program11("dracula.txt");
        readFromFile(bst, bst.hashTable);
        writeResultsToFile(bst.hashTableSize, calculateLoadFactor(bst.hashTable), bst.hashTable);
        // Repeat with a different hash table size
        int newHashTableSize = 449171; // Another prime number
        bst.hashTableSize = newHashTableSize;
        bst.hashTable = new Node[newHashTableSize];

        readFromFile(bst, bst.hashTable);
        algoCount = "Second";
        writeResultsToFile(newHashTableSize, calculateLoadFactor(bst.hashTable), bst.hashTable);
    }
}
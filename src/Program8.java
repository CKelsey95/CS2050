/* Colton Kelsey
    Program 8
 */
import java.io.*;

public class Program8 {
    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();
        readFromFile(tree);
        tree.inOrder();
        writeToFile(tree);
    }
    // main binary tree class
    static class BinarySearchTree {
        static class Node {
            String data;
            Node left;
            Node right;

            // node constructor
            public Node(String data) {
                this.data = data;
                left = null;
                right = null;
            }
        }
        Node root;
        int nodeCount; // keeps track of the number of nodes

        // constructor for BST
        public BinarySearchTree() {
            root = null;
            nodeCount = 0;
        }
        // if tree is empty, create root, if not, use other method.
        public void insert(String data) {
            if (root == null) {
                root = new Node(data);
                nodeCount++;
            } else {
                insertRec(root, data);
            }
        }
        // main insert method to create new nodes
        private void insertRec(Node root, String data) {
            int compareResult = data.compareTo(root.data);
            if (compareResult < 0) {
                if (root.left == null) {
                    root.left = new Node(data);
                    nodeCount++;
                } else {
                    insertRec(root.left, data);
                }
            } else if (compareResult > 0) {
                if (root.right == null) {
                    root.right = new Node(data);
                    nodeCount++;
                } else {
                    insertRec(root.right, data);
                }
            }
        }
        // initiates in order traversal
        public void inOrder() {
            inOrderRec(root);
        }
        // in order traversal of BST, smallest to largest value
        private void inOrderRec(Node root) {
            if (root != null) {
                inOrderRec(root.left);
                inOrderRec(root.right);
            }
        }
        // instantiates height method
        public int height() {
            return height(root);
        }
        // calculates height for both subtrees, adds them, and adds 1 to account for root
        private int height(Node node) {
            if (node == null) {
                return 0;
            } else {
                int leftHeight = height(node.left);
                int rightHeight = height(node.right);
                return Math.max(leftHeight, rightHeight +1);
            }
        }
        // uses height to calculate the max possible nodes
        public long maxNodesPossible() {
            int treeHeight = height();
            return (long) Math.pow(2, treeHeight) -1;
        }
        public long nodeDifference(BinarySearchTree tree) {
            long maxNodes = maxNodesPossible();
            return maxNodes - tree.nodeCount;
        }
    }
    //  reads from the file, removes bad characters, converts the remainder to lower case, triggers insert method
    static void readFromFile(BinarySearchTree tree) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("dracula.txt"));
            String input;
            while ((input = reader.readLine()) != null) {
                String[] splitInput = input.split("[^a-zA-Z]+");  // removes bad characters
                for (String word : splitInput) {
                    String cleanString = word.toLowerCase();
                    tree.insert(cleanString);
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error While Reading File");
        }
    }
    // this method writes total nodes, height, max nodes possible, and difference between max nodes and created nodes
    static void writeToFile(BinarySearchTree tree) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("analysis.txt"));
            writer.write("total nodes created: " + tree.nodeCount);
            writer.write("\ntree height: " + tree.height());
            writer.write("\nmax nodes possible: " + tree.maxNodesPossible());
            writer.write("\nmax nodes - created nodes: " + tree.nodeDifference(tree));

            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
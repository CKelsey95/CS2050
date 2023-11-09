/* Colton Kelsey
   Program 9
 */
import java.io.*;
class Node {
    String data; // holds the key
    Node parent; // pointer to the parent
    Node left; // pointer to left child
    Node right; // pointer to right child
    int color; // 1 . Red, 0 . Black
    int count = 0; // counter for each subsequent instance of the same word
}
public class Program9 {
    private Node root;
    private Node TNULL;
    public final int RED = 1;
    public final int BLACK = 0;
    private int totalwordCount = 0;

    BufferedWriter writer = new BufferedWriter(new FileWriter("rbtree.txt"));

    // returns node count
    public int wordCount(String key) {
        Node node = searchTree(key);
        if (node != TNULL) {
            return node.count;
        } else {
            return 0; // The word doesn't exist in the tree
        }
    }
    // find tree height
    public int treeHeight(Node node) {
        if (node == TNULL) {
            return 0;
        } else {
            int leftHeight = treeHeight(node.left);
            int rightHeight = treeHeight(node.right);

            return Math.max(leftHeight, rightHeight) + 1;
        }
    }
    //  return height
    public int getHeight() {
        return treeHeight(root);
    }
    // finds max nodes if perfectly balanced
    public long maxNodesPossible() {
        int treeHeight = treeHeight(root);
        return (long) Math.pow(2, treeHeight) -1;
    }
    // finds difference between perfectly balanced and real total
    public long nodeDifference() {
        long maxNodes = maxNodesPossible();
        return maxNodes - totalwordCount;
    }
    private void preOrderHelper(Node node) throws IOException {
        if (node != TNULL) {
            writer.write(node.data + " ");
            preOrderHelper(node.left);
            preOrderHelper(node.right);
        }
    }
    private void inOrderHelper(Node node) throws IOException {
        if (node != TNULL) {
            inOrderHelper(node.left);
            writer.write(node.data + " ");
            inOrderHelper(node.right);
        }
    }
    private void postOrderHelper(Node node) throws IOException {
        if (node != TNULL) {
            postOrderHelper(node.left);
            postOrderHelper(node.right);
            writer.write(node.data + " ");
        }
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
    private void fixDelete(Node x) {
        Node s;
        while (x != root && x.color == BLACK) {
            if (x == x.parent.left) {
                s = x.parent.right;
                if (s.color == RED) {
                    // case 3.1
                    s.color = BLACK;
                    x.parent.color = RED;
                    leftRotate(x.parent);
                    s = x.parent.right;
                }

                if (s.left.color == BLACK && s.right.color == BLACK) {
                    // case 3.2
                    s.color = RED;
                    x = x.parent;
                } else {
                    if (s.right.color == BLACK) {
                        // case 3.3
                        s.left.color = BLACK;
                        s.color = RED;
                        rightRotate(s);
                        s = x.parent.right;
                    }

                    // case 3.4
                    s.color = x.parent.color;
                    x.parent.color = BLACK;
                    s.right.color = BLACK;
                    leftRotate(x.parent);
                    x = root;
                }
            } else {
                s = x.parent.left;
                if (s.color == RED) {
                    // case 3.1
                    s.color = BLACK;
                    x.parent.color = RED;
                    rightRotate(x.parent);
                    s = x.parent.left;
                }

                if (s.right.color == BLACK) {
                    // case 3.2
                    s.color = RED;
                    x = x.parent;
                } else {
                    if (s.left.color == BLACK) {
                        // case 3.3
                        s.right.color = BLACK;
                        s.color = RED;
                        leftRotate(s);
                        s = x.parent.left;
                }

                    // case 3.4
                    s.color = x.parent.color;
                    x.parent.color = BLACK;
                    s.left.color = BLACK;
                    rightRotate(x.parent);
                    x = root;
                }
            }
        }
        x.color = BLACK;
    }
    private void rbTransplant(Node u, Node v){
        if (u.parent == null) {
            root = v;
        } else if (u == u.parent.left){
            u.parent.left = v;
        } else {
            u.parent.right = v;
        }
        v.parent = u.parent;
    }
    private void deleteNodeHelper(Node node, String key) {
        // find the node containing key
        Node z = TNULL;
        Node x, y;
        while (node != TNULL){
            if (node.data.equals(key)) {
                z = node;
            }

            if (key.compareTo(node.data) <= 0) {
                node = node.right;
            } else {
                node = node.left;
            }
        }

        if (z == TNULL) {
            System.out.println("Couldn't find key in the tree");
            return;
        }

        y = z;
        int yOriginalColor = y.color;
        if (z.left == TNULL) {
            x = z.right;
            rbTransplant(z, z.right);
        } else if (z.right == TNULL) {
            x = z.left;
            rbTransplant(z, z.left);
        } else {
            y = minimum(z.right);
            yOriginalColor = y.color;
            x = y.right;
            if (y.parent == z) {
                x.parent = y;
            } else {
                rbTransplant(y, y.right);
                y.right = z.right;
                y.right.parent = y;
            }

            rbTransplant(z, y);
            y.left = z.left;
            y.left.parent = y;
            y.color = z.color;
        }
        if (yOriginalColor == BLACK){
            fixDelete(x);
        }
    }
    private void fixInsert(Node k){
        Node u;
        while (k.parent.color == RED) {
            if (k.parent == k.parent.parent.right) {
                u = k.parent.parent.left; // uncle
                if (u.color == RED) {
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

                if (u.color == RED) {
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
    private void printHelper(Node root, String prefix, boolean isTail, BufferedWriter writer, int level) throws IOException {
        if (root != TNULL && level <= 3) {
            writer.write(prefix + (isTail ? "R----" : "L----") + root.data + "(" + (root.color == 1 ? "RED" : "BLACK") + ")\n");
            if (root.left != TNULL || root.right != TNULL) {
                printHelper(root.left, prefix + (isTail ? "     " : "|    "), false, writer, level + 1);
                printHelper(root.right, prefix + (isTail ? "     " : "|    "), true, writer, level + 1);
            }
        }
    }
    // setup initial values for tree
    public Program9(String file) throws IOException {
        TNULL = new Node();
        TNULL.color = BLACK;
        TNULL.left = null;
        TNULL.right = null;
        root = TNULL;
    }
    // Pre-Order traversal
    // Node.Left Subtree.Right Subtree
    public void preorder() throws IOException {
        preOrderHelper(this.root);
    }
    // In-Order traversal
    // Left Subtree . Node . Right Subtree
    public void inorder() throws IOException {
        inOrderHelper(this.root);
    }
    // Post-Order traversal
    // Left Subtree . Right Subtree . Node
    public void postorder() throws IOException {
        postOrderHelper(this.root);
    }
    // search the tree for the key k
    // and return the corresponding node
    public Node searchTree(String k) {
        return searchTreeHelper(this.root, k);
    }
    // find the node with the minimum key
    public Node minimum(Node node) {
        while (node.left != TNULL) {
            node = node.left;
        }
        return node;
    }
    // find the node with the maximum key
    public Node maximum(Node node) {
        while (node.right != TNULL) {
            node = node.right;
        }
        return node;
    }
    // find the successor of a given node
    public Node successor(Node x) {
        // if the right subtree is not null,
        // the successor is the leftmost node in the
        // right subtree
        if (x.right != TNULL) {
            return minimum(x.right);
        }

        // else it is the lowest ancestor of x whose
        // left child is also an ancestor of x.
        Node y = x.parent;
        while (y != TNULL && x == y.right) {
            x = y;
            y = y.parent;
        }
        return y;
    }
    // find the predecessor of a given node
    public Node predecessor(Node x) {
        // if the left subtree is not null,
        // the predecessor is the rightmost node in the
        // left subtree
        if (x.left != TNULL) {
            return maximum(x.left);
        }

        Node y = x.parent;
        while (y != TNULL && x == y.left) {
            x = y;
            y = y.parent;
        }

        return y;
    }
    // rotate left at node x
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
    // rotate right at node x
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
    // insert the key to the tree in its appropriate position, iterates wordcount on insertion
    public void insert(String key) {
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

    // delete the node from the tree
    public void deleteNode(String data) {
        deleteNodeHelper(this.root, data);
    }
    // print the tree structure
    public void prettyPrint(BufferedWriter writer) {
        try {
            printHelper(this.root, "", true, writer, 1); // Start printing from level 1
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    // reads input file, cleans the data, calls insertion method, and iterates the total word count
    static void readFromFile(Program9 bst) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("dracula.txt"));
            String input;
            while ((input = reader.readLine()) != null) {
                String[] splitInput = input.split("[^a-zA-Z]+");  // removes bad characters
                for (String word : splitInput) {
                    String cleanString = word.toLowerCase();
                    bst.insert(cleanString);
                    bst.totalwordCount++;
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error While Reading File");
        }
    }
    // writes height, count of "the", total real nodes, max nodes on perfectly balanced tree, and the difference between
    // perfect max and real total nodes.
    static void writeToFile(Program9 bst) {
        int treeHeight = bst.getHeight();
        int theCount = bst.wordCount("the");
        int totalWordCount = bst.totalwordCount;
        long nodeDifference = bst.nodeDifference();
        long maxNodesPossible = bst.maxNodesPossible();
        // writes results file
        try (BufferedWriter resultWriter = new BufferedWriter(new FileWriter("results.txt"))) {
            resultWriter.write("Tree Height: " + treeHeight);
            resultWriter.newLine();
            resultWriter.write("Count of 'the': " + theCount);
            resultWriter.newLine();
            resultWriter.write("Total Nodes in Tree: " + totalWordCount);
            resultWriter.newLine();
            resultWriter.write("Max Nodes if Perfectly Balanced: " + maxNodesPossible);
            resultWriter.newLine();
            resultWriter.write("Difference between Perfect Max and Real Total: " + nodeDifference);
            resultWriter.newLine();
        } catch (IOException e) {
            System.out.println("Error while writing results.txt");
        }
    }
    // main method calling
    public static void main(String [] args) throws IOException {
        Program9 bst = new Program9("rbtree.txt");
        readFromFile(bst);
        bst.prettyPrint(bst.writer);
        writeToFile(bst);
    }
}
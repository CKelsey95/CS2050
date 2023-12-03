/* Colton Kelsey
Program 12 */

import java.io.*;
import java.util.HashMap;

public class Program12 {
    int count = 0; // counter for instances of same word
    static long nodeCount = 0; // counter for total words processed
    public static HashMap<String,Integer> draculaHash = new HashMap<>();
    static long startTime; // nanotime start
    static long endTime; // nanotime end
    static long totalTime; // nanotime total
    public Program12(HashMap<String, Integer> draculaHash) {
    }

    public void insertHash(String key) {
        if (draculaHash.containsKey(key)) { // if key already exists, increment count, update hashmap
            count = draculaHash.get(key) + 1;
            draculaHash.put(key,count);
        } else {           // if key doesn't exist,insert key into hashmap, count = 1
            count = 1;
            draculaHash.put(key,count);
        }
    }
    public static void readFromFile(Program12 hash) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("dracula.txt"));
            String input;
            startTime = System.nanoTime();
            while ((input = reader.readLine()) != null) {
                String[] splitInput = input.split("[^a-zA-Z]+");  // removes bad characters
                for (String word : splitInput) {
                    String cleanString = word.toLowerCase();  // lowercase string
                    hash.insertHash(cleanString);  // calls insertHash method
                    nodeCount++; // increments total words processed counter
                }
            }
            endTime = System.nanoTime();
            totalTime = (endTime - startTime); // totals the time for processing and insertion to HashMap
            reader.close();
        } catch (IOException e) {
            System.out.println("Error While Reading File");
        }
    }
    // writes results for all three HashMaps to file.
    public static void writeToFile(Program12 hash, String description) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("results.txt", true));
            writer.write(description + "\n\n");
            writer.write("HashMap Size: " + draculaHash.size() + "\n");
            writer.write("Total Time: " + totalTime + " nanoseconds \n");
            writer.write("Node Count: " + nodeCount + "\n");
            writer.write("Load Factor: .75 \n");
            writer.write("-----------------------------\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("Error while writing results.txt");
        }
    }
    // writes results from Program 11 in file
    public static void writeToFileOld() {
        try {
            BufferedWriter writerOld = new BufferedWriter(new FileWriter("results.txt", true));
            writerOld.write("Program 11 Results\n");
            writerOld.write("\nHash Algorithm: First\nHash Table Size: 89231\nLoad Factor: 0.11641694030101646 \n");
            writerOld.write("\nHash Algorithm: Second \nHash Table Size: 449171\nLoad Factor: 0.021038758067640165 \n");
            writerOld.close();
        } catch (IOException e) {
            System.out.println("Error writing Program 11 results");
        }
    }
    public static void main(String[] args) {
        Program12 printHash1 = new Program12(draculaHash);
        readFromFile(printHash1);
        writeToFile(printHash1, "HashMap with Default Constructor");

        draculaHash = new HashMap<>(10000,0.75f);
        Program12 printHash2 = new Program12(draculaHash);
        readFromFile(printHash2);
        writeToFile(printHash2, "Hashmap with Initial Capacity 10000 and LF of 0.75");

        draculaHash = new HashMap<>(20000, 0.75f);
        Program12 printHash3 = new Program12(draculaHash);
        readFromFile(printHash3);
        writeToFile(printHash3, "Hashmap with Initial Capacity 20000 and LF of 0.75");

        writeToFileOld();
    }
}
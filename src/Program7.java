/* Colton Kelsey
    Program 7
 */

import java.io.*;
import java.util.*;

public class Program7 {
    // creates indexes, all integer arrays + array list
    static int Intindex = 0;
    static int StrIndex = 0;
    static int[] bubbleIntArray = new int[20000];
    static int[] selectionIntArray = new int[20000];
    static int[] insertionIntArray = new int[20000];
    static ArrayList<Integer> ArrIntList = new ArrayList<>();
    // creates all String arrays + array list
    static String[] bubbleStringArray = new String[10000];
    static String[] selectionStringArray = new String[10000];
    static String[] insertionStringArray = new String[10000];
    static ArrayList<String> ArrStringList = new ArrayList<>();
    // creates variables for all sorting times
    static long bubbleIntTimeTotal;
    static long selectionIntTimeTotal;
    static long insertionIntTimeTotal;
    static long bubbleStringTimeTotal;
    static long selectionStringTimeTotal;
    static long insertionStringTimeTotal;
    static long systemStringTimeTotal;

    public static void main(String[] args) throws IOException {
        readFromIntFile();   // INT reads from file and stores into arrays
        int n = bubbleIntArray.length;  // sets int to array length for sorting
        bubbleIntSort(bubbleIntArray,n);  // INT bubble sort operation
        selectionIntSort(selectionIntArray); // INT selection sort operations
        insertIntSort(insertionIntArray);  // INT insertion sort opertations

        readFromStringFile();              // reads from file and stores into array
        int s = bubbleStringArray.length;  // sets int to array length for sorting
        bubbleStringSort(bubbleStringArray,s); // STRING bubble sort operation
        int a = selectionStringArray.length; // sets int to array length for sorting
        selectStringSort(selectionStringArray,a); // STRING selection sort operation
        systemStringSort();             // string collections.sort method
        insertStringSort(insertionStringArray);  // String insertion sort

        // writes all time totals to file
        writeResultsToFile("src\\results.txt", bubbleIntTimeTotal, selectionIntTimeTotal, insertionIntTimeTotal,
                bubbleStringTimeTotal, selectionStringTimeTotal, systemStringTimeTotal, insertionStringTimeTotal);
    }
        // start of Integer sorting operations
    static void bubbleIntSort(int bubbleIntArray[],int n){
        long timeStart = System.nanoTime();
        int i, j, temp;
        boolean swapped;
        for (i = 0; i < n - 1; i++) {
            swapped = false;
            for (j = 0; j < n - i - 1; j++) {
                if (bubbleIntArray[j] > bubbleIntArray[j + 1]) {
                    temp = bubbleIntArray[j];
                    bubbleIntArray[j] = bubbleIntArray[j + 1];
                    bubbleIntArray[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped)
                break;
        }
        long timeEnd = System.nanoTime();
        bubbleIntTimeTotal = (timeEnd - timeStart);
    }
    static void selectionIntSort(int selectionIntArray[]){
       int n = selectionIntArray.length;
       long timeStart = System.nanoTime();
       for (int i = 0; i < n - 1; i++)
       {
           int min_idx = i;
           for (int j = i + 1; j < n; j++)
               if (selectionIntArray[j] < selectionIntArray[min_idx])
                   min_idx = j;

           int temp = selectionIntArray[min_idx];
           selectionIntArray[min_idx] = selectionIntArray[i];
           selectionIntArray[i] = temp;
       }
       long timeEnd = System.nanoTime();
       selectionIntTimeTotal = (timeEnd - timeStart);
    }
    static void insertIntSort(int insertionIntArray[]){
        int n = insertionIntArray.length;
        long timeStart = System.nanoTime();
        for (int i = 1; i < n; i++) {
            int key = insertionIntArray[i];
            int j = i -1;
            while (j >= 0 && insertionIntArray[j]  > key) {
                insertionIntArray[j + 1] = insertionIntArray[j];
                j = j -1;
            }
            insertionIntArray[j + 1] = key;
        }

        long timeEnd = System.nanoTime();
        insertionIntTimeTotal = (timeEnd - timeStart);
    }
    // start of String sorting operations
    static void bubbleStringSort(String bubbleStringArray[], int s) {
        String temp;
        long timeStart = System.nanoTime();
        for (int j = 0; j < s -1; j++){
            for (int i = j + 1; i < s; i++) {
                if (bubbleStringArray[j].compareTo(bubbleStringArray[i]) > 0) {
                    temp = bubbleStringArray[j];
                    bubbleStringArray[j] = bubbleStringArray[i];
                    bubbleStringArray[i] = temp;
                }
            }
        }

        long timeEnd = System.nanoTime();
        bubbleStringTimeTotal = (timeEnd - timeStart);
    }
    static void selectStringSort(String selectionStringArray[], int s) {
        long timeStart = System.nanoTime();
        for(int i = 0; i < s - 1; i++) {
            int min_index= i;
            String minStr = selectionStringArray[i];
            for(int j = i + 1; j < s; j++){
                if(selectionStringArray[j].compareTo(minStr) < 0) {
                    minStr = selectionStringArray[j];
                    min_index = j;
                }
            }
            if(min_index != i) {
                String temp = selectionStringArray[min_index];
                selectionStringArray[min_index] = selectionStringArray[i];
                selectionStringArray[i] = temp;
            }
        }
        long timeEnd = System.nanoTime();
        selectionStringTimeTotal = (timeEnd - timeStart);
    }
    static void systemStringSort(){
        long timeStart = System.nanoTime();
        Collections.sort(ArrStringList);
        long timeEnd = System.nanoTime();
        systemStringTimeTotal = (timeEnd - timeStart);
    }
    static void insertStringSort(String insertionStringArray[]){
        int n = insertionStringArray.length;
        long timeStart = System.nanoTime();
        for (int i = 1; i < n; i++) {
            String key = insertionStringArray[i];
            int j = i -1;
            while (j >= 0 && insertionStringArray[j].compareTo(key) >0) {
                insertionStringArray[j + 1] = insertionStringArray[j];
                j = j - 1;
            }
            insertionStringArray[j + 1] = key;
        }
        long timeEnd = System.nanoTime();
        insertionStringTimeTotal = (timeEnd - timeStart);
    }
    // reads from file to create integer arrays + array list
    static void readFromIntFile(){
        try {
            BufferedReader NumberFile = new BufferedReader(new FileReader("src\\NumbersInFile.txt"));
            String line;
            while ((line = NumberFile.readLine()) != null) { // only while file is not empty
                int number = Integer.parseInt(line);
                ArrIntList.add(number);
                bubbleIntArray[Intindex] = number;
                selectionIntArray[Intindex] = number;
                insertionIntArray[Intindex] = number;
                Intindex++;
            }
            NumberFile.close();
        }
            catch (IOException e){
            System.out.println("Error reading the Int file: " + e.getMessage());
        }
    }
    // reads from file to create String arrays + array list
    static void readFromStringFile(){
        try {
            BufferedReader StringFile = new BufferedReader(new FileReader("src\\StringsInFile.txt"));
            String line;
            while ((line = StringFile.readLine()) != null) {
                ArrStringList.add(line);
                bubbleStringArray[StrIndex] = line;
                selectionStringArray[StrIndex] = line;
                insertionStringArray[StrIndex] = line;
                StrIndex++;
            }
            StringFile.close();
        } catch (IOException e){
            System.out.println("Error reading the String file: " + e.getMessage());
        }
    }
    // writes results of all operations to file
    static void writeResultsToFile(String fileName, long bubbleIntTimeTotal, long selectionIntTimeTotal, long insertionIntTimeTotal,
                                   long bubbleStringTimeTotal, long selectStringTimeTotal,
                                   long systemStringTimeTotal, long insertionStringTimeTotal) {
        try {
            BufferedWriter results = new BufferedWriter(new FileWriter(fileName));

            // Write the time totals and array element counts to the file
            results.write("INTEGER: total bubble sort time is: " + bubbleIntTimeTotal + " nanoseconds\n");
            results.write("INTEGER: total selection sort time is: " + selectionIntTimeTotal + " nanoseconds\n");
            results.write("INTEGER: total insertion sort time is: " +insertionIntTimeTotal + " nanoseconds\n");
            results.write("STRING: total bubble sort time is: " + bubbleStringTimeTotal + " nanoseconds\n");
            results.write("STRING: total select sort time is: " + selectStringTimeTotal + " nanoseconds\n");
            results.write("STRING: total system sort time is: " + systemStringTimeTotal + " nanoseconds\n");
            results.write("STRING: total insertion sort time is: " + insertionStringTimeTotal + " nanoseconds\n");

            // Write the total elements from both files
            results.write("Total Integer Array Elements: " + Intindex + "\n");
            results.write("Total String Array Elements: " + StrIndex + "\n");

            // Close the writer
            results.close();

        } catch (IOException e) {
            System.out.println("Error writing results to file: " + e.getMessage());
        }
    }
}
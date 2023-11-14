import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Program10 {
    static int[] timIntArray = new int[20000];
    static int[] quickIntArray = new int[20000];
    static int IntIndex = 0;
    static ArrayList<Integer> ArrIntList = new ArrayList<>();
    static long timIntTimeTotal;
    static long quickIntTimeTotal;

    public static void main(String[] args) throws IOException {
        readFromIntFile();
        int n = timIntArray.length;
        timIntSort(timIntArray,0,n - 1);
        quickIntSort(quickIntArray,0,n - 1);
        writeResultsToFile();
    }
    static void timIntSort(int[] arr, int left, int right) {
        long timeStart = System.nanoTime();
        if (right - left + 1 <= 32) {
            insertionSort(arr, left, right);
        } else {
            int mid = (left + right) / 2;
            timIntSort(arr, left, mid);
            timIntSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
        long timeEnd = System.nanoTime();
        timIntTimeTotal = (timeEnd - timeStart);
    }
    static void quickIntSort(int[] arr, int low, int high) {
        long timeStart = System.nanoTime();
        if (low < high) {
            int partitionIndex = partition(arr, low, high);
            quickIntSort(arr, low, partitionIndex - 1);
            quickIntSort(arr, partitionIndex + 1, high);
        }
        long timeEnd = System.nanoTime();
        quickIntTimeTotal = (timeEnd - timeStart);
    }
    static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;

                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }
    static void insertionSort(int[] arr, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int key = arr[i];
            int j = i - 1;

            while (j >= left && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = key;
        }
    }
    static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];

        for (int i = 0; i < n1; ++i) {
            leftArray[i] = arr[left + i];
        }
        for (int j = 0; j < n2; ++j) {
            rightArray[j] = arr[mid + 1 + j];
        }

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (leftArray[i] <= rightArray[j]) {
                arr[k] = leftArray[i];
                i++;
            } else {
                arr[k] = rightArray[j];
                j++;
            }
            k++;
        }
        while (i < n1) {
            arr[k] = leftArray[i];
            i++;
            k++;
        }
        while (j < n2) {
            arr[k] = rightArray[j];
            j++;
            k++;
        }
    }
    static void readFromIntFile() throws IOException {
        try {
            BufferedReader intFile = new BufferedReader(new FileReader("NumbersInFile.txt"));
            String line;
            while ((line = intFile.readLine()) != null) {
                int number = Integer.parseInt(line);
                ArrIntList.add(number);
                timIntArray[IntIndex] = number;
                quickIntArray[IntIndex] = number;
                IntIndex++;
            }
            intFile.close();
        } catch (IOException e) {
            System.out.println("Error reading Int file: " + e.getMessage());
        }
    }
    static void writeResultsToFile() throws IOException {
        try (BufferedWriter results = new BufferedWriter(new FileWriter("results.txt"))) {
            results.write("Sort Method    Time To Sort(nanoseconds)\n");
            results.write("Bubble            " + "589458700\n");
            results.write("Insertion         " + "33371400\n");
            results.write("Selection         " + "181738800\n");
            results.write("Timsort           " + timIntTimeTotal+("\n"));
            results.write("Quicksort         " + quickIntTimeTotal+("\n"));

        } catch (IOException e) {
            System.out.println("Error writing results to file: " + e.getMessage());
        }
    }
}
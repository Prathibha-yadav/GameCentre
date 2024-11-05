import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class SortingPuzzle {
    public static void play() {
        JFrame frame = new JFrame("Sorting Puzzle");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        JTextField inputField = new JTextField(15);
        JButton bubbleSortButton = new JButton("Bubble Sort");
        JButton selectionSortButton = new JButton("Selection Sort");
        JButton mergeSortButton = new JButton("Merge Sort");
        JButton quickSortButton = new JButton("Quick Sort");
        JButton builtInSortButton = new JButton("Built-in Sort");
        JTextArea outputArea = new JTextArea(10, 30);
        outputArea.setEditable(false);
        
        // Event listener for Bubble Sort
        bubbleSortButton.addActionListener(e -> sortAndDisplay(inputField.getText(), "Bubble Sort", outputArea));

        // Event listener for Selection Sort
        selectionSortButton.addActionListener(e -> sortAndDisplay(inputField.getText(), "Selection Sort", outputArea));

        // Event listener for Merge Sort
        mergeSortButton.addActionListener(e -> sortAndDisplay(inputField.getText(), "Merge Sort", outputArea));

        // Event listener for Quick Sort
        quickSortButton.addActionListener(e -> sortAndDisplay(inputField.getText(), "Quick Sort", outputArea));

        // Event listener for Built-in Sort
        builtInSortButton.addActionListener(e -> sortAndDisplay(inputField.getText(), "Built-in Sort", outputArea));

        frame.add(new JLabel("Enter numbers (comma separated):"));
        frame.add(inputField);
        frame.add(bubbleSortButton);
        frame.add(selectionSortButton);
        frame.add(mergeSortButton);
        frame.add(quickSortButton);
        frame.add(builtInSortButton);
        frame.add(new JScrollPane(outputArea));
        frame.setVisible(true);
    }

    private static void sortAndDisplay(String input, String sortType, JTextArea outputArea) {
        try {
            // Parse input numbers
            String[] inputArray = input.split(",");
            int[] numbers = Arrays.stream(inputArray)
                    .map(String::trim) // Trim whitespace
                    .mapToInt(Integer::parseInt)
                    .toArray();

            outputArea.setText(sortType + ":\n");
            switch (sortType) {
                case "Bubble Sort":
                    bubbleSort(numbers, outputArea);
                    break;
                case "Selection Sort":
                    selectionSort(numbers, outputArea);
                    break;
                case "Merge Sort":
                    mergeSort(numbers, 0, numbers.length - 1, outputArea);
                    break;
                case "Quick Sort":
                    quickSort(numbers, 0, numbers.length - 1, outputArea);
                    break;
                default:
                    Arrays.sort(numbers);
                    outputArea.append("Sorted: " + Arrays.toString(numbers));
                    break;
            }
            outputArea.append("Final Sorted Array: " + Arrays.toString(numbers));
        } catch (NumberFormatException e) {
            outputArea.setText("Invalid input! Please enter valid integers.");
        } catch (Exception e) {
            outputArea.setText("An error occurred: " + e.getMessage());
        }
    }

    // Bubble Sort algorithm with intermediate steps
    private static void bubbleSort(int[] array, JTextArea outputArea) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    // Swap array[j] and array[j + 1]
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    outputArea.append("Swapped: " + Arrays.toString(array) + "\n");
                }
            }
        }
    }

    // Selection Sort algorithm with intermediate steps
    private static void selectionSort(int[] array, JTextArea outputArea) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            // Swap the found minimum element with the first element
            if (minIndex != i) { // Swap only if a smaller element was found
                int temp = array[minIndex];
                array[minIndex] = array[i];
                array[i] = temp;
                outputArea.append("Swapped: " + Arrays.toString(array) + "\n");
            }
        }
    }

    // Merge Sort algorithm with intermediate steps
    private static void mergeSort(int[] array, int left, int right, JTextArea outputArea) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(array, left, mid, outputArea);
            mergeSort(array, mid + 1, right, outputArea);
            merge(array, left, mid, right, outputArea);
        }
    }

    private static void merge(int[] array, int left, int mid, int right, JTextArea outputArea) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        // Create temp arrays
        int[] L = new int[n1];
        int[] R = new int[n2];

        // Copy data to temp arrays
        System.arraycopy(array, left, L, 0, n1);
        System.arraycopy(array, mid + 1, R, 0, n2);

        // Merge the temp arrays
        int i = 0, j = 0;
        int k = left;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                array[k] = L[i];
                i++;
            } else {
                array[k] = R[j];
                j++;
            }
            k++;
        }

        // Copy remaining elements of L[] if any
        while (i < n1) {
            array[k] = L[i];
            i++;
            k++;
        }

        // Copy remaining elements of R[] if any
        while (j < n2) {
            array[k] = R[j];
            j++;
            k++;
        }

        outputArea.append("Merged: " + Arrays.toString(array) + "\n");
    }

    // Quick Sort algorithm with intermediate steps
    private static void quickSort(int[] array, int low, int high, JTextArea outputArea) {
        if (low < high) {
            int pi = partition(array, low, high, outputArea);
            quickSort(array, low, pi - 1, outputArea);
            quickSort(array, pi + 1, high, outputArea);
        }
    }

    private static int partition(int[] array, int low, int high, JTextArea outputArea) {
        int pivot = array[high]; // Pivot element
        int i = (low - 1); // Index of smaller element
        for (int j = low; j < high; j++) {
            if (array[j] < pivot) {
                i++;
                // Swap array[i] and array[j]
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                outputArea.append("Swapped: " + Arrays.toString(array) + "\n");
            }
        }
        // Swap array[i + 1] and array[high] (or pivot)
        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;
        outputArea.append("Swapped: " + Arrays.toString(array) + "\n");
        return i + 1;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SortingPuzzle::play);
    }
}

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
        JButton builtInSortButton = new JButton("Built-in Sort");
        JTextArea outputArea = new JTextArea(10, 30);
        outputArea.setEditable(false);
        
        // Event listener for Bubble Sort
        bubbleSortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sortAndDisplay(inputField.getText(), "Bubble Sort", true, outputArea);
            }
        });

        // Event listener for Selection Sort
        selectionSortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sortAndDisplay(inputField.getText(), "Selection Sort", false, outputArea);
            }
        });

        // Event listener for Built-in Sort
        builtInSortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sortAndDisplay(inputField.getText(), "Built-in Sort", false, outputArea);
            }
        });

        frame.add(new JLabel("Enter numbers (comma separated):"));
        frame.add(inputField);
        frame.add(bubbleSortButton);
        frame.add(selectionSortButton);
        frame.add(builtInSortButton);
        frame.add(new JScrollPane(outputArea));
        frame.setVisible(true);
    }

    private static void sortAndDisplay(String input, String sortType, boolean bubbleSort, JTextArea outputArea) {
        try {
            // Parse input numbers
            String[] inputArray = input.split(",");
            int[] numbers = Arrays.stream(inputArray)
                    .map(String::trim) // Trim whitespace
                    .mapToInt(Integer::parseInt)
                    .toArray();

            // Sort based on selected sorting algorithm
            if (bubbleSort) {
                bubbleSort(numbers);
                outputArea.setText(sortType + ": " + Arrays.toString(numbers));
            } else if (sortType.equals("Selection Sort")) {
                selectionSort(numbers);
                outputArea.setText(sortType + ": " + Arrays.toString(numbers));
            } else {
                Arrays.sort(numbers);
                outputArea.setText(sortType + ": " + Arrays.toString(numbers));
            }
        } catch (NumberFormatException e) {
            outputArea.setText("Invalid input! Please enter valid integers.");
        } catch (Exception e) {
            outputArea.setText("An error occurred: " + e.getMessage());
        }
    }

    // Bubble Sort algorithm
    private static void bubbleSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    // Swap array[j] and array[j+1]
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    // Selection Sort algorithm
    private static void selectionSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            // Swap the found minimum element with the first element
            int temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }
    }
}

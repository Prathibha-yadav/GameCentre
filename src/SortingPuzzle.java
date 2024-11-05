import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class SortingPuzzle {
    public static void play() {
        JFrame frame = new JFrame("Sorting Puzzle");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        JTextField inputField = new JTextField(15);
        JButton sortButton = new JButton("Sort");
        JTextArea outputArea = new JTextArea(5, 20);
        outputArea.setEditable(false);
        
        sortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = inputField.getText();
                String[] inputArray = input.split(",");
                int[] numbers = Arrays.stream(inputArray)
                        .mapToInt(Integer::parseInt)
                        .toArray();
                Arrays.sort(numbers);
                outputArea.setText("Sorted Numbers: " + Arrays.toString(numbers));
            }
        });

        frame.add(new JLabel("Enter numbers (comma separated):"));
        frame.add(inputField);
        frame.add(sortButton);
        frame.add(new JScrollPane(outputArea));
        frame.setVisible(true);
    }
}

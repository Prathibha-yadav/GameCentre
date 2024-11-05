import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WordSearch {
    private static JTextArea puzzleArea;
    private static JTextArea wordArea;

    public static void play() {
        JFrame frame = new JFrame("Word Search");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        puzzleArea = new JTextArea(10, 20);
        wordArea = new JTextArea(5, 20);
        JButton checkButton = new JButton("Check Words");

        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement word checking logic here
                JOptionPane.showMessageDialog(frame, "Word check functionality is not yet implemented.");
            }
        });

        frame.add(new JLabel("Enter Word Search Puzzle:"), BorderLayout.NORTH);
        frame.add(new JScrollPane(puzzleArea), BorderLayout.CENTER);
        frame.add(new JLabel("Enter Words (comma separated):"), BorderLayout.EAST);
        frame.add(new JScrollPane(wordArea), BorderLayout.SOUTH);
        frame.add(checkButton, BorderLayout.WEST);

        frame.setVisible(true);
    }
}

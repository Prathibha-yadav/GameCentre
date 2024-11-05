import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class WordSearch {
    private static JTextArea puzzleArea;
    private static JTextArea wordArea;
    private static JTextArea resultArea;

    public static void play() {
        JFrame frame = new JFrame("Word Search");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        puzzleArea = new JTextArea(10, 30);
        wordArea = new JTextArea(5, 30);
        resultArea = new JTextArea(5, 30);
        resultArea.setEditable(false);

        JButton checkButton = new JButton("Check Words");
        
        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String puzzleText = puzzleArea.getText().trim();
                String wordsText = wordArea.getText().trim();
                String[] words = wordsText.split(",");

                List<String> foundWords = new ArrayList<>();
                for (String word : words) {
                    word = word.trim();
                    if (wordExistsInPuzzle(puzzleText, word)) {
                        foundWords.add(word);
                    }
                }

                if (foundWords.isEmpty()) {
                    resultArea.setText("No words found.");
                } else {
                    resultArea.setText("Found Words: " + String.join(", ", foundWords));
                }
            }
        });

        frame.add(new JLabel("Enter Word Search Puzzle (one line per row):"), BorderLayout.NORTH);
        frame.add(new JScrollPane(puzzleArea), BorderLayout.CENTER);
        frame.add(new JLabel("Enter Words (comma separated):"), BorderLayout.EAST);
        frame.add(new JScrollPane(wordArea), BorderLayout.SOUTH);
        frame.add(checkButton, BorderLayout.WEST);
        frame.add(new JScrollPane(resultArea), BorderLayout.EAST);

        frame.setVisible(true);
    }

    private static boolean wordExistsInPuzzle(String puzzle, String word) {
        String[] rows = puzzle.split("\n");
        for (String row : rows) {
            if (row.contains(word)) {
                return true;
            }
        }

        // Check for words vertically
        for (int col = 0; col < rows[0].length(); col++) {
            StringBuilder columnWord = new StringBuilder();
            for (String row : rows) {
                if (col < row.length()) {
                    columnWord.append(row.charAt(col));
                }
            }
            if (columnWord.toString().contains(word)) {
                return true;
            }
        }

        // Check for words diagonally
        return checkDiagonally(rows, word);
    }

    private static boolean checkDiagonally(String[] rows, String word) {
        int numRows = rows.length;
        int numCols = rows[0].length();

        // Check diagonal from top-left to bottom-right
        for (int row = 0; row <= numRows - word.length(); row++) {
            for (int col = 0; col <= numCols - word.length(); col++) {
                StringBuilder diagonalWord = new StringBuilder();
                for (int k = 0; k < word.length(); k++) {
                    diagonalWord.append(rows[row + k].charAt(col + k));
                }
                if (diagonalWord.toString().equals(word)) {
                    return true;
                }
            }
        }

        // Check diagonal from top-right to bottom-left
        for (int row = 0; row <= numRows - word.length(); row++) {
            for (int col = word.length() - 1; col < numCols; col++) {
                StringBuilder diagonalWord = new StringBuilder();
                for (int k = 0; k < word.length(); k++) {
                    diagonalWord.append(rows[row + k].charAt(col - k));
                }
                if (diagonalWord.toString().equals(word)) {
                    return true;
                }
            }
        }

        return false;
    }
}

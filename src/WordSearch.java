import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Random;

public class WordSearch {
    private static JTextArea puzzleArea;
    private static JTextArea wordArea;
    private static JTextArea resultArea;
    private static JButton checkButton;
    private static JButton generateButton;
    private static int score;

    private static final String[] WORDS = {"JAVA", "SWING", "PROGRAMMING", "CODE", "COMPUTER", "LANGUAGE", "DEVELOPER", "FUNCTION"};

    public static void play() {
        score = 0; // Reset score for each new game
        generatePuzzle();

        JFrame frame = new JFrame("Word Search Game");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        puzzleArea = new JTextArea();
        puzzleArea.setFont(new Font("Monospaced", Font.PLAIN, 20));
        puzzleArea.setEditable(false);

        wordArea = new JTextArea();
        wordArea.setFont(new Font("Monospaced", Font.PLAIN, 16));
        wordArea.setEditable(true);
        wordArea.setLineWrap(true);
        wordArea.setWrapStyleWord(true);

        resultArea = new JTextArea();
        resultArea.setFont(new Font("Monospaced", Font.PLAIN, 16));
        resultArea.setEditable(false);
        resultArea.setText("Score: " + score);

        checkButton = new JButton("Check Words");
        checkButton.addActionListener(e -> checkWords());

        generateButton = new JButton("Generate New Puzzle");
        generateButton.addActionListener(e -> generatePuzzle());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(checkButton);
        buttonPanel.add(generateButton);

        frame.add(new JScrollPane(puzzleArea), BorderLayout.CENTER);
        frame.add(new JScrollPane(wordArea), BorderLayout.EAST);
        frame.add(new JScrollPane(resultArea), BorderLayout.SOUTH);
        frame.add(buttonPanel, BorderLayout.NORTH);

        frame.setVisible(true);
    }

    private static void generatePuzzle() {
        StringBuilder puzzle = new StringBuilder();
        char[][] grid = new char[10][10];

        // Fill grid with random letters
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                grid[i][j] = (char) ('A' + new Random().nextInt(26));
            }
        }

        // Place words in grid
        for (String word : WORDS) {
            placeWordInGrid(grid, word);
        }

        // Build the puzzle string
        for (char[] row : grid) {
            for (char c : row) {
                puzzle.append(c).append(" ");
            }
            puzzle.append("\n");
        }

        puzzleArea.setText(puzzle.toString());
        wordArea.setText("");
        resultArea.setText("Score: " + score);
    }

    private static void placeWordInGrid(char[][] grid, String word) {
        Random rand = new Random();
        int wordLength = word.length();
        boolean placed = false;

        while (!placed) {
            int direction = rand.nextInt(2); // 0 for horizontal, 1 for vertical
            int row = rand.nextInt(grid.length);
            int col = rand.nextInt(grid[0].length);

            // Check if word can fit
            if (direction == 0 && col + wordLength <= grid[0].length) { // Horizontal
                boolean canPlace = true;
                for (int i = 0; i < wordLength; i++) {
                    if (grid[row][col + i] != ' ') {
                        canPlace = false;
                        break;
                    }
                }
                if (canPlace) {
                    for (int i = 0; i < wordLength; i++) {
                        grid[row][col + i] = word.charAt(i);
                    }
                    placed = true;
                }
            } else if (direction == 1 && row + wordLength <= grid.length) { // Vertical
                boolean canPlace = true;
                for (int i = 0; i < wordLength; i++) {
                    if (grid[row + i][col] != ' ') {
                        canPlace = false;
                        break;
                    }
                }
                if (canPlace) {
                    for (int i = 0; i < wordLength; i++) {
                        grid[row + i][col] = word.charAt(i);
                    }
                    placed = true;
                }
            }
        }
    }

    private static void checkWords() {
        String[] enteredWords = wordArea.getText().toUpperCase().split("\\s+");
        for (String word : enteredWords) {
            if (Arrays.asList(WORDS).contains(word)) {
                score++;
            }
        }
        resultArea.setText("Score: " + score);
        wordArea.setText("");
    }
}

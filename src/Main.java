import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Game Centre");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 400);
        frame.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Welcome to Game Centre", JLabel.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        titleLabel.setForeground(new Color(255, 87, 34));
        frame.add(titleLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 3, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton guessTheNumberButton = createStyledButton("Guess the Number");
        JButton ticTacToeButton = createStyledButton("Tic-Tac-Toe");
        JButton mazeSolverButton = createStyledButton("Maze Solver");
        JButton sortingPuzzleButton = createStyledButton("Sorting Puzzle");
        JButton memoryMatchingButton = createStyledButton("Memory Matching");
        JButton towerOfHanoiButton = createStyledButton("Tower of Hanoi");
        JButton hangmanButton = createStyledButton("Hangman");
        JButton puzzleSliderButton = createStyledButton("Puzzle Slider");
        JButton sudokuButton = createStyledButton("Sudoku"); // New Sudoku button

        buttonPanel.add(guessTheNumberButton);
        buttonPanel.add(ticTacToeButton);
        buttonPanel.add(mazeSolverButton);
        buttonPanel.add(sortingPuzzleButton);
        buttonPanel.add(memoryMatchingButton);
        buttonPanel.add(towerOfHanoiButton);
        buttonPanel.add(hangmanButton);
        buttonPanel.add(puzzleSliderButton);
        buttonPanel.add(sudokuButton); // Add Sudoku button to panel
        frame.add(buttonPanel, BorderLayout.CENTER);

        guessTheNumberButton.addActionListener(e -> GuessTheNumber.play());
        ticTacToeButton.addActionListener(e -> TicTacToe.play());
        mazeSolverButton.addActionListener(e -> MazeSolver.play());
        sortingPuzzleButton.addActionListener(e -> SortingPuzzle.play());
        memoryMatchingButton.addActionListener(e -> MemoryMatching.play());
        towerOfHanoiButton.addActionListener(e -> TowerOfHanoi.play());
        hangmanButton.addActionListener(e -> Hangman.play());
        puzzleSliderButton.addActionListener(e -> PuzzleSlider.play());
        sudokuButton.addActionListener(e -> Sudoku.play()); // Action for Sudoku

        frame.setVisible(true);
    }

    private static JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(63, 81, 181)); // Indigo color
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.DARK_GRAY, 2),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
        return button;
    }
}

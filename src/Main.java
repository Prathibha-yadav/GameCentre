import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Game Centre");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JButton guessTheNumberButton = new JButton("Guess the Number");
        JButton ticTacToeButton = new JButton("Tic-Tac-Toe");
        JButton mazeSolverButton = new JButton("Maze Solver");
        JButton sortingPuzzleButton = new JButton("Sorting Puzzle");
        JButton memoryMatchingButton = new JButton("Memory Matching");
        JButton towerOfHanoiButton = new JButton("Tower of Hanoi");
        JButton wordSearchButton = new JButton("Word Search");

        JPanel panel = new JPanel();
        panel.add(guessTheNumberButton);
        panel.add(ticTacToeButton);
        panel.add(mazeSolverButton);
        panel.add(sortingPuzzleButton);
        panel.add(memoryMatchingButton);
        panel.add(towerOfHanoiButton);
        panel.add(wordSearchButton);
        frame.add(panel);

        guessTheNumberButton.addActionListener(e -> GuessTheNumber.play());
        ticTacToeButton.addActionListener(e -> TicTacToe.play());
        mazeSolverButton.addActionListener(e -> MazeSolver.play());
        sortingPuzzleButton.addActionListener(e -> SortingPuzzle.play());
        memoryMatchingButton.addActionListener(e -> MemoryMatching.play());
        towerOfHanoiButton.addActionListener(e -> TowerOfHanoi.play());
        wordSearchButton.addActionListener(e -> WordSearch.play());

        frame.setVisible(true);
    }
}

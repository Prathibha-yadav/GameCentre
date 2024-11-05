import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class PuzzleSlider {
    private static final int GRID_SIZE = 4; // 4x4 grid
    private JFrame frame;
    private JButton[][] buttons;
    private int emptyRow, emptyCol; // Track position of the empty space

    public PuzzleSlider() {
        frame = new JFrame("Puzzle Slider");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new GridLayout(GRID_SIZE, GRID_SIZE));

        buttons = new JButton[GRID_SIZE][GRID_SIZE];
        initializePuzzle();

        frame.setVisible(true);
    }

    private void initializePuzzle() {
        ArrayList<Integer> numbers = new ArrayList<>();
        for (int i = 1; i < GRID_SIZE * GRID_SIZE; i++) {
            numbers.add(i);
        }
        numbers.add(0); // 0 represents the empty space
        Collections.shuffle(numbers);

        int index = 0;
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                int number = numbers.get(index++);
                JButton button = new JButton(number == 0 ? "" : String.valueOf(number));
                button.setFont(new Font("SansSerif", Font.BOLD, 24));
                button.addActionListener(new MoveAction(row, col));
                
                buttons[row][col] = button;
                frame.add(button);

                if (number == 0) {
                    emptyRow = row;
                    emptyCol = col;
                }
            }
        }
    }

    private boolean isSolved() {
        int count = 1;
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                if (row == GRID_SIZE - 1 && col == GRID_SIZE - 1) break;
                if (!buttons[row][col].getText().equals(String.valueOf(count++))) {
                    return false;
                }
            }
        }
        return true;
    }

    private class MoveAction implements ActionListener {
        private int row, col;

        public MoveAction(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if ((Math.abs(emptyRow - row) == 1 && emptyCol == col) ||
                (Math.abs(emptyCol - col) == 1 && emptyRow == row)) {

                buttons[emptyRow][emptyCol].setText(buttons[row][col].getText());
                buttons[row][col].setText("");

                emptyRow = row;
                emptyCol = col;

                if (isSolved()) {
                    JOptionPane.showMessageDialog(frame, "Congratulations! You solved the puzzle!");
                }
            }
        }
    }

    public static void play() {
        new PuzzleSlider();
    }
}

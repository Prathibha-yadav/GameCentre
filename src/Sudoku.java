import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Sudoku {
    private static final int SIZE = 9;
    private static final int SUBGRID_SIZE = 3;
    private JFrame frame;
    private JTextField[][] cells;

    public Sudoku() {
        frame = new JFrame("Sudoku");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(new BorderLayout());

        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(SIZE, SIZE));
        cells = new JTextField[SIZE][SIZE];

        initializeBoard(gridPanel);

        JButton checkButton = new JButton("Check Solution");
        checkButton.addActionListener(new CheckAction());

        frame.add(gridPanel, BorderLayout.CENTER);
        frame.add(checkButton, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    private void initializeBoard(JPanel gridPanel) {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                JTextField cell = new JTextField();
                cell.setFont(new Font("SansSerif", Font.BOLD, 18));
                cell.setHorizontalAlignment(JTextField.CENTER);
                cell.setBorder(BorderFactory.createLineBorder(Color.GRAY));

                // Optional: Lock some cells as pre-filled numbers
                if (Math.random() < 0.2) {  // Randomly pre-fill about 20% of cells
                    int num = (int) (Math.random() * SIZE + 1);
                    cell.setText(String.valueOf(num));
                    cell.setEditable(false);
                    cell.setBackground(new Color(220, 220, 220));
                }

                cells[row][col] = cell;
                gridPanel.add(cell);
            }
        }
    }

    private boolean isBoardValid() {
        return rowsValid() && colsValid() && subgridsValid();
    }

    private boolean rowsValid() {
        for (int row = 0; row < SIZE; row++) {
            boolean[] seen = new boolean[SIZE + 1];
            for (int col = 0; col < SIZE; col++) {
                String text = cells[row][col].getText();
                if (!text.isEmpty()) {
                    int num = Integer.parseInt(text);
                    if (num < 1 || num > SIZE || seen[num]) return false;
                    seen[num] = true;
                }
            }
        }
        return true;
    }

    private boolean colsValid() {
        for (int col = 0; col < SIZE; col++) {
            boolean[] seen = new boolean[SIZE + 1];
            for (int row = 0; row < SIZE; row++) {
                String text = cells[row][col].getText();
                if (!text.isEmpty()) {
                    int num = Integer.parseInt(text);
                    if (num < 1 || num > SIZE || seen[num]) return false;
                    seen[num] = true;
                }
            }
        }
        return true;
    }

    private boolean subgridsValid() {
        for (int startRow = 0; startRow < SIZE; startRow += SUBGRID_SIZE) {
            for (int startCol = 0; startCol < SIZE; startCol += SUBGRID_SIZE) {
                boolean[] seen = new boolean[SIZE + 1];
                for (int row = 0; row < SUBGRID_SIZE; row++) {
                    for (int col = 0; col < SUBGRID_SIZE; col++) {
                        String text = cells[startRow + row][startCol + col].getText();
                        if (!text.isEmpty()) {
                            int num = Integer.parseInt(text);
                            if (num < 1 || num > SIZE || seen[num]) return false;
                            seen[num] = true;
                        }
                    }
                }
            }
        }
        return true;
    }

    private class CheckAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (isBoardValid()) {
                JOptionPane.showMessageDialog(frame, "Congratulations! Your solution is correct.");
            } else {
                JOptionPane.showMessageDialog(frame, "The solution is incorrect. Keep trying!");
            }
        }
    }

    public static void play() {
        new Sudoku();
    }
}

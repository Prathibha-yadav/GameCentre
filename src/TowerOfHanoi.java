import javax.swing.*;
import java.awt.*;
import java.util.Stack;

public class TowerOfHanoi {
    private static int totalDisks;

    public static void play() {
        totalDisks = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of disks (1-5):"));
        if (totalDisks < 1 || totalDisks > 5) {
            JOptionPane.showMessageDialog(null, "Please enter a number between 1 and 5.");
            return;
        }
        JFrame frame = new JFrame("Tower of Hanoi");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        
        JTextArea textArea = new JTextArea(10, 30);
        textArea.setEditable(false);
        frame.add(new JScrollPane(textArea));
        
        solveHanoi(totalDisks, 'A', 'C', 'B', textArea);
        
        frame.setVisible(true);
    }

    private static void solveHanoi(int n, char source, char target, char auxiliary, JTextArea textArea) {
        if (n == 1) {
            textArea.append("Move disk 1 from " + source + " to " + target + "\n");
            return;
        }
        solveHanoi(n - 1, source, auxiliary, target, textArea);
        textArea.append("Move disk " + n + " from " + source + " to " + target + "\n");
        solveHanoi(n - 1, auxiliary, target, source, textArea);
    }
}

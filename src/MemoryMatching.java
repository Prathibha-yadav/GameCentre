import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MemoryMatching {
    private static JButton[] buttons = new JButton[16];
    private static String[] cardValues = {"A", "A", "B", "B", "C", "C", "D", "D",
            "E", "E", "F", "F", "G", "G", "H", "H"};
    private static int firstCardIndex = -1;
    private static int matchedPairs = 0;
    private static int attempts = 0;

    public static void play() {
        JFrame frame = new JFrame("Memory Matching Game");
        frame.setSize(400, 400);
        frame.setLayout(new GridLayout(4, 4));
        
        List<String> values = Arrays.asList(cardValues);
        Collections.shuffle(values);
        values.toArray(cardValues);

        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton("?");
            final int index = i;
            buttons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (firstCardIndex == -1) {
                        firstCardIndex = index;
                        buttons[index].setText(cardValues[index]);
                    } else {
                        buttons[index].setText(cardValues[index]);
                        attempts++;
                        if (!cardValues[firstCardIndex].equals(cardValues[index])) {
                            // If the cards don't match, hide them again after a delay
                            Timer timer = new Timer(1000, new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    buttons[firstCardIndex].setText("?");
                                    buttons[index].setText("?");
                                    firstCardIndex = -1;
                                }
                            });
                            timer.setRepeats(false);
                            timer.start();
                        } else {
                            // Cards match
                            matchedPairs++;
                            firstCardIndex = -1; // Reset for the next turn
                            // Check if the game is over
                            if (matchedPairs == 8) {
                                JOptionPane.showMessageDialog(frame, "Congratulations! You've matched all pairs in " + attempts + " attempts.");
                                frame.dispose(); // Close the game
                            }
                        }
                    }
                }
            });
            frame.add(buttons[i]);
        }

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
}

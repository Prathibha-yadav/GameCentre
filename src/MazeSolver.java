import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class MazeSolver {
    private static int[][] maze;
    private static JButton[][] buttons;
    private static int playerX = 0; // Player's current X position
    private static int playerY = 0; // Player's current Y position

    public static void play() {
        generateMaze(); // Generate a random maze
        
        JFrame frame = new JFrame("Interactive Maze Solver");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new BorderLayout());

        JPanel mazePanel = new JPanel(new GridLayout(maze.length, maze[0].length));
        buttons = new JButton[maze.length][maze[0].length];
        
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                buttons[i][j] = new JButton();
                if (maze[i][j] == 1) {
                    buttons[i][j].setBackground(Color.WHITE); // Open path
                } else {
                    buttons[i][j].setBackground(Color.BLACK); // Wall
                }
                buttons[i][j].setEnabled(false); // Disable button clicks
                mazePanel.add(buttons[i][j]);
            }
        }

        // Highlight the initial player position
        highlightPlayerPosition();

        // Key listener for player movement
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        movePlayer(-1, 0);
                        break;
                    case KeyEvent.VK_DOWN:
                        movePlayer(1, 0);
                        break;
                    case KeyEvent.VK_LEFT:
                        movePlayer(0, -1);
                        break;
                    case KeyEvent.VK_RIGHT:
                        movePlayer(0, 1);
                        break;
                }
            }
        });

        frame.add(mazePanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private static void movePlayer(int deltaX, int deltaY) {
        int newX = playerX + deltaX;
        int newY = playerY + deltaY;

        // Check for out of bounds and walls
        if (newX >= 0 && newX < maze.length && newY >= 0 && newY < maze[0].length && maze[newX][newY] == 1) {
            // Move player to the new position
            buttons[playerX][playerY].setBackground(Color.WHITE); // Reset previous position to white
            playerX = newX;
            playerY = newY;
            highlightPlayerPosition(); // Highlight new position

            // Check if the player reached the exit
            if (playerX == maze.length - 1 && playerY == maze[0].length - 1) {
                JOptionPane.showMessageDialog(null, "Congratulations! You've solved the maze!");
            }
        }
    }

    private static void highlightPlayerPosition() {
        buttons[playerX][playerY].setBackground(Color.BLUE); // Highlight current position
    }

    private static void generateMaze() {
        // Simple maze generation for demonstration purposes
        Random rand = new Random();
        maze = new int[5][5];
        
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                maze[i][j] = rand.nextInt(2); // Randomly assign 0 (wall) or 1 (path)
            }
        }
        // Ensure start and end points are paths
        maze[0][0] = 1; // Start
        maze[maze.length - 1][maze[0].length - 1] = 1; // End
        
        // Ensure there's at least one path from start to finish
        for (int i = 1; i < maze.length; i++) {
            maze[i][0] = 1; // Create a path in the first column
        }
        for (int j = 1; j < maze[0].length; j++) {
            maze[maze.length - 1][j] = 1; // Create a path in the last row
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MazeSolver::play);
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MazeSolver {
    private static int[][] maze = {
            {1, 0, 0, 0, 0},
            {1, 1, 1, 1, 0},
            {0, 0, 0, 1, 0},
            {0, 1, 1, 1, 1},
            {0, 0, 0, 0, 1}
    };
    private static boolean[][] visited;
    private static List<Point> path = new ArrayList<>();

    public static void play() {
        visited = new boolean[maze.length][maze[0].length];
        JFrame frame = new JFrame("Maze Solver");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setLayout(new GridLayout(maze.length, maze[0].length));

        JButton[][] buttons = new JButton[maze.length][maze[0].length];
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                buttons[i][j] = new JButton();
                if (maze[i][j] == 1) {
                    buttons[i][j].setBackground(Color.WHITE);
                } else {
                    buttons[i][j].setBackground(Color.BLACK);
                }
                buttons[i][j].setEnabled(false); // Disable button clicks
                frame.add(buttons[i][j]);
            }
        }

        if (solveMaze(0, 0)) {
            for (Point p : path) {
                buttons[p.x][p.y].setBackground(Color.GREEN);
            }
        } else {
            JOptionPane.showMessageDialog(frame, "No path found!");
        }

        frame.setVisible(true);
    }

    private static boolean solveMaze(int x, int y) {
        if (x < 0 || x >= maze.length || y < 0 || y >= maze[0].length || maze[x][y] == 0 || visited[x][y]) {
            return false;
        }

        visited[x][y] = true; // Mark the cell as visited
        path.add(new Point(x, y)); // Add the current cell to the path

        // Check if we've reached the destination
        if (x == maze.length - 1 && y == maze[0].length - 1) {
            return true;
        }

        // Explore the neighbors (right, down, left, up)
        if (solveMaze(x + 1, y) || solveMaze(x, y + 1) || solveMaze(x - 1, y) || solveMaze(x, y - 1)) {
            return true;
        }

        // Backtrack if not found
        path.remove(path.size() - 1);
        return false;
    }
}

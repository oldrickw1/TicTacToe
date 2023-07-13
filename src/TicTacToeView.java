import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class TicTacToeView extends JFrame {
    private Square[] squares = new Square[9];

    public TicTacToeView() {
        setName("Tic-Tac-Toe");
        setSize(300, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(3,3));
    }

    public void setSquares(ActionListener buttonListener) {
        for (int i = 0; i < 9; i++) {
            Square square = new Square(i);
            square.addActionListener(buttonListener);
            squares[i] = square;
            add(square);
        }
    }

    public void displayMessage(String message) {
        JOptionPane.showMessageDialog(this, message);

    }


    public void updateBoard(Mark[] marks) {
        for (int i = 0; i < 9; i++) {
            if (marks[i] != null) {
                String path = marks[i].getImagePath();
                squares[i].setIcon(new ImageIcon(path));
            } else {
                squares[i].setIcon(null);
            }
        }
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame implements ActionListener{
    private final JButton[] squares = new JButton[Board.SQUARES_IN_BOARD];
    private final Controller controller;

    public GUI(Controller controller) {
        this.controller = controller;
        setName("Tic-Tac-Toe");
        setSize(300, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 3));
        setButtons();
    }

    public void setButtons() {
        for (int i = 0; i < Board.SQUARES_IN_BOARD; i++) {
            JButton btn = new JButton();
            btn.setFocusable(false);
            btn.setFont(new Font("Arial", Font.BOLD, 60));
            btn.setActionCommand(Integer.toString(i));
            btn.addActionListener(this);
            squares[i] = btn;
            add(btn);
        }
    }

    public void displayMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public void updateBoard(Cell[] marks) {
        for (int i = 0; i < Board.SQUARES_IN_BOARD; i++) {
            switch (marks[i]) {
                case CROSS -> squares[i].setText("X");
                case NOUGHT -> squares[i].setText("O");
                case EMPTY -> squares[i].setText("");
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int position = Integer.parseInt(e.getActionCommand());
        controller.handleBoardClick(position);
    }
}

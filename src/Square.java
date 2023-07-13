import javax.swing.*;

public class Square extends JButton {
    private final int position;

    public Square(int squareNumber) {
        this.position = squareNumber;
    }

    public int getPosition() {
        return position;
    }
}

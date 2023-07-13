import javax.swing.*;

public class Square extends JButton {
    private int cellNumber;

    public Square(int cellNumber) {
        this.cellNumber = cellNumber;
    }

    public int getCellNumber() {
        return cellNumber;
    }
}

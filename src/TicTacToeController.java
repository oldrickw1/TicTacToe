import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class TicTacToeController {
    private Board theModel;
    private TicTacToeView theView;
    private AI ai;
    private boolean myTurn;

    public TicTacToeController(Board theModel, TicTacToeView theView) {
        this.theModel = theModel;
        this.theView = theView;
        this.ai = new AI();
        myTurn = true;
    }

    public void run() {
        theView.setSquares(buttonListener);
        theView.setVisible(true);
    }


    ActionListener buttonListener = (e -> {
        Square square = (Square) e.getSource();

        try {
            // Preventing player from making another move before opponent has made one
            if (!myTurn) {
                System.out.println("Await your turn!");
                return;
            }
            // my turn
            theModel.addMark(new Mark(MarkType.X), square.getCellNumber());
            myTurn = false;
            if (updateAndCheckIfGameOver()) {
                myTurn = true;
                return;
            }
            // opponent's turn
            Timer timer = new Timer(1500, event -> {
                theModel.addMark(new Mark(MarkType.O), ai.makeRandomChoice(theModel.getMarks()));
                if (updateAndCheckIfGameOver()) {
                    myTurn = true;
                    return;
                }
                myTurn = true;
            });
            timer.setRepeats(false);
            timer.start();


        } catch (IllegalStateException exception) {
            theView.displayMessage("Illegal step");
        }

    });

    private boolean updateAndCheckIfGameOver() {
        theView.updateBoard(theModel.getMarks());
        int rc = theModel.checkIfGameOver();
        switch (rc) {
            case 1 -> {
                theView.displayMessage("You win!");
                theModel = new Board();
                theView.updateBoard(theModel.getMarks());
                return true;
            }
            case 2 -> {
                theView.displayMessage("You lose!");
                theModel = new Board();
                theView.updateBoard(theModel.getMarks());
                return true;
            }
            case 3 -> {
                theView.displayMessage("Tie!");
                theModel = new Board();
                theView.updateBoard(theModel.getMarks());
                return true;
            }
        }
        return false;
    }



}

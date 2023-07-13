import javax.swing.*;
import java.awt.event.ActionListener;

public class TicTacToeController {
    private Board theModel;
    private TicTacToeView theView;
    private Opponent opponent;
    private boolean myTurn;

    public TicTacToeController(Board theModel, TicTacToeView theView) {
        this.theModel = theModel;
        this.theView = theView;
        this.opponent = new NaiveOpponent();
        myTurn = true;
    }

    public void run() {
        theView.setSquares(buttonListener);
        theView.setVisible(true);
    }

    ActionListener buttonListener = (e -> {
        Square square = (Square) e.getSource();

        try {
            // player's turn
            // Preventing player from making another move before opponent has made one. Not happy with this approach..
            if (!myTurn) {
                return;
            }
            theModel.addMark(new Mark(Mark.MarkType.X), square.getPosition());
            myTurn = false;
            theView.updateBoard(theModel.getBoard());
            if (checkIfGameOver()) {
                resetGame();
                return;
            }
            // opponent's turn
            Timer timer = new Timer(1500, event -> {
                theModel.addMark(new Mark(Mark.MarkType.O), opponent.makeChoice(theModel.getBoard()));
                theView.updateBoard(theModel.getBoard());
                if (checkIfGameOver()) {
                    resetGame();
                    return;
                }
                myTurn = true;
            });
            timer.setRepeats(false);
            timer.start();

        } catch (IllegalStateException exception) {
            theView.displayMessage("Illegal move");
        }
    });


    private boolean checkIfGameOver() {
        int rc = theModel.checkIfGameOver();
        switch (rc) {
            case 1 -> {
                theView.displayMessage("You win!");
                return true;
            }
            case 2 -> {
                theView.displayMessage("You lose!");
                return true;
            }
            case 3 -> {
                theView.displayMessage("Tie!");
                return true;
            }
        }
        return false;
    }

    private void resetGame() {
        theModel = new Board();
        theView.updateBoard(theModel.getBoard());
        myTurn = true;
    }
}

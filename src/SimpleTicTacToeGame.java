import javax.swing.*;
import java.awt.event.ActionListener;

public class Controller {
    private Board theModel;
    private GUI theView;
    private Opponent opponent;
    private boolean myTurn;

    public Controller(Board theModel, GUI theView) {
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
            if (theModel.getGameStatus() != Board.GameStatus.RUNNING) {
                theView.displayMessage(theModel.getGameStatus().getMessage());
                resetGame();
                return;
            }
            // opponent's turn
            Timer timer = new Timer(1500, event -> {
                theModel.addMark(new Mark(Mark.MarkType.O), opponent.makeChoice(theModel.getBoard()));
                theView.updateBoard(theModel.getBoard());
                if (theModel.getGameStatus() != Board.GameStatus.RUNNING) {
                    theView.displayMessage(theModel.getGameStatus().getMessage());
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


    private void resetGame() {
        theModel = new Board();
        theView.updateBoard(theModel.getBoard());
        myTurn = true;
    }
}

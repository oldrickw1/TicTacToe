import javax.swing.*;
import java.awt.event.ActionListener;

public class SimpleTicTacToeGame implements Controller{
    private Board theModel;
    private GUI theView = null;
    private Opponent opponent = null;
    private boolean myTurn;

    public SimpleTicTacToeGame(Board theModel) {

        this.theModel = theModel;
        this.theView = new GUI(this);
        this.opponent = new NaiveOpponent();
        myTurn = true;
    }

    public void run() {
        theView.setVisible(true);
    }

    private void resetGame() {
        theModel = new Board();
        theView.updateBoard(theModel.getBoard());
        myTurn = true;
    }

    @Override
    public void handleBoardClick(int position) {
        try {
            // Preventing player from making another move before opponent has made one. Not happy with this approach..
            if (!myTurn) {
                return;
            }
            theModel.makeMove(Cell.CROSS, position);
            theModel.checkIfGameOverAndSetStatus();
            myTurn = false;
            theView.updateBoard(theModel.getBoard());
            if (checkIfGameOver()) return;
            Timer timer = new Timer(1500, opponentMove);
            if (checkIfGameOver()) return;
            timer.setRepeats(false);
            timer.start();
            myTurn = true;

        } catch (IllegalStateException exception) {
            theView.displayMessage("Illegal move");
        }
    }

    ActionListener opponentMove = (e) -> {
        theModel.makeMove(Cell.NOUGHT, opponent.makeChoice(theModel.getBoard()));
        theModel.checkIfGameOverAndSetStatus();
        theView.updateBoard(theModel.getBoard());
    };


    private boolean checkIfGameOver() {
        if (theModel.getGameStatus() != Board.GameStatus.RUNNING) {
            theView.displayMessage(theModel.getGameStatus().getMessage());
            resetGame();
            return true;
        }
        return false;
    }
}


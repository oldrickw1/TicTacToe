import javax.swing.*;

public class SimpleTicTacToeGame implements Controller{
    private Board model;
    private GUI view;
    private Opponent opponent;
    private boolean myTurn;

    public SimpleTicTacToeGame(Board model) {

        this.model = model;
        this.view = new GUI(this);
        this.opponent = new NaiveOpponent();
        myTurn = true;
    }

    public void run() {
        view.setVisible(true);
    }

    private void resetGame() {
        model = new Board();
        view.updateBoard(model.getBoard());
    }

    @Override
    public void handleBoardClick(int position) {
        try {
            if (myTurn) {
                model.makeMove(Cell.CROSS, position);
                view.updateBoard(model.getBoard());
                if (checkIfGameOver()) return;

                makeOpponentMove();
            }
        } catch (IllegalStateException exception) {
            view.displayMessage("Illegal move");
        }
    }

    private void makeOpponentMove() {
        myTurn = false;
        Timer timer = new Timer(1500, (e) -> {
            model.makeMove(Cell.NOUGHT, opponent.makeChoice(model.getBoard()));
            view.updateBoard(model.getBoard());
            myTurn = true;
            checkIfGameOver();
        });

        timer.setRepeats(false);
        timer.start();
    };



    private boolean checkIfGameOver() {
        Board.GameStatus gameStatus = model.checkIfGameOver();
        if (gameStatus != Board.GameStatus.RUNNING) {
            handleGameOver(gameStatus);
            return true;
        }
        return false;
    }

    private void handleGameOver(Board.GameStatus gameStatus) {
        view.displayMessage(gameStatus.getMessage());
        resetGame();
    }
}


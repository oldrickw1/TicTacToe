public class Main {
    public static void main(String[] args) {
        Board theModel = new Board();
        TicTacToeView theView = new TicTacToeView();
        TicTacToeController controller = new TicTacToeController(theModel,theView);

        controller.run();
    }
}

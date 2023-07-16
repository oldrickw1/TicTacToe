public class Main {
    public static void main(String[] args) {
        Board theModel = new Board();
        SimpleTicTacToeGame simpleTicTacToeGame = new SimpleTicTacToeGame(theModel);

        simpleTicTacToeGame.run();
    }
}

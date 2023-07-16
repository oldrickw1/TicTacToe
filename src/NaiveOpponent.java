import java.util.ArrayList;
import java.util.Random;

public class NaiveOpponent implements Opponent{
    private final Random rand;

    public NaiveOpponent() {
        rand = new Random();
    }

    public int makeChoice(Cell[] board) {
        ArrayList<Integer> openSquares = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            if (board[i] == Cell.EMPTY) {
                openSquares.add(i);
            }
        }
        return openSquares.get(rand.nextInt(openSquares.size()));
    }
}

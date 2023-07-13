import java.util.ArrayList;
import java.util.Random;

public class NaiveOpponent implements Opponent{
    private final Random rand;

    public NaiveOpponent() {
        rand = new Random();
    }

    public int makeChoice(Mark[] marks) {
        ArrayList<Integer> openSquares = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            if (marks[i] == null) {
                openSquares.add(i);
            }
        }
        return openSquares.get(rand.nextInt(openSquares.size()));
    }
}

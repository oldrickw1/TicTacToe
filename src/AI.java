import java.util.ArrayList;
import java.util.Random;

public class AI {
    private Random rand;

    public AI() {
        rand = new Random();
    }

    public int makeRandomChoice(Mark[] marks) {
        int j = 0;
        ArrayList<Integer> options = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            if (marks[i] == null) {
                options.add(i);
            }
        }
        int cellNumber = options.get(rand.nextInt(options.size()));
        return cellNumber;
    }
}

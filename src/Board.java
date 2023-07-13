import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Board {
    private final ArrayList<HashSet<Integer>> WINNING_COMBINATIONS = new ArrayList<>(List.of(
            new HashSet<>(Set.of(0, 1, 2)),
            new HashSet<>(Set.of(3, 4, 5)),
            new HashSet<>(Set.of(6, 7, 8)),
            new HashSet<>(Set.of(0, 3, 6)),
            new HashSet<>(Set.of(1, 4, 7)),
            new HashSet<>(Set.of(2, 5, 8)),
            new HashSet<>(Set.of(0, 4, 8)),
            new HashSet<>(Set.of(2, 4, 6))
    ));
    private final Mark[] board;


    public Board() {
        board = new Mark[9];
    }

    public void addMark(Mark mark, int squareNumber) {
        if (board[squareNumber] != null) {
            throw new IllegalStateException("Already a mark here");
        } else {
            board[squareNumber] = mark;
        }
    }



    public int checkIfGameOver() {
        if (checkIfSomeoneHasWon(Mark.MarkType.X)) {
            return 1;
        } else if (checkIfSomeoneHasWon(Mark.MarkType.O)) {
            return 2;
        } else if (isTie()) {
            return 3;
        } else {
            return 0;
        }
    }

    private boolean isTie() {
        boolean boardIsFull = true;
        for (int i = 0; i < 9; i++) {
            if (board[i] == null) {
                boardIsFull = false;
                break;
            }
        }
        return boardIsFull;
    }

    private boolean checkIfSomeoneHasWon(Mark.MarkType markType) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < 9; i++) {
            if (board[i] != null && board[i].getMarkType() == markType) {
                set.add(i);
            }
        }
        return containsSubSet(WINNING_COMBINATIONS, set);
    }

    public boolean containsSubSet(ArrayList<HashSet<Integer>> list, Set<Integer> targetSet) {
        for (Set<Integer> set : list) {
            if (targetSet.containsAll(set)) {
                return true;
            }
        }
        return false;
    }

    public Mark[] getBoard() {
        return board;
    }
}


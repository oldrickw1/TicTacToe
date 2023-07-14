import java.util.*;

public class Board {
    public static final int SQUARES_IN_BOARD = 9;
    private static final List<Set<Integer>> WINNING_COMBINATIONS = new ArrayList<>(List.of(
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
    private final Set<Integer> xSet = new HashSet<>();
    private final Set<Integer> oSet = new HashSet<>();
    private GameStatus gameStatus = GameStatus.RUNNING;


    public Board() {
        board = new Mark[SQUARES_IN_BOARD];
    }

    public void addMark(Mark mark, int position) {
        if (board[position] != null) {
            throw new IllegalStateException("Already a mark here");
        } else {
            updatePlayerState(mark, position);
            board[position] = mark;
        }
    }

    private void updatePlayerState(Mark mark, int position) {
        if (mark.getMarkType() == Mark.MarkType.X) {
            xSet.add(position);
        } else if (mark.getMarkType() == Mark.MarkType.O) {
            oSet.add(position);
        }
    }

    public boolean checkIfGameOverAndSetStatus() {
        if (checkIfWinner(Mark.MarkType.X)) {
            gameStatus = GameStatus.X_HAS_WON;
            return true;
        } else if (checkIfWinner(Mark.MarkType.O)) {
            gameStatus = GameStatus.O_HAS_WON;
            return true;
        } else if (isTie()) {
            gameStatus = GameStatus.TIE;
            return true;
        } else {
            return false;
        }
    }

    private boolean isTie() {
        return (!Arrays.asList(board).contains(null));
    }


    private boolean checkIfWinner(Mark.MarkType markType) {
        return containsSubSet(WINNING_COMBINATIONS, markType == Mark.MarkType.X ? xSet : oSet);
    }

    public boolean containsSubSet(List<Set<Integer>> list, Set<Integer> targetSet) {
        return list.stream().anyMatch(targetSet::containsAll);
    }

    public Mark[] getBoard() {
        return board;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public enum GameStatus {
        RUNNING(null),
        X_HAS_WON("Player X has won!"),
        O_HAS_WON("Player O has won!"),
        TIE("Tie!");

        private String message;

        GameStatus(String message) {

        }

        public String getMessage() {
            return message;
        }
    }
}


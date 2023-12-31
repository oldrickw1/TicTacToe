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

    private final Cell[] board;
    private final Set<Integer> crossSet = new HashSet<>();
    private final Set<Integer> noughtSet = new HashSet<>();

    public Board() {
        board = new Cell[SQUARES_IN_BOARD];
        for (int i = 0; i < SQUARES_IN_BOARD; i++) {
            board[i] = Cell.EMPTY;
        }
    }

    public void makeMove(Cell cell, int position) {
        if (board[position] != Cell.EMPTY) {
            throw new IllegalStateException("Already a mark here");
        } else {
            updatePlayerState(cell, position);
            board[position] = cell;
        }
    }

    private void updatePlayerState(Cell cell, int position) {
        if (cell == Cell.CROSS) {
            crossSet.add(position);
        } else if (cell == Cell.NOUGHT) {
            noughtSet.add(position);
        }
    }

    public GameStatus checkIfGameOver() {
        if (checkIfWinner(Cell.CROSS)) {
            return GameStatus.CROSS_WINS;
        } else if (checkIfWinner(Cell.NOUGHT)) {
            return GameStatus.NOUGHT_WINS;
        } else if (isTie()) {
            return GameStatus.TIE;
        } else {
            return GameStatus.RUNNING;
        }
    }

    private boolean isTie() {
        return (!Arrays.asList(board).contains(Cell.EMPTY));
    }

    private boolean checkIfWinner(Cell cell) {
        return containsSubSet(WINNING_COMBINATIONS, cell == Cell.CROSS ? crossSet : noughtSet);
    }

    public boolean containsSubSet(List<Set<Integer>> list, Set<Integer> targetSet) {
        return list.stream().anyMatch(targetSet::containsAll);
    }

    public Cell[] getBoard() {
        return board;
    }


    public enum GameStatus {
        RUNNING(null),
        CROSS_WINS("Player X has won!"),
        NOUGHT_WINS("Player O has won!"),
        TIE("Tie!");

        private final String message;

        GameStatus(String message) {
            this.message=message;
        }

        public String getMessage() {
            return message;
        }
    }
}


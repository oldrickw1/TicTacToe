import java.util.*;

public class Board {
    private Mark[] marks;
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



    public Board() {
        marks = new Mark[9];
    }

    public void addMark(Mark mark, int cellNumber) {
        if (marks[cellNumber] != null || invalidCellNumber(cellNumber)) {
            throw new IllegalStateException("Already a mark here");
        } else {
            marks[cellNumber] = mark;
        }
    }

    private boolean invalidCellNumber(int n) {
        return (n < 0 || n > 8);
    }


    public int checkIfGameOver() {
        if (checkIfSomeoneHasWon(MarkType.X)) {
            return 1;
        } else if (checkIfSomeoneHasWon(MarkType.O)) {
            return 2;
        } else if (isTie()) {
            return 3;
        } else {
            return 0;
        }
    }


    private boolean isTie() {
        for (int i = 0; i < 9; i++) {
            if (marks[i] == null) {
                return false;
            }
        }
        return true;
    }

    private boolean checkIfSomeoneHasWon(MarkType markType) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < 9; i++) {
            if (marks[i] != null && marks[i].getType() == markType) {
                set.add(i);
            }
        }
        System.out.println("The set of " + markType + "'s " + set.toString());
        return containsSubSet(WINNING_COMBINATIONS,set);
    }

    public boolean containsSubSet(ArrayList<HashSet<Integer>> list, Set<Integer> targetSet) {
        for (Set<Integer> set : list) {
            if (targetSet.containsAll(set)) {
                return true;
            }
        }
        return false;
    }

    public Mark[] getMarks() {
        return marks;
    }
}


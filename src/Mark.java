public class Mark {
    private final MarkType type;

    public Mark(MarkType type) {
        this.type = type;
    }

    public MarkType getMarkType() {
        return type;
    }


    public enum MarkType {
        X("Resources/o_small.png"),
        O("Resources/x_small.png");

        private final String pathToIcon;

        MarkType(String pathToIcon) {
            this.pathToIcon = pathToIcon;
        }

        public String getPathToIcon() {
            return pathToIcon;
        }
    }

}

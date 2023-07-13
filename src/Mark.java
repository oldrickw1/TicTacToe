public class Mark {
    private MarkType type;
    private String imagePath;

    public Mark(MarkType type) {
        this.type = type;
        if (type == MarkType.O) {
            imagePath = "Resources/o_small.png";
        } else if (type == MarkType.X) {
            imagePath = "Resources/x_small.png";
        }
    }

    public MarkType getType() {
        return type;
    }

    public String getImagePath() {
        return imagePath;
    }
}

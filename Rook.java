public class Rook extends Figure {

    static final String[] figureDraw = {
            "█████████RRRRRR████████",
            "█████████R    R████████",
            "█████████RRRRRR████████",
            "█████████R   R█████████",
            "█████████R    R████████"
    };

    public Rook(int rowPosition, int cowPosition) {
        super(rowPosition, cowPosition);
    }

    @Override
    public void move(int rowPosition, int cowPosition) {

        super.setRowPosition(rowPosition);
        super.setCowPosition(cowPosition);
    }

    @Override
    public String draw(int row, String color) {

        return color + figureDraw[row];
    }
}

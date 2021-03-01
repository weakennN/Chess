public class Bishop extends Figure {

    static final String[] figureDraw = {
            "███████BBBBBB██████████",
            "███████B    B██████████",
            "███████BBBBBB██████████",
            "███████B    B██████████",
            "███████BBBBBB██████████"
    };

    public Bishop(int rowPosition, int cowPosition) {
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

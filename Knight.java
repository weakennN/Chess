public class Knight extends Figure {

    static final String[] figureDraw = {
            "████████NN   N█████████",
            "████████N N  N█████████",
            "████████N  N N█████████",
            "████████N   NN█████████",
            "████████N    N█████████"
    };

    public Knight(int rowPosition, int cowPosition) {
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

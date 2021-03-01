public class Queen extends Figure {

    static final String[] figureDraw = {
            "██████████QQQQ█████████",
            "█████████Q    Q████████",
            "█████████Q    Q████████",
            "█████████QQQQQQ████████",
            "███████████████Q███████"
    };

    public Queen(int rowPosition, int cowPosition) {
        super(rowPosition, cowPosition);
    }

    @Override
    public void move(int rowPosition, int cowPosition) {

        super.setRowPosition(rowPosition);
        super.setCowPosition(cowPosition);
    }

    @Override
    public String draw(int row,String color){

        return color + figureDraw[row];
    }
}

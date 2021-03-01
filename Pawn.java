public class Pawn extends Figure {

    static final String[] figureDraw = {
            "███████PPPPPPPPP███████",
            "███████P       P███████",
            "███████PPPPPPPPP███████",
            "███████P███████████████",
            "███████P███████████████"
    };

    private boolean firstMove;

    public Pawn(int rowPosition, int cowPosition) {
        super(rowPosition, cowPosition);

        this.firstMove = false;
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

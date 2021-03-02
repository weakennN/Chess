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

        isMoveValid(rowPosition, cowPosition);
        this.firstMove = true;
        super.setRowPosition(rowPosition);
        super.setCowPosition(cowPosition);
    }

    @Override
    public String draw(int row, String color) {

        return color + figureDraw[row];
    }

    @Override
    public void possibleMoves() {

        if (!this.firstMove) {
            super.addPossibleRowPosition(super.getRowPosition() + 2);
            super.addPossibleColPosition(super.getCowPosition());
        } else {
            super.addPossibleRowPosition(super.getRowPosition() + 1);
        }

    }

    @Override
    protected void isMoveValid(int row, int col) {

        if (!super.getPossibleRowPositions().contains(row) && !super.getPossibleColPositions().contains(col)) {

            throw new IllegalArgumentException("Invalid move.");
        }

    }
}

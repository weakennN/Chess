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
        super.emptyMoves();
    }

    @Override
    public String draw(int row, String color) {

        return color + figureDraw[row];
    }

    @Override
    public void possibleMoves() {

        super.addPossibleRowPosition(super.getRowPosition() - 2);
        super.addPossibleRowPosition(super.getRowPosition() + 2);
        super.addPossibleColPosition(super.getColPosition() + 1);
        super.addPossibleColPosition(super.getColPosition() - 1);
        super.addPossibleRowPosition(super.getRowPosition() + 1);
        super.addPossibleRowPosition(super.getRowPosition() - 1);
        super.addPossibleColPosition(super.getColPosition() + 2);
        super.addPossibleColPosition(super.getColPosition() - 2);

    }

    @Override
    protected void isMoveValid(int row, int col) {

        if (!super.getPossibleRowPositions().contains(row) || !super.getPossibleColPositions().contains(col)) {

            throw new IllegalArgumentException("Invalid move.");
        }
    }

    @Override
    public void attackSquare(int row, int col) {


    }
}

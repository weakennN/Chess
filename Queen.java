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
        super.emptyMoves();
    }

    @Override
    public String draw(int row, String color) {

        return color + figureDraw[row];
    }

    @Override
    public void possibleMoves() {

        super.addPossibleRowPosition(super.getRowPosition() + 1);
        super.addPossibleRowPosition(super.getRowPosition() - 1);
        super.addPossibleColPosition(super.getColPosition() + 1);
        super.addPossibleColPosition(super.getColPosition() - 1);
    }

    @Override
    protected void isMoveValid(int row, int col) {

        if (super.getRowPosition() > row || super.getRowPosition() < row && super.getColPosition() != col && !super.getPossibleColPositions().contains(col)) {

            throw new IllegalArgumentException("Invalid move,");
        } else if (super.getColPosition() > col || super.getColPosition() < col && super.getRowPosition() != row && !super.getPossibleRowPositions().contains(row)) {
            throw new IllegalArgumentException("Invalid move,");
        }
    }

    @Override
    public void attackSquare(int row, int col) {

        this.move(row, col);
    }
}

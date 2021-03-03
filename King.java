public class King extends Figure {

    static final String[] figureDraw = {
            "█████████K  K██████████",
            "█████████K K███████████",
            "█████████KK████████████",
            "█████████K K███████████",
            "█████████K  K██████████"
    };

    public King(int rowPosition, int cowPosition) {
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

    @Override
    public void possibleMoves() {

        super.addPossibleRowPosition(super.getRowPosition() + 1);
        super.addPossibleRowPosition(super.getRowPosition() - 1);
        super.addPossibleColPosition(super.getColPosition() + 1);
        super.addPossibleColPosition(super.getColPosition() - 1);
    }

    @Override
    protected void isMoveValid(int row, int col) {

        if (!super.getPossibleRowPositions().contains(row) && !super.getPossibleColPositions().contains(col)) {

            throw new IllegalArgumentException("Invalid move");
        }
    }

    @Override
    public void attackSquare(int row, int col) {

    }
}

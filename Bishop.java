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

        super.emptyMoves();
        super.setRowPosition(rowPosition);
        super.setCowPosition(cowPosition);
    }

    @Override
    public String draw(int row, String color) {

        return color + figureDraw[row];
    }

    @Override
    public void possibleMoves() {

        addPossibleDiagonalMoves(1, 1);
        addPossibleDiagonalMoves(-1, 1);
        addPossibleDiagonalMoves(-1, -1);
        addPossibleDiagonalMoves(1, 1);

    }

    @Override
    protected void isMoveValid(int row, int col) {

        if (!super.getPossibleRowPositions().contains(row) || !super.getPossibleColPositions().contains(col)) {

            throw new IllegalArgumentException("Invalid move");
        }
    }

    private void addPossibleDiagonalMoves(int rowIncrementer, int colIncrementer) {

        for (int row = super.getRowPosition() + rowIncrementer,col = super.getColPosition() + colIncrementer;col >= 0 && row < 8 && col < 8 && row >= 0;row += rowIncrementer,col += colIncrementer){

            super.addPossibleRowPosition(row);
            super.addPossibleColPosition(col);
        }
    }
}

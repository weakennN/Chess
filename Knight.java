public class Knight extends Figure {

    static final String[] blackFigureDraw = {
            "████████NN   N█████████",
            "████████N N  N█████████",
            "████████N  N N█████████",
            "████████N   NN█████████",
            "████████N    N█████████"
    };

    static final String[] whiteFigureDraw = {
            "████████nn   n█████████",
            "████████n n  n█████████",
            "████████n  n n█████████",
            "████████n   nn█████████",
            "████████n    n█████████"
    };

    public Knight(int rowPosition, int cowPosition, FigureColor figureColor) {
        super(rowPosition, cowPosition, figureColor);
    }

    @Override
    public void move(int rowPosition, int cowPosition) {

        super.setRowPosition(rowPosition);
        super.setCowPosition(cowPosition);
        super.emptyMoves();
    }

    @Override
    public String draw(int row, String color) {

        if (super.getColor().equals(FigureColor.WHITE)) {

            return color + whiteFigureDraw[row];
        }


        return color + blackFigureDraw[row];
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

        this.move(row, col);
    }
}

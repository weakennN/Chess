public class Rook extends Figure {

    static final String[] blackFigureDraw = {
            "█████████RRRRRR████████",
            "█████████R    R████████",
            "█████████RRRRRR████████",
            "█████████R   R█████████",
            "█████████R    R████████"
    };

    static final String[] whiteFigureDraw = {
            "█████████rrrrrr████████",
            "█████████r    r████████",
            "█████████rrrrrr████████",
            "█████████r   r█████████",
            "█████████r    r████████"
    };

    public Rook(int rowPosition, int cowPosition, FigureColor figureColor) {
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

       /* super.addPossibleColPosition(super.getColPosition());
        this.possibleVerticalMoves(1);
        this.possibleVerticalMoves(-1);
        this.possibleHorizontalMoves(1);
        this.possibleHorizontalMoves(-1);

        */

    }

    @Override
    protected void isMoveValid(int row, int col) {

        if (super.getRowPosition() > row || super.getRowPosition() < row && super.getColPosition() != col) {

            throw new IllegalArgumentException("Invalid move,");
        } else if (super.getColPosition() > col || super.getColPosition() < col && super.getRowPosition() != row) {
            throw new IllegalArgumentException("Invalid move,");
        }

     /*   if (super.getRowPosition() < row && super.getColPosition() < col) {
            throw new IllegalArgumentException("Invalid move");
        }
        if (super.getRowPosition() < row && super.getColPosition() > col) {
            throw new IllegalArgumentException("Invalid move");
        }

      */
    }

    @Override
    public void attackSquare(int row, int col) {

    }

    private void possibleVerticalMoves(int incrementer) {

        for (int i = super.getRowPosition(); i < 8 && i >= 0; i += incrementer) {
            super.addPossibleRowPosition(i);
        }
    }

    private void possibleHorizontalMoves(int incrementer) {

        for (int i = super.getColPosition(); i < 8 && i >= 0; i += incrementer) {
            super.addPossibleColPosition(i);
        }
    }
}

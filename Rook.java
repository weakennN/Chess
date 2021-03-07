package Chess;

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
        AttackedSquares.removeAttackedSquares(super.getPossiblePositions());
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

        possibleVerticalMoves(1);
        possibleVerticalMoves(-1);
        possibleHorizontalMoves(1);
        possibleHorizontalMoves(-1);

    }

    @Override
    public void attackSquare(int row, int col) {

        this.move(row, col);
    }

    private void possibleVerticalMoves(int incrementer) {

        for (int i = super.getRowPosition(); i < 8 && i >= 0; i += incrementer) {
            Position position = new Position(i, super.getColPosition());
            super.addPossiblePosition(position);
            AttackedSquares.addAttackedSquares(position);
        }
    }

    private void possibleHorizontalMoves(int incrementer) {

        for (int i = super.getColPosition(); i < 8 && i >= 0; i += incrementer) {
            Position position = new Position(super.getRowPosition(), i);
            super.addPossiblePosition(position);
            AttackedSquares.addAttackedSquares(position);
        }
    }
}

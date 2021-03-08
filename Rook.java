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
    public void possibleMoves(Figure[][] figures) {

        possibleVerticalMoves(1, figures);
        possibleVerticalMoves(-1, figures);
        possibleHorizontalMoves(1, figures);
        possibleHorizontalMoves(-1, figures);

    }
    
    private void possibleVerticalMoves(int incrementer, Figure[][] figures) {

        for (int i = super.getRowPosition() + incrementer; i < 8 && i >= 0; i += incrementer) {
            Position position = new Position(i, super.getColPosition());
            super.addPossiblePosition(position);
            AttackedSquares.addAttackedSquares(position);
            if (figures[i][super.getColPosition()] != null) {

                break;
            }
        }
    }

    private void possibleHorizontalMoves(int incrementer, Figure[][] figures) {

        for (int i = super.getColPosition() + incrementer; i < 8 && i >= 0; i += incrementer) {
            Position position = new Position(super.getRowPosition(), i);
            super.addPossiblePosition(position);
            AttackedSquares.addAttackedSquares(position);

            if (figures[super.getRowPosition()][i] != null) {

                break;
            }
        }
    }
}

package Chess;

public class Rook extends Figure {

    private boolean isAbleToCastle;

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

        setAbleToCastle(true);
    }

    @Override
    public void move(int rowPosition, int cowPosition) {

        setAbleToCastle(false);
        super.setRowPosition(rowPosition);
        super.setCowPosition(cowPosition);
        AttackedSquares.removeAttackedSquares(super.getPossiblePositions(), super.getColor());
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
            AttackedSquares.addAttackedSquares(position, super.getColor());
            if (figures[i][super.getColPosition()] != null && !(figures[i][super.getColPosition()] instanceof King)) {

                break;
            }
        }
    }

    private void possibleHorizontalMoves(int incrementer, Figure[][] figures) {

        for (int i = super.getColPosition() + incrementer; i < 8 && i >= 0; i += incrementer) {
            Position position = new Position(super.getRowPosition(), i);
            super.addPossiblePosition(position);
            AttackedSquares.addAttackedSquares(position, super.getColor());

            if (figures[super.getRowPosition()][i] != null && !(figures[super.getRowPosition()][i] instanceof King)) {

                break;
            }
        }
    }

    private void setAbleToCastle(boolean isAbleToCastle) {

        this.isAbleToCastle = isAbleToCastle;
    }

    public boolean getIsAbleToCastle() {

        return this.isAbleToCastle;
    }

}

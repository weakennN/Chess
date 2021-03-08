package Chess;

public class Queen extends Figure {

    static final String[] blackFigureDraw = {
            "██████████QQQQ█████████",
            "█████████Q    Q████████",
            "█████████Q    Q████████",
            "█████████QQQQQQ████████",
            "███████████████Q███████"
    };

    static final String[] whiteFigureDraw = {
            "██████████qqqq█████████",
            "█████████q    q████████",
            "█████████q    q████████",
            "█████████qqqqqq████████",
            "███████████████q███████"
    };

    public Queen(int rowPosition, int cowPosition, FigureColor figureColor) {
        super(rowPosition, cowPosition, figureColor);
    }

    @Override
    public void move(int rowPosition, int cowPosition) {

        super.setRowPosition(rowPosition);
        super.setCowPosition(cowPosition);
        AttackedSquares.removeAttackedSquares(super.getPossiblePositions(),super.getColor());
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

        addPossibleDiagonalMoves(1, 1, figures);
        addPossibleDiagonalMoves(-1, 1, figures);
        addPossibleDiagonalMoves(-1, -1, figures);
        addPossibleDiagonalMoves(1, -1, figures);

    }

    private void possibleVerticalMoves(int incrementer, Figure[][] figures) {

        for (int i = super.getRowPosition() + incrementer; i < 8 && i >= 0; i += incrementer) {
            Position position = new Position(i, super.getColPosition());
            super.addPossiblePosition(position);
            AttackedSquares.addAttackedSquares(position,super.getColor());

            if (figures[i][super.getColPosition()] != null) {

                break;
            }
        }
    }

    private void possibleHorizontalMoves(int incrementer, Figure[][] figures) {

        for (int i = super.getColPosition() + incrementer; i < 8 && i >= 0; i += incrementer) {
            Position position = new Position(super.getRowPosition(), i);
            super.addPossiblePosition(position);
            AttackedSquares.addAttackedSquares(position,super.getColor());

            if (figures[super.getRowPosition()][i] != null) {

                break;
            }
        }
    }

    private void addPossibleDiagonalMoves(int rowIncrementer, int colIncrementer, Figure[][] figures) {

        for (int row = super.getRowPosition() + rowIncrementer, col = super.getColPosition() + colIncrementer; col >= 0 && row < 8 && col < 8 && row >= 0; row += rowIncrementer, col += colIncrementer) {

            Position position = new Position(row, col);
            super.addPossiblePosition(position);
            AttackedSquares.addAttackedSquares(position,super.getColor());

            if (figures[row][col] != null) {

                break;
            }
        }
    }
}

package Chess;

public class Bishop extends Figure {

    static final String[] blackFigureDraw = {
            "███████BBBBBB██████████",
            "███████B    B██████████",
            "███████BBBBBB██████████",
            "███████B    B██████████",
            "███████BBBBBB██████████"
    };

    static final String[] whiteFigureDraw = {
            "███████bbbbbb██████████",
            "███████b    b██████████",
            "███████bbbbbb██████████",
            "███████b    b██████████",
            "███████bbbbbb██████████"
    };

    public Bishop(int rowPosition, int cowPosition, FigureColor figureColor) {
        super(rowPosition, cowPosition, figureColor);


    }

    @Override
    public void move(int rowPosition, int cowPosition) {

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

        addPossibleDiagonalMoves(1, 1, figures);
        addPossibleDiagonalMoves(-1, 1, figures);
        addPossibleDiagonalMoves(-1, -1, figures);
        addPossibleDiagonalMoves(1, -1, figures);

    }

    private void addPossibleDiagonalMoves(int rowIncrementer, int colIncrementer, Figure[][] figures) {

        for (int row = super.getRowPosition() + rowIncrementer, col = super.getColPosition() + colIncrementer;
             col >= 0 && row < 8 && col < 8 && row >= 0;
             row += rowIncrementer, col += colIncrementer) {

            Position position = new Position(row, col);
            super.addPossiblePosition(position);
            AttackedSquares.addAttackedSquares(position, super.getColor());

            if (figures[row][col] != null && !(figures[row][col] instanceof King)) {

                break;
            }
        }
    }
}

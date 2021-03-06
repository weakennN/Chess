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

        addPossibleDiagonalMoves(1, 1);
        addPossibleDiagonalMoves(-1, 1);
        addPossibleDiagonalMoves(-1, -1);
        addPossibleDiagonalMoves(1, -1);

    }

    @Override
    public void attackSquare(int row, int col) {

        this.move(row, col);
    }
    
    private void possibleVerticalMoves(int incrementer) {

        for (int i = super.getRowPosition(); i < 8 && i >= 0; i += incrementer) {
            super.addPossiblePosition(new Position(i, super.getColPosition()));
        }
    }

    private void possibleHorizontalMoves(int incrementer) {

        for (int i = super.getColPosition(); i < 8 && i >= 0; i += incrementer) {
            super.addPossiblePosition(new Position(super.getRowPosition(), i));
        }
    }

    private void addPossibleDiagonalMoves(int rowIncrementer, int colIncrementer) {

        for (int row = super.getRowPosition() + rowIncrementer, col = super.getColPosition() + colIncrementer; col >= 0 && row < 8 && col < 8 && row >= 0; row += rowIncrementer, col += colIncrementer) {

            Position position = new Position(row, col);
            super.addPossiblePosition(position);
        }
    }
}

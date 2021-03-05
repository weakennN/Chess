package Chess;

public class Validator {

    private Figure[][] figures;

    public Validator(Figure[][] figures) {

        this.figures = figures;
    }

    public void validateRookMoves(int figureRow, int figureCol, int colToMove, int rowToMove) {

        if (figureRow < rowToMove) {

            verticalMoves(figureRow, figureCol, colToMove, rowToMove, 1);
        } else if (figureRow > rowToMove) {

            verticalMoves(figureRow, figureCol, colToMove, rowToMove, -1);
        }
    }

    private void verticalMoves(int figureRow, int figureCol, int colToMove, int rowToMove, int incrementer) {

        for (int i = figureRow + 1; i < rowToMove; i += incrementer) {

            if (figures[i][figureCol] != null) {

                throw new IllegalArgumentException("Theres a figure in the way.");
            }
        }
    }
}

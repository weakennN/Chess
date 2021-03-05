package Chess;

public class Validator {

    private Figure[][] figures;

    public Validator(Figure[][] figures) {

        this.figures = figures;
    }

    public void validateRookMoves(int figureRow, int figureCol, int colToMove, int rowToMove) {

        if (figures[figureRow][figureCol].getColor().equals(FigureColor.BLACK)) {
            if (figureRow < rowToMove) {

                verticalMovesForBlack(figureRow, figureCol, colToMove, rowToMove, 1);
            } else if (figureRow > rowToMove) {

                verticalMovesForBlack(figureRow, figureCol, colToMove, rowToMove, -1);
            } else if (figureCol > colToMove) {

                horizontalMovesForBlack(figureRow, figureCol, colToMove, rowToMove, -1);
            } else if (figureCol < colToMove) {

                horizontalMovesForBlack(figureRow, figureCol, colToMove, rowToMove, 1);
            }
        } else {

            if (figureRow < rowToMove) {

                verticalMovesForWhite(figureRow, figureCol, colToMove, rowToMove, 1);
            } else if (figureRow > rowToMove) {

                verticalMovesForWhite(figureRow, figureCol, colToMove, rowToMove, -1);
            } else if (figureCol > colToMove) {

                horizontalMovesForWhite(figureRow, figureCol, colToMove, rowToMove, -1);
            } else if (figureCol < colToMove) {

                horizontalMovesForWhite(figureRow, figureCol, colToMove, rowToMove, 1);
            }
        }
    }

    private void verticalMovesForBlack(int figureRow, int figureCol, int colToMove, int rowToMove, int incrementer) {

        for (int i = figureRow + incrementer; i < rowToMove; i += incrementer) {

            if (figures[i][figureCol] != null) {

                throw new IllegalArgumentException("Theres a figure in the way.");
            }
        }
    }

    private void verticalMovesForWhite(int figureRow, int figureCol, int colToMove, int rowToMove, int incrementer) {

        for (int i = figureRow + incrementer; i > rowToMove; i += incrementer) {

            if (figures[i][figureCol] != null) {

                throw new IllegalArgumentException("Theres a figure in the way.");
            }
        }
    }

    private void horizontalMovesForBlack(int figureRow, int figureCol, int colToMove, int rowToMove, int incrementer) {

        for (int i = figureCol + incrementer; i < colToMove; i += incrementer) {

            if (figures[figureRow][i] != null) {

                throw new IllegalArgumentException("Theres a figure in the way.");
            }
        }
    }

    private void horizontalMovesForWhite(int figureRow, int figureCol, int colToMove, int rowToMove, int incrementer) {

        for (int i = figureCol + incrementer; i > colToMove; i += incrementer) {

            if (figures[figureRow][i] != null) {

                throw new IllegalArgumentException("Theres a figure in the way.");
            }
        }
    }
}

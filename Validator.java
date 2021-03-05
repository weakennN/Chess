package Chess;

public class Validator {

    private Figure[][] figures;

    public Validator(Figure[][] figures) {

        this.figures = figures;
    }

    public void validateRookMoves(int figureRow, int figureCol, int colToMove, int rowToMove) {

        // if (figures[figureRow][figureCol].getColor().equals(FigureColor.BLACK)) {
        if (figureRow < rowToMove) {

            verticalMovesForBlack(figureRow, figureCol, colToMove, rowToMove, 1);
        } else if (figureRow > rowToMove) {

            verticalMovesForWhite(figureRow, figureCol, colToMove, rowToMove, -1);
        } else if (figureCol > colToMove) {

            horizontalMovesForWhite(figureRow, figureCol, colToMove, rowToMove, -1);
        } else if (figureCol < colToMove) {

            horizontalMovesForBlack(figureRow, figureCol, colToMove, rowToMove, 1);
        }
    }

    public void validateBishopMoves(int figureRow, int figureCol, int colToMove, int rowToMove) {


        if (figureRow > rowToMove && figureCol < colToMove) {

            diagonalMoves(figureRow, figureCol, colToMove, rowToMove, -1, 1);
        } else if (figureRow > rowToMove && figureCol > colToMove) {

            diagonalMoves(figureRow, figureCol, colToMove, rowToMove, -1, -1);
        } else if (figureRow < rowToMove && figureCol > colToMove) {

            diagonalMoves(figureRow, figureCol, colToMove, rowToMove, 1, -1);
        } else if (figureRow < rowToMove && figureCol < colToMove) {

            diagonalMoves(figureRow, figureCol, colToMove, rowToMove, 1, 1);
        }


    }

    public void diagonalMoves(int figureRow, int figureCol, int colToMove, int rowToMove, int rowIncrementer, int colIncrementer) {

        for (int i = figureRow + rowIncrementer, j = figureCol + colIncrementer; i < rowToMove && i >= 0 && j < colToMove && j >= 0; i += rowIncrementer, j += colIncrementer) {

            if (figures[i][j] != null) {

                throw new IllegalArgumentException("There is a figure in the way.");
            }
        }
    }

    private void verticalMovesForBlack(int figureRow, int figureCol, int colToMove, int rowToMove, int incrementer) {

        for (int i = figureRow + incrementer; i < rowToMove; i += incrementer) {

            if (figures[i][figureCol] != null) {

                throw new IllegalArgumentException("There is a figure in the way.");
            }
        }
    }

    private void verticalMovesForWhite(int figureRow, int figureCol, int colToMove, int rowToMove, int incrementer) {

        for (int i = figureRow + incrementer; i > rowToMove; i += incrementer) {

            if (figures[i][figureCol] != null) {

                throw new IllegalArgumentException("There is a figure in the way.");
            }
        }
    }

    private void horizontalMovesForBlack(int figureRow, int figureCol, int colToMove, int rowToMove, int incrementer) {

        for (int i = figureCol + incrementer; i < colToMove; i += incrementer) {

            if (figures[figureRow][i] != null) {

                throw new IllegalArgumentException("There is a figure in the way.");
            }
        }
    }

    private void horizontalMovesForWhite(int figureRow, int figureCol, int colToMove, int rowToMove, int incrementer) {

        for (int i = figureCol + incrementer; i > colToMove; i += incrementer) {

            if (figures[figureRow][i] != null) {

                throw new IllegalArgumentException("There is a figure in the way.");
            }
        }
    }
}

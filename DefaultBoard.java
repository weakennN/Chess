import java.util.List;

public class DefaultBoard implements IBoard {

    final int boardWidth = 8;
    final int boardHeight = 8;
    private Figure[][] figures;

    public DefaultBoard() {

        this.figures = new Figure[boardHeight][boardWidth];
    }

    @Override
    public void drawBoard() {

      /*  figures[0][0] = new Rook(0, 0);
        figures[0][1] = new Knight(0, 1);
        figures[0][2] = new Bishop(0, 2);
        figures[0][3] = new King(0, 3);
        figures[0][4] = new Queen(0, 4);
        figures[0][5] = new Bishop(0, 5);
        figures[0][6] = new Knight(0, 6);
        figures[0][7] = new Rook(0, 7);
        figures[1][0] = new Pawn(1, 0);

       */

        StringBuilder result = new StringBuilder();

        for (int row = 0; row < boardHeight; row++) {

            for (int i = 0; i < fieldHeight; i++) {

                for (int col = 0; col < boardWidth; col++) {

                    String color = "";

                    if (row % 2 == 0) {

                        if (col % 2 == 0) {
                            color = ConsoleColors.BLACK;
                        } else {
                            color = ConsoleColors.WHITE;
                        }

                    } else {

                        if (col % 2 != 0) {
                            color = ConsoleColors.BLACK;
                        } else {
                            color = ConsoleColors.WHITE;
                        }

                    }

                    if (figures[row][col] == null) {

                        result.append(color);
                        drawSquare(result);

                    } else {

                        result.append(figures[row][col].draw(i, color));
                    }


                }
                result.append("\n");
            }

        }

        System.out.println(result);

    }

    private void drawSquare(StringBuilder result) {

        for (int i = 0; i < fieldWidth; i++) {

            result.append("█");
        }
    }

    public void settFiguresPositions(List<Figure> figureList) {

        for (int i = 0; i < figureList.size(); i++) {

            figures[figureList.get(i).getRowPosition()][figureList.get(i).getColPosition()] = figureList.get(i);
        }
    }

    public void moveFigure(int row, int col, int rowToMove, int colToMove) {

        if (figures[row][col] == null) {

            throw new IllegalArgumentException("Theres no figure at this square.");
        }

        figures[row][col].possibleMoves();
        figures[row][col].isMoveValid(rowToMove, colToMove);
        figures[row][col].move(rowToMove, colToMove);
        //TODO: Make possibleMoves(); execute it self in the move();
        figures[rowToMove][colToMove] = figures[row][col];
        figures[row][col] = null;
        drawBoard();
    }
}

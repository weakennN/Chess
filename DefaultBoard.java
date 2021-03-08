package Chess;

import java.util.List;

public class DefaultBoard implements IBoard {

    final int boardWidth = 8;
    final int boardHeight = 8;
    private Figure[][] figures;
    private Validator validator;

    public DefaultBoard() {

        this.figures = new Figure[boardHeight][boardWidth];
        this.validator = new Validator(figures);

    }

    @Override
    public void drawBoard() {
        int count = 1;
        StringBuilder result = new StringBuilder();

        result.append(ConsoleColors.CYAN);
        result.append("(N) - Knight" + "\n");
        result.append("(B) - Bishop" + "\n");
        result.append("(P) - Pawn" + "\n");
        result.append("(Q) - Queen" + "\n");
        result.append("(K) - King" + "\n");
        result.append("(R) - Rook" + "\n");

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

                if (i == 2) {
                    result.append(ConsoleColors.WHITE + "     " + count++);
                }

                result.append("\n");
            }

        }

        System.out.println(result);

        for (int i = 0; i < 8; i++) {

            System.out.print(ConsoleColors.WHITE + "          " + (i + 1) + "            ");
        }
        System.out.println();
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

        try {
            King king;
            if (((King) figures[BlackKingPositions.row][BlackKingPositions.col]).getInCheck()) {

                figures[row][col].move(rowToMove, colToMove);
                figures[rowToMove][colToMove] = figures[row][col];
                figures[row][col] = null;
                if (isMoveValidAfterCheck(figures, FigureColor.WHITE)) {

                    figures[row][col] = figures[rowToMove][colToMove];
                    figures[rowToMove][colToMove] = null;
                    throw new IllegalArgumentException("Invalid move.");
                } else {

               /* figures[row][col] = figures[rowToMove][colToMove];
                figures[rowToMove][colToMove] = null;

                */

                  //  figures[rowToMove][colToMove] = figures[row][col];
                   // figures[row][col] = null;
                    ((King) figures[BlackKingPositions.row][BlackKingPositions.col]).setInCheck(false);
                    drawBoard();
                    return;
                }

            } else if (((King) figures[WhiteKingPositions.row][WhiteKingPositions.col]).getInCheck()) {


                figures[row][col].move(rowToMove, colToMove);
                figures[rowToMove][colToMove] = figures[row][col];
                figures[row][col] = null;
                if (isMoveValidAfterCheck(figures, FigureColor.BLACK)) {

                    figures[row][col] = figures[rowToMove][colToMove];
                    figures[rowToMove][colToMove] = null;
                    throw new IllegalArgumentException("Invalid move.");
                } else {


                    ((King) figures[WhiteKingPositions.row][WhiteKingPositions.col]).setInCheck(false);
                    drawBoard();
                    return;
                }
            }


            if (figures[rowToMove][colToMove] != null) {

                if (figures[rowToMove][colToMove].getColor().equals(figures[row][col].getColor())) {

                    throw new IllegalArgumentException("Invalid move.");
                }
            }

            if (figures[row][col] == null) {

                throw new IllegalArgumentException("Theres no figure at this square.");
            } else if (rowToMove >= 8 || colToMove >= 8) {

                throw new IllegalArgumentException("Invalid move");
            }

            Position position = new Position(rowToMove, colToMove);

            figures[row][col].possibleMoves(figures);
            figures[row][col].isMoveValid(position);
            figures[row][col].move(rowToMove, colToMove);

            //TODO: Make possibleMoves(); execute it self in the move();
            //TODO: create a interface with only one method which is validateMove and make every figure to implemented (maybe).
            figures[rowToMove][colToMove] = figures[row][col];
            figures[row][col] = null;

            drawBoard();
            if (isInCheck(figures[rowToMove][colToMove], (King) figures[BlackKingPositions.row][BlackKingPositions.col],
                    (King) figures[WhiteKingPositions.row][WhiteKingPositions.col])) {


                System.out.println("it works");
            }

        } catch (Exception exceptionIgnored) {

            System.out.println(exceptionIgnored.getMessage());
        }
    }

    private boolean isInCheck(Figure figure, King blackKing, King whiteKing) {

        figure.possibleMoves(figures);
        List<Position> currentFigurePossibleMoves = figure.getPossiblePositions();

        if (figure.getColor().equals(FigureColor.WHITE)) {

            for (int i = 0; i < currentFigurePossibleMoves.size(); i++) {

                if (currentFigurePossibleMoves.get(i).getRow() == BlackKingPositions.row && currentFigurePossibleMoves.get(i).getCol() == BlackKingPositions.col) {

                    try {

                        figure.validateMove(currentFigurePossibleMoves.get(i));
                    } catch (Exception exception) {

                        return false;
                    }

                    blackKing.setInCheck(true);
                    return true;
                }
            }
        } else {

            for (int i = 0; i < currentFigurePossibleMoves.size(); i++) {

                if (currentFigurePossibleMoves.get(i).getRow() == WhiteKingPositions.row && currentFigurePossibleMoves.get(i).getCol() == WhiteKingPositions.col) {

                    try {

                        figure.validateMove(currentFigurePossibleMoves.get(i));
                    } catch (Exception exception) {

                        return false;
                    }

                    whiteKing.setInCheck(true);
                    return true;
                }
            }
        }

        figure.emptyMoves();
        return false;
    }

    private boolean isMoveValidAfterCheck(Figure[][] figures, FigureColor color) {
        Position position;
        if (color.equals(FigureColor.WHITE)) {

            position = new Position(BlackKingPositions.row, BlackKingPositions.col);
        } else {

            position = new Position(WhiteKingPositions.row, WhiteKingPositions.col);
        }

        for (int row = 0; row < boardHeight; row++) {

            for (int col = 0; col < boardWidth; col++) {

                if (figures[row][col] != null) {
                    if (figures[row][col].getColor().equals(color)) {

                        figures[row][col].emptyMoves();
                        figures[row][col].possibleMoves(figures);

                        try {

                            figures[row][col].isMoveValid(position);
                            return true;
                        } catch (Exception exp) {

                        }

                    }
                }
            }

        }

        return false;
    }

    private boolean isCheckMate(King king) {


        return false;
    }


}

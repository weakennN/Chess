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

            if (figures[row][col] instanceof Rook) {

                validator.validateRookMoves(row, col, colToMove, rowToMove);
            } else if (figures[row][col] instanceof Bishop) {

                validator.validateBishopMoves(row, col, colToMove, rowToMove);
            } else if (figures[row][col] instanceof Queen) {

                validator.validateQueenMoves(row, col, colToMove, rowToMove);
            }


            if (figures[rowToMove][colToMove] != null) {

                if (figures[rowToMove][colToMove].getColor().equals(figures[row][col].getColor())) {

                    throw new IllegalArgumentException("Invalid move.");
                }

                figures[row][col].attackSquare(rowToMove, colToMove);
                figures[rowToMove][colToMove] = figures[row][col];
                figures[row][col] = null;
                drawBoard();
                return;
            }

            if (figures[row][col] == null) {

                throw new IllegalArgumentException("Theres no figure at this square.");
            } else if (rowToMove >= 8 || colToMove >= 8) {

                throw new IllegalArgumentException("Invalid move");
            }

            Position position = new Position(rowToMove, colToMove);

            figures[row][col].possibleMoves();
            figures[row][col].isMoveValid(position);
            figures[row][col].move(rowToMove, colToMove);
            //TODO: Make possibleMoves(); execute it self in the move();
            //TODO: create a interface with only one method which is validateMove and make every figure to implemented (maybe).
            //TODO: the pawn can jump though another pawn.
            //TODO: maybe remove the Validator class and and validateMove in Figure class and make so all the figures make its own.
            figures[rowToMove][colToMove] = figures[row][col];
            figures[row][col] = null;

            drawBoard();
            if (isInCheck(figures[rowToMove][colToMove], figures[BlackKingPositions.row][BlackKingPositions.col])) {

                System.out.println("it works");
            }

        } catch (Exception exceptionIgnored) {

            System.out.println(exceptionIgnored.getMessage());
        }
     }

    private boolean isInCheck(Figure figure, Figure king) {

        figure.possibleMoves();
        List<Position> currentFigurePossibleMoves = figure.getPossiblePositions();

        if (figure.getColor().equals(FigureColor.WHITE)) {

            for (int i = 0; i < currentFigurePossibleMoves.size(); i++) {

                if (currentFigurePossibleMoves.get(i).getRow() == 0 && currentFigurePossibleMoves.get(i).getCol() == 4) {

                    try {
                        if (figures[figure.getRowPosition()][figure.getColPosition()] instanceof Rook) {

                            validator.validateRookMoves(figure.getRowPosition(), figure.getColPosition(), 4, 0);
                        } else if (figures[figure.getRowPosition()][figure.getColPosition()] instanceof Bishop) {

                            validator.validateBishopMoves(figure.getRowPosition(), figure.getColPosition(), 4, 0);
                        } else if (figures[figure.getRowPosition()][figure.getColPosition()] instanceof Queen) {

                            validator.validateQueenMoves(figure.getRowPosition(), figure.getColPosition(), 4, 0);
                        }
                    } catch (Exception exception) {

                        return false;
                    }
                    return true;
                }
            }
        } else {

            for (int i = 0; i < currentFigurePossibleMoves.size(); i++) {

                if (currentFigurePossibleMoves.get(i).getRow() == 7 && currentFigurePossibleMoves.get(i).getCol() == 4) {

                    return true;
                }
            }
        }


        return false;
    }


}

package Chess;

import java.util.ArrayList;
import java.util.List;

public class DefaultBoard implements IBoard {

    final int boardWidth = 8;
    final int boardHeight = 8;
    private Figure[][] figures;
    private List<Position> possiblePositionsAfterCheck;
    private Figure checkFigure;

    public DefaultBoard() {

        this.figures = new Figure[boardHeight][boardWidth];
        this.possiblePositionsAfterCheck = new ArrayList<>();
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

        addPossiblePositionsToAllFigures(figures);
    }

    public void moveFigure(int row, int col, int rowToMove, int colToMove) {

        try {
            //Add all the possible positions of the checkFigure variable and add them to the possiblePositionsAfterCheck and then check if they are valid for the current figure the player want to move (or maybe first check if its valid and then add)
            King king;
            if (((King) figures[BlackKingPositions.row][BlackKingPositions.col]).getInCheck()) {

                if (!isMoveValidAfterCheck(rowToMove, colToMove)) {

                    throw new IllegalArgumentException("Invalid move.");
                } else {

                    figures[row][col].move(rowToMove, colToMove);
                    figures[rowToMove][colToMove] = figures[row][col];
                    figures[row][col] = null;
                    ((King) figures[BlackKingPositions.row][BlackKingPositions.col]).setInCheck(false);
                    drawBoard();
                    return;
                }

            } else if (((King) figures[WhiteKingPositions.row][WhiteKingPositions.col]).getInCheck()) {

                if (!isMoveValidAfterCheck(rowToMove, colToMove)) {


                    throw new IllegalArgumentException("Invalid move.");
                } else {

                    figures[row][col].move(rowToMove, colToMove);
                    figures[rowToMove][colToMove] = figures[row][col];
                    figures[row][col] = null;
                    ((King) figures[WhiteKingPositions.row][WhiteKingPositions.col]).setInCheck(false);
                    drawBoard();
                    return;
                }
            }

            castle(row, col, rowToMove, colToMove);

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

            figures[row][col].isMoveValid(position);
            figures[row][col].move(rowToMove, colToMove);

            //TODO: Make possibleMoves(); execute it self in the move();
            //TODO: create a interface with only one method which is validateMove and make every figure to implemented (maybe).
            //TODO: Do more testing on the isMoveValidAfterCheck method and isInCheck method.
            figures[rowToMove][colToMove] = figures[row][col];
            figures[row][col] = null;
            // addPossiblePositionsToAllFigures(figures);
         /*   if (isInCheck()){
               King kingTest;
                if (figures[rowToMove][colToMove].getColor().equals(FigureColor.WHITE)){

                    kingTest = (King) figures[WhiteKingPositions.row][WhiteKingPositions.col];
                    if (kingTest.getInCheck()){
                        this.figures[rowToMove][colToMove].move(row,col);
                        figures[row][col] = figures[rowToMove][colToMove];
                        figures[rowToMove][colToMove] = null;
                        kingTest.setInCheck(false);
                        throw new IllegalArgumentException("Invalid move");
                    }
                }else if(figures[rowToMove][colToMove].getColor().equals(FigureColor.BLACK)){

                    kingTest = (King) figures[BlackKingPositions.row][BlackKingPositions.col];

                    if (kingTest.getInCheck()){
                        this.figures[rowToMove][colToMove].move(row,col);
                        figures[row][col] = figures[row][col];
                        figures[rowToMove][colToMove] = null;
                        kingTest.setInCheck(false);
                        throw new IllegalArgumentException("Invalid move");
                    }
                }
            }

          */
            // drawBoard();
            addPossiblePositionsToAllFigures(figures);
            if (isInCheck()) {
                King kingTest;
                if (figures[rowToMove][colToMove].getColor().equals(FigureColor.WHITE)) {
                    //needs more testing
                    kingTest = (King) figures[WhiteKingPositions.row][WhiteKingPositions.col];
                    if (kingTest.getInCheck()) {
                        this.figures[rowToMove][colToMove].move(row, col);
                        figures[row][col] = figures[rowToMove][colToMove];
                        figures[rowToMove][colToMove] = null;
                        kingTest.setInCheck(false);
                        this.figures[row][col].possibleMoves(figures);
                        throw new IllegalArgumentException("Invalid move");
                    }
                } else if (figures[rowToMove][colToMove].getColor().equals(FigureColor.BLACK)) {

                    kingTest = (King) figures[BlackKingPositions.row][BlackKingPositions.col];

                    if (kingTest.getInCheck()) {
                        this.figures[rowToMove][colToMove].move(row, col);
                        figures[row][col] = figures[rowToMove][colToMove];
                        figures[rowToMove][colToMove] = null;
                        this.figures[row][col].possibleMoves(figures);
                        kingTest.setInCheck(false);
                        throw new IllegalArgumentException("Invalid move");
                    }
                }
                addPossiblePositionsAfterCheck();
                System.out.println("it works");
                isCheckMate();
            }

            drawBoard();

        } catch (Exception exceptionIgnored) {

            System.out.println(exceptionIgnored.getMessage());
        }


    }

    private void addPossiblePositionsToAllFigures(Figure[][] figures) {

        for (int row = 0; row < boardHeight; row++) {

            for (int col = 0; col < boardWidth; col++) {

                if (figures[row][col] != null) {

                    if (figures[row][col].getPossiblePositions().size() > 1) {

                        figures[row][col].removeAttackedSquares();
                        figures[row][col].emptyMoves();
                    }
                    figures[row][col].possibleMoves(figures);
                    test(figures[row][col].getPossiblePositions(), figures[row][col].getColor(), figures[row][col]);
                }
            }
        }
    }

    private void test(List<Position> positions, FigureColor figureColor, Figure figure) {
        King king;
        if (figure instanceof King) {

            return;
        }
        if (figureColor.equals(FigureColor.BLACK)) {

            king = (King) figures[WhiteKingPositions.row][WhiteKingPositions.col];
        } else {

            king = (King) figures[BlackKingPositions.row][BlackKingPositions.col];
        }

        for (int i = 0; i < positions.size(); i++) {

            if (positions.get(i).getRow() == king.getRowPosition() && positions.get(i).getCol() == king.getColPosition()) {

                checkFigure = figure;
            }
        }

    }

    private boolean isInCheck() {

        List<Position> positions = AttackedSquares.getWhiteAttackedSquares();

        for (int i = 0; i < positions.size(); i++) {

            if (positions.get(i).getRow() == BlackKingPositions.row && positions.get(i).getCol() == BlackKingPositions.col) {

                King king = (King) figures[BlackKingPositions.row][BlackKingPositions.col];
                king.setInCheck(true);
                return true;
            }
        }

        List<Position> positions1 = AttackedSquares.getBlackAttackedSquares();

        for (int i = 0; i < positions1.size(); i++) {

            if (positions1.get(i).getRow() == WhiteKingPositions.row && positions1.get(i).getCol() == WhiteKingPositions.col) {

                King king = (King) figures[WhiteKingPositions.row][WhiteKingPositions.col];
                king.setInCheck(true);
                return true;
            }
        }

        return false;
    }

    private boolean isMoveValidAfterCheck(int rowToMove, int colToMove) {

        for (int i = 0; i < this.possiblePositionsAfterCheck.size(); i++) {

            if (this.possiblePositionsAfterCheck.get(i).getRow() == rowToMove && this.possiblePositionsAfterCheck.get(i).getCol() == colToMove) {
                return true;
            }
        }

        return false;
    }

    private boolean isCheckMate() {

        King king1 = (King) figures[BlackKingPositions.row][BlackKingPositions.col];
        King king2 = (King) figures[WhiteKingPositions.row][WhiteKingPositions.col];
        List<Position> attackedPositions;
        if (king1.getInCheck()) {


            attackedPositions = AttackedSquares.getWhiteAttackedSquares();
        } else {

            attackedPositions = AttackedSquares.getBlackAttackedSquares();
        }

        for (int i = 0; i < this.possiblePositionsAfterCheck.size(); i++) {

            if (this.possiblePositionsAfterCheck.get(i).getRow() < 0 || this.possiblePositionsAfterCheck.get(i).getCol() < 0
                    || this.possiblePositionsAfterCheck.get(i).getRow() > 7 || this.possiblePositionsAfterCheck.get(i).getCol() > 7) {

                this.possiblePositionsAfterCheck.remove(this.possiblePositionsAfterCheck.get(i));
                i -= 1;
            }

        }

        for (int i = 0; i < this.possiblePositionsAfterCheck.size(); i++) {

            for (int j = 0; j < attackedPositions.size(); j++) {

                if (this.possiblePositionsAfterCheck.get(i).getRow() == attackedPositions.get(j).getRow() && this.possiblePositionsAfterCheck.get(i).getCol() == attackedPositions.get(j).getCol()) {

                    this.possiblePositionsAfterCheck.remove(this.possiblePositionsAfterCheck.get(i));
                    i -= 1;
                }
            }
        }

        if (this.possiblePositionsAfterCheck.size() == 0) {

            System.out.println("check mate");
            drawBoard();
            System.exit(0);
        }

        return false;
    }

    private void addPossiblePositionsAfterCheck() {
        King king1 = (King) figures[BlackKingPositions.row][BlackKingPositions.col];
        King king2 = (King) figures[WhiteKingPositions.row][WhiteKingPositions.col];
        King king;
        List<Position> attackedPositions;
        if (king1.getInCheck()) {

            king = king1;
            attackedPositions = AttackedSquares.getWhiteAttackedSquares();
        } else {

            king = king2;
            attackedPositions = AttackedSquares.getBlackAttackedSquares();
        }

        boolean flag = true;
        if (king.getInCheck()) {

            List<Position> possiblePositions = king.getPossiblePositions();

            for (int i = 0; i < possiblePositions.size(); i++) {

                for (int j = 0; j < attackedPositions.size(); j++) {

                    if (attackedPositions.get(j).getRow() == possiblePositions.get(i).getRow() && attackedPositions.get(j).getCol() == possiblePositions.get(i).getCol()) {

                        flag = false;
                        break;
                    }
                }

                if (flag) {

                    this.possiblePositionsAfterCheck.add(new Position(possiblePositions.get(i).getRow(), possiblePositions.get(i).getCol()));
                } else {

                    flag = true;
                }
            }

            for (int i = 0; i < this.checkFigure.getPossiblePositions().size(); i++) {


                for (int row = 0; row < boardHeight; row++) {

                    for (int col = 0; col < boardWidth; col++) {

                        if (figures[row][col] != null) {
                            if (figures[row][col].getColor().equals(king.getColor())) {
                                try {
                                    figures[row][col].isMoveValid(new Position(checkFigure.getPossiblePositions().get(i).getRow(), this.checkFigure.getPossiblePositions().get(i).getCol()));
                                } catch (Exception ignored) {

                                    continue;
                                }
                                if (figures[checkFigure.getPossiblePositions().get(i).getRow()][this.checkFigure.getPossiblePositions().get(i).getCol()] != null) {

                                    continue;
                                }
                                this.figures[row][col].move(checkFigure.getPossiblePositions().get(i).getRow(), this.checkFigure.getPossiblePositions().get(i).getCol());
                                figures[checkFigure.getPossiblePositions().get(i).getRow()][this.checkFigure.getPossiblePositions().get(i).getCol()] = figures[row][col];
                                figures[row][col] = null;
                                if (isInCheck()) {

                                } else {
                                    this.possiblePositionsAfterCheck.add((new Position(checkFigure.getPossiblePositions().get(i).getRow(), this.checkFigure.getPossiblePositions().get(i).getCol())));
                                }
                                figures[checkFigure.getPossiblePositions().get(i).getRow()][this.checkFigure.getPossiblePositions().get(i).getCol()].move(row, col);
                                figures[row][col] = figures[checkFigure.getPossiblePositions().get(i).getRow()][this.checkFigure.getPossiblePositions().get(i).getCol()];
                                figures[checkFigure.getPossiblePositions().get(i).getRow()][this.checkFigure.getPossiblePositions().get(i).getCol()] = null;

                            }
                        }
                    }

                }

            }
            System.out.println();
        }
    }

    public Figure[][] getFigures() {

        return this.figures;
    }

    private void castle(int row, int col, int rowToMove, int colToMove) {

//TODO: add more validations more methods so you dont copy paste
        if (row != WhiteKingPositions.row && col != WhiteKingPositions.col
                && row != BlackKingPositions.row && col != BlackKingPositions.col) {
            return;
        }

        if (figures[row][col].getColor().equals(figures[rowToMove][colToMove].getColor())) {

            if (figures[row][col] instanceof King || figures[row][col] instanceof Rook &&
                    figures[rowToMove][colToMove] instanceof King ||
                    figures[rowToMove][colToMove] instanceof Rook) {

                King king;
                Rook rook;

                if (figures[row][col].getColor().equals(FigureColor.BLACK)) {

                    if (row == BlackKingPositions.row && col == BlackKingPositions.col) {

                        king = (King) figures[row][col];
                        rook = (Rook) figures[rowToMove][colToMove];
                    } else {

                        king = (King) figures[rowToMove][colToMove];
                        rook = (Rook) figures[row][col];
                    }

                    if (rook.getColPosition() > 0) {

                        if (!validateCastle(row, col, rowToMove, colToMove, 1, king.getRowPosition(), king.getColPosition())) {

                            throw new IllegalArgumentException("Invalid move");
                        } else {

                            swapFiguresForCastle(2, -1, king, rook);
                            drawBoard();
                        }
                    } else {

                        if (!validateCastle(row, col, rowToMove, colToMove, -1, king.getRowPosition(), king.getColPosition())) {

                            throw new IllegalArgumentException("Invalid move");
                        } else {

                            swapFiguresForCastle(-2, 1, king, rook);
                            drawBoard();
                        }
                    }

                } else if (figures[row][col].getColor().equals(FigureColor.WHITE)) {

                    if (row == WhiteKingPositions.row && col == WhiteKingPositions.col) {

                        king = (King) figures[row][col];
                        rook = (Rook) figures[rowToMove][colToMove];
                    } else {

                        king = (King) figures[rowToMove][colToMove];
                        rook = (Rook) figures[row][col];
                    }

                    if (rook.getColPosition() > 0) {

                        if (!validateCastle(row, col, rowToMove, colToMove, 1, king.getRowPosition(), king.getColPosition())) {

                            throw new IllegalArgumentException("Invalid move");
                        } else {

                            swapFiguresForCastle(2, -1, king, rook);
                            drawBoard();
                        }
                    } else {

                        if (!validateCastle(row, col, rowToMove, colToMove, -1, king.getRowPosition(), king.getColPosition())) {

                            throw new IllegalArgumentException("Invalid move");
                        } else {

                            swapFiguresForCastle(-2, 1, king, rook);
                            drawBoard();
                        }
                    }
                }
            }
        }
    }

    public boolean validateCastle(int row, int col, int rowToMove, int colToMove, int incrementer, int kingRow, int kingCol) {

        for (int i = kingCol + incrementer; i >= 1 && i < 7; i += incrementer) {

            if (figures[kingRow][i] != null) {

                return false;
            }
        }

        return true;
    }

    private void swapFiguresForCastle(int kingColIncrementer, int rookColIncrementer, King king, Rook rook) {

        figures[king.getRowPosition()][king.getColPosition() + kingColIncrementer] = figures[king.getRowPosition()][king.getColPosition()];
        figures[king.getRowPosition()][king.getColPosition()] = null;
        king.move(king.getRowPosition(), king.getColPosition() + kingColIncrementer);
        figures[rook.getRowPosition()][king.getColPosition() + rookColIncrementer] = figures[rook.getRowPosition()][rook.getColPosition()];
        figures[rook.getRowPosition()][rook.getColPosition()] = null;
        rook.move(rook.getRowPosition(), king.getColPosition() + rookColIncrementer);
    }

}

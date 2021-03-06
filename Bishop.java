package Chess;

import java.util.ArrayList;

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

        addPossibleDiagonalMoves(1, 1);
        addPossibleDiagonalMoves(-1, 1);
        addPossibleDiagonalMoves(-1, -1);
        addPossibleDiagonalMoves(1, -1);

    }


    @Override
    protected void isMoveValid(Position position) {

        if (!super.validateMove(position)) {

            throw new IllegalArgumentException("Invalid move!!!!!!!!!");
        }
    }

    @Override
    public void attackSquare(int row, int col) {

    }

    private void addPossibleDiagonalMoves(int rowIncrementer, int colIncrementer) {

        for (int row = super.getRowPosition() + rowIncrementer, col = super.getColPosition() + colIncrementer; col >= 0 && row < 8 && col < 8 && row >= 0; row += rowIncrementer, col += colIncrementer) {
            
            Position position = new Position(row, col);
            super.addPossiblePosition(position);
        }
    }
}

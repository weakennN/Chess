package Chess;

public class Knight extends Figure {

    static final String[] blackFigureDraw = {
            "████████NN   N█████████",
            "████████N N  N█████████",
            "████████N  N N█████████",
            "████████N   NN█████████",
            "████████N    N█████████"
    };

    static final String[] whiteFigureDraw = {
            "████████nn   n█████████",
            "████████n n  n█████████",
            "████████n  n n█████████",
            "████████n   nn█████████",
            "████████n    n█████████"
    };

    public Knight(int rowPosition, int cowPosition, FigureColor figureColor) {
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

        super.addPossiblePosition(new Position(super.getRowPosition() + 2, super.getColPosition() + 1));
        super.addPossiblePosition(new Position(super.getRowPosition() + 2, super.getColPosition() - 1));
        super.addPossiblePosition(new Position(super.getRowPosition() - 1, super.getColPosition() + 2));
        super.addPossiblePosition(new Position(super.getRowPosition() - 1, super.getColPosition() - 2));
        super.addPossiblePosition(new Position(super.getRowPosition() + 1, super.getColPosition() + 2));
        super.addPossiblePosition(new Position(super.getRowPosition() + 1, super.getColPosition() - 2));
        super.addPossiblePosition(new Position(super.getRowPosition() - 2, super.getColPosition() + 1));
        super.addPossiblePosition(new Position(super.getRowPosition() - 2, super.getColPosition() - 1));

        for (int i = 0; i < super.getPossiblePositions().size(); i++) {

            AttackedSquares.addAttackedSquares(super.getPossiblePositions().get(i), super.getColor());
        }
    }

}

package Chess;

public class King extends Figure {

    private boolean isInCheck;
    static final String[] blackFigureDraw = {
            "█████████K  K██████████",
            "█████████K K███████████",
            "█████████KK████████████",
            "█████████K K███████████",
            "█████████K  K██████████"
    };

    static final String[] whiteFigureDraw = {
            "█████████k  k██████████",
            "█████████k k███████████",
            "█████████kk████████████",
            "█████████k k███████████",
            "█████████k  k██████████"
    };

    public King(int rowPosition, int cowPosition, FigureColor figureColor) {
        super(rowPosition, cowPosition, figureColor);

        this.isInCheck = false;
    }

    @Override
    public void move(int rowPosition, int cowPosition) {

        super.setRowPosition(rowPosition);
        super.setCowPosition(cowPosition);

        if (super.getColor().equals(FigureColor.BLACK)) {

            BlackKingPositions.row = super.getRowPosition();
            BlackKingPositions.col = super.getColPosition();
        } else {

            WhiteKingPositions.row = super.getRowPosition();
            WhiteKingPositions.col = super.getColPosition();
        }

        AttackedSquares.removeAttackedSquares(super.getPossiblePositions(),super.getColor());
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

        Position position = new Position(super.getRowPosition() + 1, super.getColPosition() + 1);
        Position position1 = new Position(super.getRowPosition() + 1, super.getColPosition() - 1);
        Position position2 = new Position(super.getRowPosition() + 1, super.getColPosition());
        Position position3 = new Position(super.getRowPosition() - 1, super.getColPosition() + 1);
        Position position4 = new Position(super.getRowPosition() - 1, super.getColPosition());
        Position position5 = new Position(super.getRowPosition() - 1, super.getColPosition() - 1);
        Position position6 = new Position(super.getRowPosition(), super.getColPosition() + 1);
        Position position7 = new Position(super.getRowPosition(), super.getColPosition() - 1);

        super.addPossiblePosition(position);
        super.addPossiblePosition(position1);
        super.addPossiblePosition(position2);
        super.addPossiblePosition(position3);
        super.addPossiblePosition(position4);
        super.addPossiblePosition(position5);
        super.addPossiblePosition(position6);
        super.addPossiblePosition(position7);

        for (int i = 0; i < super.getPossiblePositions().size(); i++) {

            AttackedSquares.addAttackedSquares(super.getPossiblePositions().get(i),super.getColor());

        }

    }

    public void setInCheck(boolean inCheck) {

        this.isInCheck = inCheck;
    }

    public boolean getInCheck() {

        return this.isInCheck;
    }
}

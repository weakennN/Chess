package Chess;

public class King extends Figure {

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

    }

    @Override
    protected void isMoveValid(Position position) {

        if (!super.validateMove(position)) {

            throw new IllegalArgumentException("Invalid move!!!!!");
        }
    }

    @Override
    public void attackSquare(int row, int col) {

        this.move(row, col);
    }
}

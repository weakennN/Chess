package Chess;


import java.util.List;

public abstract class Figure {

    private int rowPosition;
    private int colPosition;
    private FigureColor figureColor;
    private PossiblePositions possiblePositions;

    public Figure(int rowPosition, int cowPosition, FigureColor figureColor) {

        setRowPosition(rowPosition);
        setCowPosition(cowPosition);
        setFigureColor(figureColor);
        this.possiblePositions = new PossiblePositions();
    }

    public void setRowPosition(int rowPosition) {
        this.rowPosition = rowPosition;
    }

    public void setCowPosition(int colPosition) {
        this.colPosition = colPosition;
    }

    public abstract void move(int rowPosition, int colPosition);

    public String draw(int row, String color) {


        return "";
    }

    public int getRowPosition() {
        return rowPosition;
    }

    public int getColPosition() {
        return colPosition;
    }

    protected void addPossiblePosition(Position position) {

        this.possiblePositions.addPosition(position);
    }

    public abstract void possibleMoves(Figure[][] figures);

    protected void isMoveValid(Position position) {

        if (!this.validateMove(position)) {

            throw new IllegalArgumentException("Invalid move!!");
        }
    }

    protected void emptyMoves() {

        this.possiblePositions.getPositionList().clear();
    }

    public abstract void attackSquare(int row, int col);

    public void setFigureColor(FigureColor figureColor) {
        this.figureColor = figureColor;
    }

    public FigureColor getColor() {

        return this.figureColor;
    }

    public List<Position> getPossiblePositions() {
        return possiblePositions.getPositionList();
    }

    public boolean validateMove(Position position) {

        List<Position> positionList = this.possiblePositions.getPositionList();

        for (int i = 0; i < positionList.size(); i++) {

            if (positionList.get(i).getRow() == position.getRow() && positionList.get(i).getCol() == position.getCol()) {

                return true;
            }
        }

        return false;
    }
}

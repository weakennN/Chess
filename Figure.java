package Chess;


import java.util.List;

public abstract class Figure {

    private int rowPosition;
    private int colPosition;
    private FigureColor figureColor;
    private PossiblePosition possiblePosition;

    public Figure(int rowPosition, int cowPosition, FigureColor figureColor) {

        setRowPosition(rowPosition);
        setCowPosition(cowPosition);
        setFigureColor(figureColor);
        this.possiblePosition = new PossiblePosition();
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

        this.possiblePosition.addPosition(position);
    }

    public abstract void possibleMoves();

    protected abstract void isMoveValid(Position position);

    protected void emptyMoves() {

        this.possiblePosition.getPositionList().clear();
    }

    public abstract void attackSquare(int row, int col);

    public void setFigureColor(FigureColor figureColor) {
        this.figureColor = figureColor;
    }

    public FigureColor getColor() {

        return this.figureColor;
    }

    public boolean validateMove(Position position) {

        List<Position> positionList = this.possiblePosition.getPositionList();

        for (int i = 0; i < positionList.size(); i++) {

            if (positionList.get(i).getRow() == position.getRow() && positionList.get(i).getCol() == position.getCol()) {

                return true;
            }
        }

        return false;
    }
}

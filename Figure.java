package Chess;
import java.util.ArrayList;
import java.util.List;

public abstract class Figure {

    private int rowPosition;
    private int colPosition;
    private List<Integer> possibleRowPositions;
    private List<Integer> possibleColPositions;
    private FigureColor figureColor;

    public Figure(int rowPosition, int cowPosition, FigureColor figureColor) {

        setRowPosition(rowPosition);
        setCowPosition(cowPosition);
        setFigureColor(figureColor);
        this.possibleRowPositions = new ArrayList<>();
        this.possibleColPositions = new ArrayList<>();
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

    protected void addPossibleRowPosition(int row) {

        this.possibleRowPositions.add(row);
    }

    protected void addPossibleColPosition(int col) {

        this.possibleColPositions.add(col);
    }

    public List<Integer> getPossibleRowPositions() {
        return possibleRowPositions;
    }

    public List<Integer> getPossibleColPositions() {
        return possibleColPositions;
    }

    public abstract void possibleMoves();

    protected abstract void isMoveValid(int row, int col);

    protected void emptyMoves() {

        this.possibleRowPositions.clear();
        this.possibleColPositions.clear();
    }

    public abstract void attackSquare(int row, int col);

    public void setFigureColor(FigureColor figureColor) {
        this.figureColor = figureColor;
    }

    public FigureColor getColor() {

        return this.figureColor;
    }
}

public abstract class Figure {

    private int rowPosition;
    private int cowPosition;

    public Figure(int rowPosition, int cowPosition) {

        setRowPosition(rowPosition);
        setCowPosition(cowPosition);
    }

    public void setRowPosition(int rowPosition) {
        this.rowPosition = rowPosition;
    }

    public void setCowPosition(int cowPosition) {
        this.cowPosition = cowPosition;
    }

    public abstract void move(int rowPosition, int cowPosition);

    public String draw(int row, String color) {


        return "";
    }
}

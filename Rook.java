public class Rook extends Figure {

    static final String[] figureDraw = {
            "█████████RRRRRR████████",
            "█████████R    R████████",
            "█████████RRRRRR████████",
            "█████████R   R█████████",
            "█████████R    R████████"
    };

    public Rook(int rowPosition, int cowPosition) {
        super(rowPosition, cowPosition);
    }

    @Override
    public void move(int rowPosition, int cowPosition) {

        super.setRowPosition(rowPosition);
        super.setCowPosition(cowPosition);
        super.emptyMoves();
    }

    @Override
    public String draw(int row, String color) {

        return color + figureDraw[row];
    }

    @Override
    public void possibleMoves() {
        super.addPossibleColPosition(super.getCowPosition());
        this.possibleVerticalMoves(1);
        this.possibleVerticalMoves(-1);
        this.possibleHorizontalMoves(1);
        this.possibleHorizontalMoves(-1);
        //TODO: horizontalPossibleMoves.
    }

    @Override
    protected void isMoveValid(int row, int col) {

    }

    private void possibleVerticalMoves(int incrementer) {

        for (int i = super.getRowPosition(); i < 8 && i >= 0; i += incrementer) {
            super.addPossibleRowPosition(i);
        }
    }

    private void possibleHorizontalMoves(int incrementer) {

        for (int i = super.getCowPosition(); i < 8 && i >= 0; i += incrementer) {
            super.addPossibleColPosition(i);
        }
    }
}

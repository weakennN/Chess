package Chess;

public class Pawn extends Figure {

    static final String[] blackFigureDraw = {
            "███████PPPPPPPPP███████",
            "███████P       P███████",
            "███████PPPPPPPPP███████",
            "███████P███████████████",
            "███████P███████████████"
    };

    static final String[] whiteFigureDraw = {
            "███████ppppppppp███████",
            "███████p       p███████",
            "███████ppppppppp███████",
            "███████p███████████████",
            "███████p███████████████"
    };

    private boolean firstMove;

    public Pawn(int rowPosition, int cowPosition, FigureColor figureColor) {
        super(rowPosition, cowPosition, figureColor);

        this.firstMove = false;
    }

    @Override
    public void move(int rowPosition, int cowPosition) {

        isMoveValid(rowPosition, cowPosition);
        this.firstMove = true;
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

        if (!this.firstMove) {
            super.addPossibleRowPosition(super.getRowPosition() + 2);
            super.addPossibleColPosition(super.getColPosition());
        } else {
            super.addPossibleRowPosition(super.getRowPosition() + 1);
        }

    }

    @Override
    protected void isMoveValid(int row, int col) {

        if (!super.getPossibleRowPositions().contains(row) || !super.getPossibleColPositions().contains(col)) {

            throw new IllegalArgumentException("Invalid move.");
        }

    }

    @Override
    public void attackSquare(int rowPosition, int cowPosition) {

        if (rowPosition - 1 == super.getRowPosition() || rowPosition + 1 == super.getRowPosition()) {

            if (super.getColPosition() - 1 == cowPosition || super.getColPosition() + 1 == cowPosition) {

                super.setCowPosition(cowPosition);
                super.setRowPosition(rowPosition);
                return;
            }
        }

        throw new IllegalArgumentException("Invalid move.");
    }
}

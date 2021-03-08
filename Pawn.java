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
    public void possibleMoves(Figure[][] figures) {

        if (super.getColor().equals(FigureColor.BLACK)) {

            if (!this.firstMove) {

                if (figures[super.getRowPosition() + 1][super.getColPosition()] == null) {
                    super.addPossiblePosition(new Position(super.getRowPosition() + 2, super.getColPosition()));
                }
                super.addPossiblePosition(new Position(super.getRowPosition() + 1, super.getColPosition()));

            } else {
                super.addPossiblePosition(new Position(super.getRowPosition() + 1, super.getColPosition()));

            }

            if (figures[super.getRowPosition() + 1][super.getColPosition() + 1] != null) {

                super.addPossiblePosition(new Position(super.getRowPosition() + 1, super.getColPosition() + 1));
            }
            if (figures[super.getRowPosition() + 1][super.getColPosition() - 1] != null) {

                super.addPossiblePosition(new Position(super.getRowPosition() + 1, super.getColPosition() - 1));
            }
        } else {

            if (!this.firstMove) {
                if (figures[super.getRowPosition() - 1][super.getColPosition()] == null) {
                    super.addPossiblePosition(new Position(super.getRowPosition() - 2, super.getColPosition()));
                }
                super.addPossiblePosition(new Position(super.getRowPosition() - 1, super.getColPosition()));

            } else {

                super.addPossiblePosition(new Position(super.getRowPosition() - 1, super.getColPosition()));
            }

            if (figures[super.getRowPosition() - 1][super.getColPosition() + 1] != null) {

                super.addPossiblePosition(new Position(super.getRowPosition() - 1, super.getColPosition() + 1));
            }
            if (figures[super.getRowPosition() - 1][super.getColPosition() - 1] != null) {

                super.addPossiblePosition(new Position(super.getRowPosition() - 1, super.getColPosition() - 1));
            }
        }
    }

}

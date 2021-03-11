package Chess;

public class Game {

    private Board board;
    private Player[] players;
    private DefaultBoard defaultBoard;

    public Game(Board board, Player[] players, DefaultBoard defaultBoard) {

        this.board = board;
        this.players = players;
        this.defaultBoard = defaultBoard;
    }

    public void play(int figureRow, int figureCol, int rowToMove, int colToMove) {

        if (players[0].getTurn()) {
            if (defaultBoard.getFigures()[figureRow][figureCol].getColor().equals(FigureColor.BLACK)){

                throw new IllegalArgumentException("White is on move.");
            }
            players[0].setTurn(false);
            players[1].setTurn(true);
        } else {
            if (defaultBoard.getFigures()[figureRow][figureCol].getColor().equals(FigureColor.WHITE)){

                throw new IllegalArgumentException("Black is on move.");
            }
            players[1].setTurn(false);
            players[0].setTurn(true);
        }

        defaultBoard.moveFigure(figureRow, figureCol, rowToMove, colToMove);
    }
}

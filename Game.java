public class Game {

    private Board board;
    private Player[] players;
    private DefaultBoard defaultBoard;

    public Game(Board board, Player[] players, DefaultBoard defaultBoard) {

        this.board = board;
        this.players = players;
        this.defaultBoard = defaultBoard;
    }

    public void play(int figureRow,int figureCol,int rowToMove,int colToMove) {

        defaultBoard.moveFigure(figureRow,figureCol,rowToMove,colToMove);
    }
}

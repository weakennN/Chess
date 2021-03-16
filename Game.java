package Chess;

public class Game {

    private Board board;
    private Player[] players;
    private DefaultBoard defaultBoard;
    public static boolean GameOver = false;

    public Game(Board board, Player[] players, DefaultBoard defaultBoard) {

        this.board = board;
        this.players = players;
        this.defaultBoard = defaultBoard;
    }

    public void play(int figureRow, int figureCol, int rowToMove, int colToMove) {
        try {

            Player player;
            Player player1;

            if (defaultBoard.getFigures()[figureRow][figureCol] == null) {

                throw new IllegalArgumentException("Invalid move");
            }

            if (players[0].getTurn()) {

                if (defaultBoard.getFigures()[figureRow][figureCol].getColor().equals(FigureColor.BLACK)) {

                    throw new IllegalArgumentException("White is on move.");
                }

                player = players[0];
                player1 = players[1];
            } else {

                if (defaultBoard.getFigures()[figureRow][figureCol].getColor().equals(FigureColor.WHITE)) {

                    throw new IllegalArgumentException("Black is on move.");
                }

                player = players[1];
                player1 = players[0];
            }

            if (defaultBoard.moveFigure(figureRow, figureCol, rowToMove, colToMove)) {

                player.setTurn(false);
                player1.setTurn(true);
            }

        } catch (Exception ignored) {

            System.out.println(ignored.getMessage());
        }
    }


}

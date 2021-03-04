import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Rook rook = new Rook(0, 0, FigureColor.BLACK);
        Rook rook2 = new Rook(7, 7, FigureColor.WHITE);
        Rook rook3 = new Rook(7, 0, FigureColor.WHITE);
        Rook rook1 = new Rook(0, 7, FigureColor.BLACK);
        King king = new King(0, 4, FigureColor.BLACK);
        Queen queen = new Queen(0, 3, FigureColor.BLACK);
        List<Figure> figures = new ArrayList<>();
        for (int i = 0; i < 8; i++) {

            Pawn pawn = new Pawn(1, i, FigureColor.BLACK);
            figures.add(pawn);
        }
        for (int i = 7; i >= 0; i--) {

            Pawn pawn = new Pawn(6, i, FigureColor.WHITE);
            figures.add(pawn);
        }

        Player[] players = new Player[2];
        players[0] = new Player(true, "Pesho");
        players[1] = new Player(false, "Pencho");
        figures.add(rook);
        figures.add(rook3);
        figures.add(rook2);
        figures.add(rook1);
        figures.add(king);
        figures.add(queen);
        Knight knight = new Knight(0, 1, FigureColor.BLACK);
        Knight knight1 = new Knight(0, 6, FigureColor.BLACK);
        Knight knight2 = new Knight(7, 1, FigureColor.WHITE);
        Knight knight3 = new Knight(7, 6, FigureColor.WHITE);
        figures.add(knight2);
        figures.add(knight3);
        figures.add(knight);
        figures.add(knight1);
        Bishop bishop = new Bishop(0, 2, FigureColor.BLACK);
        Bishop bishop1 = new Bishop(0, 5, FigureColor.BLACK);
        figures.add(bishop);
        figures.add(bishop1);

        DefaultBoard defaultBoard = new DefaultBoard();
        defaultBoard.settFiguresPositions(figures);
        Board board = new Board(figures);
        Game game = new Game(board, players, defaultBoard);
        defaultBoard.drawBoard();

        for (int i = 0; i < 10; i++) {

            System.out.print("Type your move: ");
            String com = scan.nextLine();
            String[] tokens = com.split("\\s+");
            int figureRow = Integer.parseInt(tokens[0]);
            int figureCol = Integer.parseInt(tokens[1]);
            int rowToMove = Integer.parseInt(tokens[2]);
            int colToMove = Integer.parseInt(tokens[3]);
            game.play(figureRow - 1, figureCol - 1, rowToMove - 1, colToMove - 1);
        }
    }
}

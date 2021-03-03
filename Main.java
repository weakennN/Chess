import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Rook rook = new Rook(0, 0);
        Rook rook1 = new Rook(0, 7);
        King king = new King(0, 4);
        Queen queen = new Queen(0, 3);
        List<Figure> figures = new ArrayList<>();
        for (int i = 0;i < 8;i++){

            Pawn pawn = new Pawn(1,i);
            figures.add(pawn);
        }
        figures.add(rook);
        figures.add(rook1);
        figures.add(king);
        figures.add(queen);
        Knight knight = new Knight(0, 1);
        Knight knight1 = new Knight(0, 6);
        figures.add(knight);
        figures.add(knight1);
        Bishop bishop = new Bishop(0, 2);
        Bishop bishop1 = new Bishop(0, 5);
        figures.add(bishop);
        figures.add(bishop1);

        DefaultBoard defaultBoard = new DefaultBoard();
        defaultBoard.settFiguresPositions(figures);
        Board board = new Board(figures);
        Game game = new Game(board,null,defaultBoard);
        defaultBoard.drawBoard();

        for (int i = 0;i < 10;i++){

            System.out.print("Type your move: ");
            String com = scan.nextLine();
            String[] tokens = com.split("\\s+");
            int figureRow = Integer.parseInt(tokens[0]);
            int figureCol = Integer.parseInt(tokens[1]);
            int rowToMove = Integer.parseInt(tokens[2]);
            int colToMove = Integer.parseInt(tokens[3]);
            game.play(figureRow,figureCol,rowToMove,colToMove);
        }
    }
}

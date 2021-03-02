import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Rook rook = new Rook(0, 0);
        DefaultBoard defaultBoard = new DefaultBoard();
        List<Figure> figures = new ArrayList<>(List.of(rook));
        defaultBoard.settFiguresPositions(figures);
        defaultBoard.drawBoard();

        King king = new King(0, 4);
        Queen queen = new Queen(0, 3);
        System.out.println();

        Board board = new Board(figures);

        /*figures[0][1] = new Knight(0, 1);
        figures[0][2] = new Bishop(0, 2);
        figures[0][3] = new King(0, 3);
        figures[0][4] = new Queen(0, 4);
        figures[0][5] = new Bishop(0, 5);
        figures[0][6] = new Knight(0, 6);
        figures[0][7] = new Rook(0, 7);
        figures[1][0] = new Pawn(1, 0);

         */
    }
}

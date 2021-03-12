package Chess;

import java.util.ArrayList;
import java.util.List;

public class AttackedSquares {

    private static List<Position> blackAttackedSquares = new ArrayList<>();
    private static List<Position> whiteAttackedSquares = new ArrayList<>();


    public static void addAttackedSquares(Position position, FigureColor color) {

        if (color.equals(FigureColor.BLACK)) {
            blackAttackedSquares.add(position);
        } else {
            whiteAttackedSquares.add(position);
        }
    }

    public static void removeAttackedSquares(List<Position> positions, FigureColor color) {

        if (color.equals(FigureColor.BLACK)) {
            for (int i = 0; i < positions.size(); i++) {

                if (blackAttackedSquares.contains(positions.get(i))) {

                    blackAttackedSquares.remove(positions.get(i));
                    i -= 1;
                }
            }
        } else {

            for (int i = 0; i < positions.size(); i++) {

                if (whiteAttackedSquares.contains(positions.get(i))) {

                    whiteAttackedSquares.remove(positions.get(i));
                    i -= 1;
                }
            }
        }
    }

    public static List<Position> getBlackAttackedSquares() {

        return blackAttackedSquares;
    }

    public static List<Position> getWhiteAttackedSquares() {

        return whiteAttackedSquares;
    }
}

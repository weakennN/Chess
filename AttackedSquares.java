package Chess;

import java.util.ArrayList;
import java.util.List;

public class AttackedSquares {

    private static List<Position> attackedSquares = new ArrayList<>();

    public static void addAttackedSquares(Position position) {

        attackedSquares.add(position);
    }

    public static void removeAttackedSquares(List<Position> positions) {

        for (int i = 0; i < positions.size(); i++) {

            if (attackedSquares.contains(positions.get(i))) {

                attackedSquares.remove(positions.get(i));
            }
        }
    }
}

package Chess;

import java.util.ArrayList;
import java.util.List;

public class PossiblePosition {

    private List<Position> positionList;

    public List<Position> getPositionList() {
        return this.positionList;
    }

    public PossiblePosition() {

        this.positionList = new ArrayList<>();
    }

    public void addPosition(Position position) {

        this.positionList.add(position);
    }

}

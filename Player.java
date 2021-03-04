package Chess;
public class Player implements IPlayer {

    private String name;
    private boolean turn;

    public Player(boolean turn, String name) {

        this.turn = turn;
        this.name = name;
    }

    @Override
    public void setTurn(boolean value) {

        this.turn = value;
    }

    @Override
    public boolean getTurn() {

        return this.turn;
    }
}

public class Player implements IPlayer {

    private boolean turn;

    public Player(boolean turn) {

        this.turn = turn;
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

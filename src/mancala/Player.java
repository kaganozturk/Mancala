package mancala;

public abstract class Player {

    Board board;
    int p_index;

    public Player(Board board){
        this.board = board;
    }

    abstract int move();

}

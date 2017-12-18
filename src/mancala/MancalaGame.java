package mancala;

import static mancala.GameMain.*;
import static mancala.Utils.*;

public class MancalaGame {
    Board board;
    Player[] players;

    public MancalaGame(boolean p0_human, boolean p1_human) {

        board = new Board();

        players = new Player[NUM_PLAYER];

        if (p0_human) {
            players[0] = new Human(board, 0);
        } else {
            players[0] = new Computer(board, 0);
        }

        if (p1_human) {
            players[1] = new Human(board, 1);
        } else {
            players[1] = new Computer(board, 1);
        }
        board.display();

    }


    //return true if extra move
    public boolean move(int player) {
        int pit_index = players[player].move();
        int stones = board.getPit(player, pit_index);
        board.move(player, pit_index);
        return isLastinStore(pit_index, stones);
    }






}

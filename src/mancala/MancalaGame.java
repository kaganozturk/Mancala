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
            //todo
        }

        if (p1_human) {
            players[1] = new Human(board, 1);
        } else {
            //todo
        }
        board.display();

    }


    //return true if extra move
    public boolean move(int player) {
        int pit_index = players[player].move();
        int stone = board.getPit(player, pit_index);
        board.setPit(player, pit_index, 0);
        int[] lastPit = findLastPit(player, pit_index, stone);
        move(player, pit_index, stone);
        if (emptyCapture(player, lastPit[0], lastPit[1])){
            return false;
        }
        return isLastinStore(pit_index, stone);
    }


    //return last pit
    private void move(int player, int pit_index, int stones) {

        pit_index++;
        int pit_side = player;

        while (true) {
            //check if next move is store
            if (pit_index == NUM_PITS) {
                if (pit_side == player) {
                    board.increaseStore(player);
                    pit_side = changeSide(pit_side);
                    pit_index = 0;
                }
                else {
                    pit_side = changeSide(pit_side);
                    pit_index = 0;
                    board.increasePit(pit_side, pit_index);
                    pit_index++;
                }
            }
            else
            {
                board.increasePit(pit_side, pit_index);
                pit_index++;
            }
            stones--;
            if (stones == 0)
                break;

        }
    }


    //return -1 if the game is not finished
    //if one side is empty return the other side

    public int isFinished() {
        int i = 0;
        int j;
        for (j = 0; j < NUM_PITS; j++) {
            if (board.getPit(i, j) != 0)
                break;
        }

        if (j == NUM_PITS)
            return i;

        i = 1;
        for (j = 0; j < NUM_PITS; j++) {
            if (board.getPit(i, j) != 0)
                return -1;
        }
        return i;
    }

    public boolean emptyCapture(int player, int pit_side, int pit_index) {
        if (pit_index != NUM_PITS && player == pit_side && board.getPit(pit_side, pit_index) == 1) {
//            System.out.println("Empty capture");
            board.setPit(pit_side, pit_index, 0);
            int stones = 1;
            int opp_side = changeSide(pit_side);
            int opp_pit_index = NUM_PITS - pit_index - 1;
            stones += board.getPit(opp_side, opp_pit_index);
            board.setPit(opp_side, opp_pit_index, 0);
            board.setStore(pit_side, stones);
            return true;
        }
        return false;
    }

    public void endGameCapture(int side) {
        side = changeSide(side);
        for (int i = 0; i < NUM_PITS; i++) {
            board.setStore(side, board.getPit(side, i));
            board.setPit(side, i, 0);
        }
        board.display();
    }


}

package mancala;

import static mancala.GameMain.*;
import static mancala.Utils.changeSide;
import static mancala.Utils.findLastPit;

public class Board {


    private int[][] pits;
    private int[] stores;



    public Board(){
        stores = new int[NUM_PLAYER];
        pits = new int[NUM_PLAYER][NUM_PITS];
        //initialize pits
        for (int i = 0; i < NUM_PLAYER; i++) {
            for (int j = 0; j < NUM_PITS; j++) {
                pits[i][j] = NUM_STONES;
            }
        }
    }

    public Board(Board board){
        stores = new int[NUM_PLAYER];
        pits = new int[NUM_PLAYER][NUM_PITS];

        stores[0] = board.getStore(0);
        stores[1] = board.getStore(1);

        for (int i = 0; i < NUM_PLAYER; i++) {
            for (int j = 0; j < NUM_PITS; j++) {
                pits[i][j] = board.pits[i][j];
            }
        }
    }


    public void move(int player, int pit_index) {

        int stones = getPit(player, pit_index);
        int[] lastPit = findLastPit(player, pit_index, stones);
        setPit(player, pit_index, 0);

        pit_index++;
        int pit_side = player;

        while (true) {
            //check if next move is store
            if (pit_index == NUM_PITS) {
                if (pit_side == player) {
                    increaseStore(player);
                    pit_side = changeSide(pit_side);
                    pit_index = 0;
                }
                else {
                    pit_side = changeSide(pit_side);
                    pit_index = 0;
                    increasePit(pit_side, pit_index);
                    pit_index++;
                }
            }
            else
            {
                increasePit(pit_side, pit_index);
                pit_index++;
            }
            stones--;
            if (stones == 0)
                break;

        }
        emptyCapture(player, lastPit[0], lastPit[1]);

    }

    public void emptyCapture(int player, int pit_side, int pit_index) {
        if (pit_index != NUM_PITS && player == pit_side && getPit(pit_side, pit_index) == 1) {
//            System.out.println("Empty capture");
            setPit(pit_side, pit_index, 0);
            int stones = 1;
            int opp_side = changeSide(pit_side);
            int opp_pit_index = NUM_PITS - pit_index - 1;
            stones += getPit(opp_side, opp_pit_index);
            setPit(opp_side, opp_pit_index, 0);
            setStore(pit_side, stones);
        }
    }




    public void display(){
        System.out.println("--------------------------------------");
        StringBuilder first_line = new StringBuilder();
        String second_line;
        String second_line_start;
        StringBuilder third_line = new StringBuilder();

        //player 1
        first_line.append("       | ");
        int p = 1;
        for (int i = NUM_PITS-1; i >= 0; i--) {
            first_line.append("|").append(pits[p][i]).append("| ");
        }
        first_line.append("|");

        //player 2
        second_line_start = "P" + (p+1) + " |" + stores[p] + "|";

        third_line.append("       | ");
        p = 0;
        for (int i = 0; i < NUM_PITS; i++) {
            third_line.append("|").append(pits[p][i]).append("| ");
        }
        third_line.append("|");
        //first line
        System.out.println(first_line);
        //second line
        second_line = second_line_start;
        int num_space = (first_line.length() > third_line.length()) ? first_line.length() : third_line.length();
        String space = String.format("%" + (num_space - second_line_start.length() + 1) + "s", "");
        second_line += space;
        second_line += "P" + (p+1) + " |" + stores[p] + "|";
        System.out.println(second_line);
        //third line
        System.out.println(third_line);
        System.out.println("--------------------------------------");

    }

    public int getPit(int pit_side, int pit_index) {
        return pits[pit_side][pit_index];
    }

    public void increasePit(int pit_side, int pit_index) {
        this.pits[pit_side][pit_index]++;
    }

    public void setPit(int pit_side, int pit_index, int stone) {
        this.pits[pit_side][pit_index] = stone;
    }

    public int getStore(int p_index) {
        return stores[p_index];
    }

    public void increaseStore(int p_side) {
        stores[p_side]++;
    }

    public void setStore(int p_side, int value){
        stores[p_side] += value;
    }
}

package mancala;

import static mancala.GameMain.*;

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




    public void display(){
        System.out.println("--------------------------------------");
        StringBuilder first_line = new StringBuilder();
        String second_line_start = "";
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
        System.out.print(second_line_start);
        int num_space = (first_line.length() > third_line.length()) ? first_line.length() : third_line.length();
        String space = String.format("%" + (num_space - second_line_start.length() + 1) + "s", "");
        System.out.print(space);
        System.out.println("P" + (p+1) + " |" + stores[p] + "|");
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

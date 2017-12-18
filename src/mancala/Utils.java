package mancala;

import static mancala.GameMain.*;

public class Utils {

    public static int changeSide(int side){
        return (side == 0) ? 1 : 0;
    }

    public static boolean isLastinStore(int pit_index, int stones){
        int distance = NUM_PITS - pit_index;
        if (stones%(2 * NUM_PITS + 1) - distance == 0){
//            System.out.println("Extra Move");
            return true;
        }
        return false;
    }

    public static int[] findLastPit(int pit_side, int pit_index, int stone){
        int[] pit = new int[2];
        pit[0] = pit_side;
        stone = stone%(2 * NUM_PITS + 1);
        int steps = pit_index + stone;
        if (steps > NUM_PITS){
//            System.out.println(">");
            int tmp = steps - NUM_PITS - 1;
            pit[1] = tmp%NUM_PITS;
            if (tmp / NUM_PITS == 0)
                pit[0] = changeSide(pit_side);
        }
        else if (steps == NUM_PITS){
//            System.out.println("store");
            pit[1] = NUM_PITS;
        }
        else {
//            System.out.println("<");
            pit[1] = steps;
        }
        return pit;
    }



    public static int isFinished(Board board) {
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


    public static void endGameCapture(Board board, int side) {
        side = changeSide(side);
        for (int i = 0; i < NUM_PITS; i++) {
            board.setStore(side, board.getPit(side, i));
            board.setPit(side, i, 0);
        }
    }
}

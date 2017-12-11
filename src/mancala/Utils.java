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
}

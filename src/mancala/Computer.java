package mancala;

import static mancala.GameMain.*;
import static mancala.Utils.*;

import java.util.ArrayList;
import java.util.List;

//todo
public class Computer extends Player {


    public Computer(Board board, int p_index) {
        super(board);
        this.p_index = p_index;
    }

    @Override
    int move() {
        int pit_index = minimax(p_index, 2);
        return pit_index;
    }

    private int minimax(int p_index, int depth){
        for (int i = NUM_PITS - 1; i >= 0 ; i--) {
            if (isLastinStore(p_index, board.getPit(p_index, i)))
                return i;
        }
        return minimax(changeSide(p_index), depth-1);
    }

    private List<Integer> getAvailableMoves(){
        List<Integer> availableMoves = new ArrayList<>();

        return availableMoves;
    }
}

package mancala;

import java.util.Scanner;
import static mancala.GameMain.*;

public class Human extends Player {

    Scanner scanner;


    public Human(Board board, int p_index) {
        super(board);
        this.p_index = p_index;
        scanner = new Scanner(System.in);
    }

    @Override
    int move() {

        System.out.print("Selected pit : ");
        int input = scanner.nextInt();
        while (input < 0 || input >= NUM_PITS || board.getPit(p_index, input) == 0){
            System.out.println("Enter a valid number");
            input = scanner.nextInt();
        }
        return input;
    }
}

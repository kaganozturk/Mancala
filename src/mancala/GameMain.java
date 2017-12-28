package mancala;

import java.util.Scanner;

import static mancala.Utils.*;

public class GameMain {

    public static final int NUM_PLAYER = 2;

    public static final int NUM_PITS = 6;
    public static final int NUM_STONES = 4;


    public static void main(String[] args) {


        boolean players[] = new boolean[NUM_PLAYER];
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < 2; i++) {
            System.out.println("Select Player " + (i + 1));
            System.out.println("1. Human");
            System.out.println("2. Computer");
            int input = scanner.nextInt();
            while (input != 1 && input != 2) {
                System.out.println("Enter 1 or 2");
                input = scanner.nextInt();
            }
            if (input == 1)
                players[i] = true;
            else
                players[i] = false;
        }


        MancalaGame game = new MancalaGame(players[0], players[1]);
        int cur_player = 0;
        boolean extraMove;
        int isFinished;


        while (true) {
            System.out.println("Player " + (cur_player + 1) + "'s Turn");
            extraMove = game.move(cur_player);
            game.board.display();

            isFinished = isFinished(game.board);

            if (isFinished != -1) {
                System.out.println("END");
                endGameCapture(game.board, isFinished);
                game.board.display();
                break;
            }

            if (!extraMove)
                cur_player = changeSide(cur_player);
        }

        if (game.board.getStore(0) > game.board.getStore(1))
            System.out.println("Player 1 won");
        else if (game.board.getStore(0) == game.board.getStore(1))
            System.out.println("Draw");
        else
            System.out.println("Player 2 won");
    }
}

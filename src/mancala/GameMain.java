package mancala;

import static mancala.Utils.*;

public class GameMain {

    public static final int NUM_PLAYER = 2;

    public static final int NUM_PITS = 6;
    public static final int NUM_STONES = 5;


    public static void main(String[] args) {

        MancalaGame game = new MancalaGame(true, true);
        int cur_player = 0;
        boolean extraMove;
        int isFinished;

        while (true)
        {
            System.out.println("Player " + (cur_player+1) + "'s Turn");
            extraMove = game.move(cur_player);
            game.board.display();

            isFinished = game.isFinished();

            if (isFinished != -1){
                System.out.println("END");
                game.endGameCapture(isFinished);
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

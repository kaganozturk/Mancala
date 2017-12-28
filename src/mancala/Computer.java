package mancala;

import java.util.ArrayList;
import java.util.Scanner;

import static mancala.GameMain.*;
import static mancala.Utils.*;

public class Computer extends Player {

    private int depthLimit;

    public Computer(Board board, int p_index) {
        super(board);
        this.p_index = p_index;
        System.out.println("Enter the depth limit for Player " + (p_index+1));
        depthLimit = new Scanner(System.in).nextInt();
    }

    @Override
    int move() {
        Board cur_board = new Board(board);
        int move = minimax(cur_board, p_index, depthLimit)[0];
        System.out.println("Selected pit : " + move);
        return move;
    }

    private int[] minimax(Board board, int player, int depth) {
        int bestScore;
        int bestMove = -1;
        //check if game is over
        int isFinished = isFinished(board);
        if (isFinished != -1) {
            endGameCapture(board, isFinished);
            bestScore = evaluateBoard(board);
            return new int[]{bestMove, bestScore};
        }
        //time limit
        if (depth == 0) {
            bestScore = evaluateBoard(board);
            return new int[]{bestMove, bestScore};
        }
        ArrayList<Integer> moves = getAvailableMoves(board, player);
        //max player
        if (player == 0) {
            bestScore = Integer.MIN_VALUE;
            for (Integer move : moves) {
                Board cur_board = new Board(board);
                cur_board.move(player, move);
                int score;
                if (isLastinStore(move, board.getPit(player, move)))
                    score = minimax(cur_board, player, depth - 1)[1];
                else
                    score = minimax(cur_board, changeSide(player), depth - 1)[1];
                if (score > bestScore) {
                    bestScore = score;
                    bestMove = move;
                }
            }
        }//min player
        else {
            bestScore = Integer.MAX_VALUE;
            for (Integer move : moves) {
                Board cur_board = new Board(board);
                cur_board.move(player, move);
                int score;
                if (isLastinStore(move, board.getPit(player, move)))
                    score = minimax(cur_board, player, depth - 1)[1];
                else
                    score = minimax(cur_board, changeSide(player), depth - 1)[1];
                if (score < bestScore) {
                    bestScore = score;
                    bestMove = move;
                }
            }
        }
        return new int[]{bestMove, bestScore};
    }

    private ArrayList<Integer> getAvailableMoves(Board board, int player) {
        ArrayList<Integer> availableMoves = new ArrayList<>();
        for (int i = 0; i < NUM_PITS; i++) {
            if (board.getPit(player, i) != 0)
                availableMoves.add(i);
        }
        return availableMoves;
    }

    private int evaluateBoard(Board board) {
        return board.getStore(0) - board.getStore(1);
    }
}

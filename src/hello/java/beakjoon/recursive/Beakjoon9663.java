package hello.java.beakjoon.recursive;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Beakjoon9663{

    private static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] board = new int[N];
        nQueueProblemRecursive(0, N, board);
        System.out.println(result);
    }


    private static void nQueueProblemRecursive(
            int depth,
            int N,
            int[] board
    ){
        if(depth == N){
            result += 1;
            return;
        }

        for(int i = 0; i< N; i++){
            if(!isPromiss(depth, i, board)){
                continue;
            }
            board[depth] = i;
            nQueueProblemRecursive(depth+1, N, board);
        }
    }



    private static boolean isPromiss(
            int depth,
            int col,
            int[] board
    ){
        for(int row = 0; row < depth; row++){
            //check col
            if(board[row] == col){
                return false;
            }

            //check cross
            if(Math.abs(row-depth) == Math.abs(board[row]-col)){
                return false;
            }
        }


        return true;
    }
}
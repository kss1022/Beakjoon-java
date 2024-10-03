package hello.java.beakjoon.dfs;

import java.io.*;
import java.util.*;


public class Beakjoon1987{

    private static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] size = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int R = size[0];
        int C = size[1];

        char[][] board = new char[R][C];

        for(int i = 0; i < R; i++){
            String row = br.readLine();

            for(int j = 0; j < C; j++){
                board[i][j] = row.charAt(j);
            }
        }

        Set<Character> history = new HashSet<>();
        history.add(board[0][0]);
        moveRecursive(R, C, board, 0, 0, history);
        System.out.println(result);
    }


    private static void moveRecursive(
            int N, int M, char[][] board,
            int x, int y, Set<Character> history
    ){
        result = Math.max(result, history.size());

        for(Move move: Move.moves){
            int nextRow = move.x + x;
            int nextCol = move.y + y;

            if(nextRow < 0 || nextCol < 0 || nextRow >= N || nextCol >= M){
                continue;
            }

            char alphabet = board[nextRow][nextCol];
            if(history.contains(alphabet)){
                continue;
            }

            history.add(alphabet);
            moveRecursive(N, M, board, nextRow, nextCol, history);
            history.remove(alphabet);
        }

    }


    private static class Move{
        int x;
        int y;

        Move(int x, int y){
            this.x = x;
            this.y = y;
        }

        static Move[] moves = {
                new Move(-1, 0), new Move(0, -1),
                new Move(1, 0), new Move(0, 1),
        };
    }
}
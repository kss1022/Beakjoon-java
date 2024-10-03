package hello.java.beakjoon.bruteForce;

import java.io.*;
import java.util.*;


public class Beakjoon14500{

    private static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] size = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int N = size[0];
        int M = size[1];


        int[][] board = new int[N][M];

        for(int i = 0; i < N; i++){
            int[] row = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            board[i] = row;
        }

        maxTechnomino(N, M, board);
        System.out.println(result);
    }



    private static void maxTechnomino(int N, int M, int[][] board){
        boolean[][] visited = new boolean[N][M];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                maxTechnominoRecursive(N, M , board, i, j, 0, 0, visited);
                lastTechnomino(N, M, board, i, j);
            }
        }
    }

    private static void maxTechnominoRecursive(
            int N,
            int M,
            int[][] board,
            int x,
            int y,
            int depth,
            int sum,
            boolean[][] visited
    ){

        if(depth == 4){
            result = Math.max(result, sum);
            return;
        }


        for(Move move : Move.moves){
            int nextRow = move.x + x;
            int nextCol = move.y + y;

            if(nextRow < 0 || nextCol < 0 || nextRow >= N || nextCol >= M){
                continue;
            }

            if(visited[nextRow][nextCol]){
                continue;
            }

            visited[nextRow][nextCol] = true;
            maxTechnominoRecursive(
                    N, M, board,
                    nextRow, nextCol,
                    depth + 1,
                    sum + board[nextRow][nextCol], visited
            );
            visited[nextRow][nextCol] = false;
        }
    }

    /**
     *       ***      *       *      *
     *        *      **      ***     **
     *                *              *
     **/

    private static void lastTechnomino(
            int N,
            int M,
            int[][] board,
            int x,
            int y
    ){
        //1. let basic = [(0,0), (0,1), (0,2), (1,1)]
        //2. let rotate90 = [(0,1), (1,0), (1,1), (2,1)]
        //3. let rotate180 = [(0,1) ,(1,0), (1,1), (1,2)]
        //4. let rotate270 = [(0,0) ,(1,0), (1,1), (2,0)]

        //[(0,0), (0,1), (0,2), (1,1)]
        if(!(x + 1 >= N || y + 2 >= M)){
            Move[] moves = {
                    new Move(0,0), new Move(0,1),
                    new Move(0,2), new Move(1,1)
            };

            int sum = 0;
            for(Move move : moves){
                sum += board[x + move.x][y + move.y];
            }
            result = Math.max(result, sum);
        }

        //[(0,1), (1,0), (1,1), (2,1)]
        if(!(x + 2 >= N || y + 1 >= M)){
            Move[] moves = {
                    new Move(0,1), new Move(1,0),
                    new Move(1,1), new Move(2,1)
            };

            int sum = 0;
            for(Move move : moves){
                sum += board[x + move.x][y + move.y];
            }
            result = Math.max(result, sum);
        }

        //[(0,1) ,(1,0), (1,1), (1,2)]
        if(!(x + 1 >= N || y + 2 >= M)){
            Move[] moves = {
                    new Move(0,1), new Move(1, 0),
                    new Move(1,1), new Move(1,2)
            };

            int sum = 0;
            for(Move move : moves){
                sum += board[x + move.x][y + move.y];
            }
            result = Math.max(result, sum);
        }

        //[(0,0) ,(1,0), (1,1), (2,0)]
        if(!(x + 2 >= N || y + 1 >= M)){
            Move[] moves = {
                    new Move(0,0), new Move(1,0),
                    new Move(1,1), new Move(2,0)
            };

            int sum = 0;
            for(Move move : moves){
                sum += board[x + move.x][y + move.y];
            }
            result = Math.max(result, sum);
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
                new Move(-1,0), new Move(1, 0),
                new Move(0,-1), new Move(0,1)
        };
    }
}

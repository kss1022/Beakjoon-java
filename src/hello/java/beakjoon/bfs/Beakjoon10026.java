package hello.java.beakjoon.bfs;

import java.io.*;
import java.util.*;

public class Beakjoon10026{

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        char[][] board = new char[N][N];

        for(int i = 0; i < N ; i++){
            String row = br.readLine();

            for(int j = 0; j < N; j++){
                board[i][j] = row.charAt(j);
            }
        }


        int normalResult = normalGroup(N, board);
        int redGreenResult = redGreenBlindnessGroup(N, board);



        System.out.println(normalResult + " " + redGreenResult);
    }



    private static int normalGroup(int N, char[][] board){
        boolean[][] visited = new boolean[N][N];


        int result = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(visited[i][j]){
                    continue;
                }
                normalGroupRecursive(i, j, N, board, visited);
                result += 1;
            }
        }
        return result;
    }

    private static int redGreenBlindnessGroup(int N, char[][] board){
        boolean[][] visited = new boolean[N][N];


        int result = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(visited[i][j]){
                    continue;
                }
                redGreenGroupRecursive(i, j, N, board, visited);
                result += 1;
            }
        }
        return result;
    }


    private static void redGreenGroupRecursive(
            int x,
            int y,
            int N,
            char[][] board,
            boolean[][] visited
    ){
        Queue<Position> queue = new LinkedList<>();

        queue.add(new Position(x, y));
        visited[x][y] = true;

        while(!queue.isEmpty()){
            Position pop = queue.poll();

            for(Move move : Move.moves){
                int nextRow = move.x + pop.x;
                int nextCol = move.y + pop.y;

                if(nextRow < 0 || nextCol < 0 || nextRow >= N || nextCol >= N){
                    continue;
                }

                if(visited[nextRow][nextCol]){
                    continue;
                }

                if(board[x][y] == board[nextRow][nextCol] ||
                        (board[x][y] != 'B' && board[nextRow][nextCol] != 'B')
                ){
                    visited[nextRow][nextCol] = true;
                    queue.add(new Position(nextRow, nextCol));
                }
            }
        }
    }



    private static void normalGroupRecursive(
            int x,
            int y,
            int N,
            char[][] board,
            boolean[][] visited
    ){
        Queue<Position> queue = new LinkedList<>();

        queue.add(new Position(x, y));
        visited[x][y] = true;

        while(!queue.isEmpty()){
            Position pop = queue.poll();

            for(Move move : Move.moves){
                int nextRow = move.x + pop.x;
                int nextCol = move.y + pop.y;

                if(nextRow < 0 || nextCol < 0 || nextRow >= N || nextCol >= N){
                    continue;
                }

                if(visited[nextRow][nextCol]){
                    continue;
                }

                if(board[x][y] == board[nextRow][nextCol]){
                    visited[nextRow][nextCol] = true;
                    queue.add(new Position(nextRow, nextCol));
                }
            }
        }
    }

    private static class Position{
        int x;
        int y;

        Position(int x, int y){
            this.x = x;
            this.y = y;
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
                new Move(1,0), new Move(0,1),
                new Move(-1,0), new Move(0,-1)
        };
    }

}



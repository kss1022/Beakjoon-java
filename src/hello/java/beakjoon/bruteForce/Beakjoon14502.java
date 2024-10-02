package hello.java.beakjoon.bruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Beakjoon14502{

    private static int maxRange = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        int[] size = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int N = size[0];
        int M = size[1];

        int[][] map = new int[N][M];


        for(int i = 0; i< N; i++){
            int[] row = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            map[i] = row;
        }

        maxSafeRange(N, M, map);
        System.out.println(maxRange);
    }

    /**
     *   0: empty
     *   1: wall
     *   2: virus
     **/
    private static void maxSafeRange(int N, int M, int[][] map){
        setWallRecursive(N, M , map, 0, 0);
    }


    private static void setWallRecursive(int N, int M, int[][] map, int index, int depth){
        if(depth == 3){
            maxRange = Math.max(safeRange(N, M, map), maxRange);
            return;
        }

        for(int i = index; i < N * M ; i++){
            int row = i / M;
            int col = i % M;

            //isWall
            if(map[row][col] == 0){
                map[row][col] = 1;
                setWallRecursive(N, M, map, index+1, depth+1);
                map[row][col] = 0;
            }
        }
    }




    private static int safeRange(int N, int M, int[][] map){
        int[][] spread = spreadAll(N, M , map);

        int result = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(spread[i][j] == 0){
                    result += 1;
                }
            }
        }
        return result;
    }

    private static int[][] spreadAll(int N, int M, int[][] map){
        int[][] result = new int[N][M];
        for(int i = 0; i < N; i++){
            for(int j =0; j < M; j++){
                result[i][j] = map[i][j];
            }
        }

        boolean[][] visited = new boolean[N][M];

        for(int i = 0; i < N; i++){
            for(int j =0; j < M; j++){
                if(result[i][j] == 2 && !visited[i][j]){
                    spread(N, M, result, visited, new Position(i, j));
                }
            }
        }

        return result;
    }

    private static void spread(int N, int M, int[][] map, boolean[][] visited, Position virus){
        Queue<Position> queue = new LinkedList<>();
        queue.add(virus);

        while(!queue.isEmpty()){
            Position pop = queue.poll();

            for(Move move : Move.moves){
                int nextRow = move.x + pop.x;
                int nextCol = move.y + pop.y;

                if(nextRow < 0 || nextCol < 0 || nextRow >= N || nextCol >= M){
                    continue;
                }

                if(map[nextRow][nextCol] != 0){
                    continue;
                }

                if(visited[nextRow][nextCol]){
                    continue;
                }

                visited[nextRow][nextCol] = true;
                map[nextRow][nextCol] = 2;
                queue.add(new Position(nextRow, nextCol));
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

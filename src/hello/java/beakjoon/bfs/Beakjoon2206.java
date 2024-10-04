package hello.java.beakjoon.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Beakjoon2206{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] size = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int N = size[0];
        int M = size[1];

        int[][] map = new int[N][M];

        for(int i = 0; i < N; i++){
            String row = br.readLine();
            for(int j = 0; j < M; j++){
                map[i][j] = Integer.parseInt(row.charAt(j) + "");
            }
        }

        int result = minDistance(N, M, map);
        if(result == Integer.MAX_VALUE){
            System.out.println(-1);
        }else{
            System.out.println(result);
        }
    }


    // 0 -> move, 1 ->  wall
    private static int minDistance(int N, int M, int[][] board){
        Queue<Move> queue = new LinkedList<>();
        // visited[0] -> 부수지 않은 상태
        // visited[1] -> 부순 상태
        boolean[][][] visited = new boolean[2][N][M];

        queue.add(new Move(0,0, 1, 0));
        visited[0][0][0] = true;

        int result = Integer.MAX_VALUE;

        while(!queue.isEmpty()){
            Move pop = queue.poll();

            if(pop.x == N-1 && pop.y == M-1){
                result = Math.min(result, pop.d);
            }

            for(Pair neighbor : neighbors){
                int nextRow = neighbor.x + pop.x;
                int nextCol = neighbor.y + pop.y;

                if(nextRow < 0 || nextCol < 0 || nextRow >= N || nextCol >= M){
                    continue;
                }

                if(visited[pop.isBroken][nextRow][nextCol]){
                    continue;
                }

                if(board[nextRow][nextCol] == 0){
                    visited[pop.isBroken][nextRow][nextCol] = true;
                    queue.add(new Move(nextRow, nextCol, pop.d + 1, pop.isBroken));
                }else if(pop.isBroken == 0){
                    //벽이고 이미 부수지 않은 상태
                    visited[1][nextRow][nextCol] = true;
                    queue.add(new Move(nextRow, nextCol, pop.d + 1, 1));
                }
            }

        }

        return result;
    }




    private static class Move{
        int x = 0;
        int y = 0;
        int d = 0;
        int isBroken;

        Move(int x, int y, int d, int isBroken){
            this.x = x;
            this.y = y;
            this.d = d;
            this.isBroken = isBroken;
        }
    }


    private static class Pair{
        int x = 0;
        int y = 0;

        Pair(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    private static final Pair[] neighbors = {
            new Pair(1, 0), new Pair(0, 1),
            new Pair(-1, 0), new Pair(0, -1)
    };



}
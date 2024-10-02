package hello.java.beakjoon.bfs;


import java.io.*;
import java.util.*;


public class Beakjoon7576 {
    public static void solution(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        int[] size = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int M = size[0];
        int N = size[1];


        int[][] tomatos = new int[N][M];

        for(int i = 0; i < N; i++){
            int[] row = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            tomatos[i] = row;
        }

        int result = ripeDay(N, M, tomatos);
        System.out.println(result);
    }

    private static int ripeDay(int N, int M, int[][] tomatos){
        Set<Position> ripes = new HashSet<>();


        for(int i = 0; i < N;  i++){
            for(int j = 0; j < M; j++){
                if(tomatos[i][j]  == 1){
                    ripes.add(new Position(i, j));
                }
            }
        }

        int result = 0;

        while(true){
            ripes = ripe(N, M, tomatos, ripes);
            if (ripes.isEmpty()){
                break;
            }

            result += 1;
        }

        for(int i = 0; i < N;  i++){
            for(int j = 0; j < M; j++){
                if(tomatos[i][j]  == 0){
                    return -1;
                }
            }
        }

        return result;
    }

    private static Set<Position> ripe(int N, int M, int[][] tomatos, Set<Position> ripes){
        Set<Position> result = new HashSet<>();


        for(Position tomato : ripes){
            for(Move move : Move.moves){
                int nextRow = move.x + tomato.x;
                int nextCol = move.y + tomato.y;

                if(nextRow < 0 || nextCol < 0 || nextRow >= N ||   nextCol >= M){
                    continue;
                }

                if(tomatos[nextRow][nextCol] != 0){
                    continue;
                }

                tomatos[nextRow][nextCol] = 1;
                result.add(new Position(nextRow, nextCol));
            }

        }

        return result;
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
                new Move(1, 0),
                new Move(0, 1),
                new Move(-1, 0),
                new Move(0, -1),
        };
    }

}
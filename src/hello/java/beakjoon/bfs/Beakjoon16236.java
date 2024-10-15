package hello.java.beakjoon.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Beakjoon16236{

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[][] map = new int[N][N];

        for(int i = 0; i < N; i++){
            int[] row = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            map[i] = row;
        }
        eatFish(N, map);
        System.out.println(result);
    }


    private static int result = 0;

    private static void eatFish(int N, int[][] map){
        Pos shark = getShark(N, map);
        map[shark.x][shark.y]  = 0;
        eatFishRecursive(N, map, shark, 2, 0);
    }



    private static void eatFishRecursive(
            int N,
            int[][] map,
            Pos shark,
            int sharkSize,
            int eating
    ){

        boolean[][] visited = new boolean[N][N];

        Queue<Mem> queue = new LinkedList<>();

        visited[shark.x][shark.y] = true;
        queue.add(new Mem(shark, 0));


        List<Mem> findFishs = new ArrayList<>();

        int minDistance = Integer.MAX_VALUE;

        //먹을수 있는 물고기를 찾는다
        while(!queue.isEmpty()){
            Mem dequeue = queue.poll();
            Pos pos = dequeue.pos;
            int distance = dequeue.distance;

            if (minDistance < distance){
                continue;
            }

            if (map[pos.x][pos.y] != 0 && map[pos.x][pos.y] < sharkSize ) {
                minDistance = distance;
                findFishs.add(dequeue);
                continue;
            }

            for(Move move : Move.moves){
                int nextRow = move.x + pos.x;
                int nextCol = move.y + pos.y;

                if(nextRow < 0 || nextCol < 0 ||
                        nextRow >= N || nextCol >= N){
                    continue;
                }

                if(visited[nextRow][nextCol]){
                    continue;
                }

                int fishSize = map[nextRow][nextCol];

                //큰 경우 이동x
                if(fishSize > sharkSize){
                    visited[nextRow][nextCol] = true;
                    continue;
                }

                Pos nextPos = new Pos(nextRow, nextCol);
                queue.add(new Mem(nextPos, distance+1));
                visited[nextRow][nextCol] = true;
            }
        }


        //없다면 재귀 끝
        if(findFishs.isEmpty()){
            return;
        }

        //찾은 물고기중 가장 가깝고 가장 위 가장 왼쪽에 있는 물고기를 먹는다
        Mem min = findFishs.get(0);
        if(findFishs.size() != 1){
            for (int i = 1; i < findFishs.size(); i++){
                Mem mem = findFishs.get(i);
                if (mem.pos.x < min.pos.x){
                    min = mem;
                }else if (mem.pos.x == min.pos.x && mem.pos.y < min.pos.y){
                    min = mem;
                }
            }
        }


        //자신의 크기와 같은 수의 물고기를 먹을 때 마다 크기가 1 증가한다.
        int newEating = eating + 1;
        int newSharkSize = sharkSize;

        if (newEating == sharkSize){
            newSharkSize += 1;
            newEating = 0;
        }

        map[min.pos.x][min.pos.y] = 0;
        result += min.distance;
        eatFishRecursive(N, map, min.pos, newSharkSize, newEating);
    }


    private static Pos getShark(int N, int[][] map){
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(map[i][j] == 9){
                    //find
                    return new Pos(i, j);
                }
            }
        }

        throw new RuntimeException("This is a fatal error.");
    }



    private static class Mem{
        Pos pos;
        int distance;

        Mem(Pos pos, int distance){
            this.pos = pos;
            this.distance = distance;
        }

        public int hashCode(){
            return pos.hashCode();
        }

        public boolean equals(Object obj){
            if(this == obj){
                return true;
            }

            if(obj == null || getClass() != obj.getClass()){
                return false;
            }

            Mem mem = (Mem) obj;
            return this.pos.x == mem.pos.x && this.pos.y == mem.pos.y;
        }
    }



    private static class Pos{
        int x;
        int y;

        Pos(int x, int y){
            this.x = x;
            this.y = y;
        }

        public int hashCode(){
            return Objects.hash(x, y);
        }

        public boolean equals(Object obj){
            if(this == obj){
                return true;
            }

            if(obj == null || getClass() != obj.getClass()){
                return false;
            }

            Pos pos = (Pos) obj;
            return this.x == pos.x && this.y == pos.y;
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

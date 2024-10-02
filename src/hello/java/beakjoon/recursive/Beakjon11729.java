package hello.java.beakjoon.recursive;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Beakjon11729 {

    private static List<Move> result = new ArrayList<>();

    public static void solution(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        hanoi(N, 1, 2, 3);


        System.out.println(result.size());
        StringBuilder sb = new StringBuilder();
        for(Move move : result){
            sb.append(move.start + " " + move.dest + "\n");
        }
        System.out.println(sb.toString());
    }

    private static void hanoi(int n, int start, int mid, int dest){
        if(n == 1){
            result.add(new Move(start, dest));
            return;
        }

        //n-1개의 원판을 mid로 옮긴다
        hanoi(n - 1, start, dest, mid);

        //마지막 원판을 dest로 옮긴다
        result.add(new Move(start, dest));

        //n-1개의 원판을 다시 dest로 옮긴다
        hanoi(n - 1, mid, start, dest);
    }



    private static class Move{
        int start;
        int dest;


        Move(int start, int dest){
            this.start = start;
            this.dest = dest;
        }
    }
}


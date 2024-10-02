package hello.java.beakjoon.recursive;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Beakjoon2447 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        char[][] result = fillStars(N);
        printResult(result);
    }


    private static char[][] fillStars(int N){
        char[][] result = new char[N][N];
        for(int i = 0; i< N; i++){
            Arrays.fill(result[i], '*');
        }

        fillStartRecursive(N, 3, result);
        return result;
    }

    private static void fillStartRecursive(
            int N,
            int size,
            char[][] result
    ){
        if(N < size){
            return;
        }

        for(int i = 0; i < N ; i += size){
            for(int j = 0; j < N; j += size){
                int emptySize = size / 3;

                for(int k = 0; k < emptySize; k++){
                    for(int l = 0; l < emptySize; l++){
                        result[i+emptySize+k][j+emptySize+l] = ' ';
                    }

                }
            }
        }

        fillStartRecursive(N, size * 3, result);
    }

    private static void printResult(char [][] result){
        StringBuilder sb = new StringBuilder();
        for (char[] row : result){
            for(char c : row){
                sb.append(c);
            }

            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

}

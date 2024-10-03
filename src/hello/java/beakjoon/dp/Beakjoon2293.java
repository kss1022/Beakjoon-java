package hello.java.beakjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Beakjoon2293{

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] input = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int n = input[0];
        int k = input[1];


        int[] coins = new int[n];

        for(int i = 0; i < n; i++){
            int coin = Integer.parseInt(br.readLine());
            coins[i] = coin;
        }

        int result = combination(n, k, coins);
        System.out.println(result);
    }



    private static int combination(int n, int k, int[] coins){
        int[] mem = new int[k+1];
        mem[0] = 1;

        for(int coin : coins){
            for(int i = coin; i <= k; i++){
                mem[i] = mem[i] + mem[i - coin];
            }
        }

        return mem[k];
    }


}

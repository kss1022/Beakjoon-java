package hello.java.beakjoon.dp;

import java.io.*;
import java.util.*;

public class Beakjoon12865 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] size = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int N = size[0];
        int K = size[1];

        Product[] products = new Product[N];
        for(int i = 0; i < N; i++){
            int[] product = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            products[i] = new Product(product[0], product[1]);
        }

        int result = dp(N, K, products);
        System.out.println(result);
    }

    private static int dp(int N, int K, Product[] products){
        int[][] mem = new int[N][K+1];
        for(int i = 0; i < N; i++){
            Arrays.fill(mem[i], 0);
        }

        Product start = products[0];
        for(int j = 1; j < K+1; j++){
            if(j >= start.weight){
                mem[0][j] = start.price;
            }
        }


        for(int i = 1; i < N; i++){
            Product product = products[i];
            for(int j = 1; j < K+1; j++){
                if (j < product.weight){
                    mem[i][j] = mem[i-1][j];
                    continue;
                }


                int remainWeight = j - product.weight;
                int remainPrice = mem[i-1][remainWeight];

                int maxPrice = mem[i-1][j];
                int sum = remainPrice + product.price;
                mem[i][j] = Math.max(maxPrice, sum);
            }
        }
        return mem[N-1][K];
    }


    private static class Product{
        int weight;
        int price;

        Product(int weight, int price){
            this.weight = weight;
            this.price = price;
        }
    }
}
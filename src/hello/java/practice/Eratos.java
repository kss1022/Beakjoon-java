package hello.java.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.IntStream;

public class Eratos {

    public static void run(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        int[] inputArray = Arrays.stream(input.split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int min = inputArray[0];
        int max = inputArray[1];

        boolean[] isPrime = eratos(max);
        IntStream.rangeClosed(min, max)
                .filter(num -> isPrime[num])
                .forEach(num -> System.out.print(num+ " "));
    }
    public static boolean[] eratos(int max){
        boolean[] isPrime = new boolean[max+1];
        Arrays.fill(isPrime, true);

        isPrime[0] = false;
        isPrime[1] = false;


        int i = 2;

        while(i * i <= max){
            if (!isPrime[i]){
                i += 1;
                continue;
            }

            for (int j = i * 2; j <= max; j += i){
                isPrime[j] = false;
            }
            i += 1;
        }


        return isPrime;
    }
}

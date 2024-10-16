package hello.java.beakjoon.binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


public class Beakjoon12015{

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N =  Integer.parseInt(br.readLine());

        int[] nums = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        System.out.println(LCS(N, nums));
    }


    private static int LCS(int N, int[] nums){
        int[] sorted = new int[N];
        sorted[0] = nums[0];

        int lastIndex = 0;

        if (N == 1){
            return lastIndex + 1;
        }

        for(int i = 1; i < N ; i++){
            int last = sorted[lastIndex];
            int num = nums[i];

            if(last < num){
                lastIndex +=1;
                sorted[lastIndex] = num;
            }else{
                int index = binarySearch(sorted, 0, lastIndex, num);
                sorted[index] = num;
            }
        }

        return lastIndex+1;
    }


    private static int binarySearch(int[] nums, int in_start, int in_end,  int num){
        int start = in_start;
        int end = in_end;

        while(start <= end){
            int mid = (start + end) / 2;
            int at = nums[mid];

            if(num == at){
                return mid;
            }else if(num < at){
                end = mid-1;
            }else{
                start = mid+1;
            }
        }
        return start;
    }
}
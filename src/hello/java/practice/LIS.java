package hello.java.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class LIS {
    public static void solution(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] nums = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int result = LIS(n, nums);
        System.out.println(result);
    }

    private static int LIS(int n, int[] nums){
        List<Integer> result = new ArrayList<>();

        result.add(nums[0]);


        for(int i = 1; i < n; i++){

            int num = nums[i];
            int last = result.get(result.size()-1);

            if (num > last){
                result.add(num);
            }else{
                int index = binaraySearch(result, num);
                result.set(index, num);
            }
        }

        return result.size();
    }


    private static int binaraySearch(List<Integer> list, int num){

        int start = 0;
        int end = list.size() - 1;

        int result = 0;

        while(start <= end){
            int mid = (start + end) / 2;
            int midNum = list.get(mid);

            if (midNum >= num){
                end = mid - 1;
                result = mid;
            }else{
                start = mid + 1;
            }
        }

        return result;
    }

}

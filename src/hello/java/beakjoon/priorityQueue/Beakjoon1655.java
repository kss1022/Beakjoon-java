package hello.java.beakjoon.priorityQueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;


public class Beakjoon1655{

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


    public static void main(String[] args) throws IOException {
        int N =  Integer.parseInt(br.readLine());
        printMiddlelNum(N);
    }


    //
    //  maxHeap Top -> mid
    //
    //  maxHeap 5 2 1 -99
    //  minHeap 5 7 10
    //

    private static void printMiddlelNum(int N) throws IOException {
        //descending
        Queue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());

        //ascending
        Queue<Integer> minHeap = new PriorityQueue<>(Comparator.naturalOrder());

        StringBuilder result = new StringBuilder();

        for(int i = 0; i < N; i++){
            int num = Integer.parseInt(br.readLine());
            if (maxHeap.size() == minHeap.size()){
                maxHeap.add(num);
            }else{
                minHeap.add(num);
            }

            if(!maxHeap.isEmpty() && !minHeap.isEmpty()
                    && maxHeap.peek() > minHeap.peek()){
                int popMax = maxHeap.poll();
                int popMin = minHeap.poll();

                maxHeap.add(popMin);
                minHeap.add(popMax);
            }

            result.append(maxHeap.peek() + "\n");
        }


        System.out.println(result);
    }
}

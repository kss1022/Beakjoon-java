package hello.java.beakjoon.topologicalSort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

class Beakjoon2252{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] input = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int N = input[0];
        int M = input[1];

        Student[] students = new Student[N+1];
        for(int i = 1; i <= N; i++){
            students[i] = new Student();
        }

        for(int i = 0; i < M; i++){
            int[] compare = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int front = compare[0];
            int back = compare[1];

            students[front].compares.add(back);
        }


        List<Integer> result = topologicalSort(N, M, students);
        String joined = result.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(" "));
        System.out.println(joined);
    }

    private static List<Integer> topologicalSort(int N, int M, Student[] students){
        HashSet<Integer> discovered = new HashSet<>();
        LinkedList<Integer> sortedList = new LinkedList<>();

        for(int i = 1; i <= N; i++){
            if(discovered.contains(i)){
                continue;
            }
            topologicalSortRecursive(i, discovered, sortedList, students);
        }

        return sortedList;
    }


    private static void topologicalSortRecursive(
            int student,
            HashSet<Integer> discovered,
            LinkedList<Integer> sortedList,
            Student[] students
    ){
        discovered.add(student);

        for(int front : students[student].compares){
            if(discovered.contains(front)){
                continue;
            }

            topologicalSortRecursive(front, discovered, sortedList, students);
        }

        sortedList.addFirst(student);
    }

    private static class Student{
        List<Integer> compares = new ArrayList<>();
    }
}
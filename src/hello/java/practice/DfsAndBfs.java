package hello.java.practice;


import java.io.*;
import java.util.*;
import java.util.stream.Collectors;


public class DfsAndBfs {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] nums = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int n = nums[0];
        int e = nums[1];
        int s = nums[2];

        List<List<Integer>> edges = new ArrayList<>();

        for (int i = 0; i < n + 1; i++){
            edges.add(new ArrayList<>());
        }


        for (int j = 0; j < e; j++){
            int[] edge = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int start = edge[0];
            int dest = edge[1];

            edges.get(start).add(dest);
            edges.get(dest).add(start);
        }



        List<Integer> dfsResult = dfs(n, s, edges);
        printResult(dfsResult);

        List<Integer> bfsResult = bfs(n, s, edges);
        printResult(bfsResult);
    }



    private static List<Integer> dfs(int n, int start, List<List<Integer>> edges){


        Stack<Integer> stack = new Stack();
        Boolean[] visited = new Boolean[n+1];
        Arrays.fill(visited, false);

        stack.push(start);


        List<Integer> result = new ArrayList<>();

        while(!stack.isEmpty()){
            int pop = stack.pop();

            if (visited[pop]){
                continue;
            }

            result.add(pop);
            visited[pop] = true;

            List<Integer> neighbors = edges.get(pop).stream()
                    .sorted(Comparator.reverseOrder())
                    .collect(Collectors.toList());

            for (int neighbor : neighbors){
                if (visited[neighbor]){
                    continue;
                }
                stack.push(neighbor);
            }
        }

        return result;
    }

    private static List<Integer> dfs2(int n, int start, List<List<Integer>> edges){
        Boolean[] visited = new Boolean[n+1];
        Arrays.fill(visited, false);

        List<Integer> result = new ArrayList<>();
        dfsRecursive(start, edges, result ,visited);
        return result;
    }

    private static void dfsRecursive(
            int vertex,
            List<List<Integer>> edges,
            List<Integer> result,
            Boolean[] visited
    ){
        visited[vertex] = true;
        result.add(vertex);

        List<Integer> neighbors = edges.get(vertex);

        for (int neighbor : neighbors){
            if(visited[neighbor]){
                continue;
            }

            dfsRecursive(neighbor, edges, result, visited);
        }
    }

    private static List<Integer> bfs(int n, int start, List<List<Integer>> edges){
        Queue<Integer> queue = new LinkedList<>();

        Boolean[] visited = new Boolean[n+1];
        Arrays.fill(visited, false);


        List<Integer> result = new ArrayList<>();

        queue.add(start);
        visited[start] = true;
        result.add(start);


        while(!queue.isEmpty()){
            int pop = queue.poll();


            List<Integer> neighbors = edges.get(pop)
                    .stream()
                    .sorted(Comparator.naturalOrder())
                    .collect(Collectors.toList());

            for (int neighbor : neighbors){
                if (visited[neighbor]){
                    continue;
                }

                visited[neighbor] = true;
                queue.add(neighbor);
                result.add(neighbor);
            }
        }

        return result;
    }


    static void printResult(List<Integer> result){
        StringBuilder sb = new StringBuilder();
        sb.append(result.get(0));

        for(int i = 1; i < result.size(); i++){
            sb.append(" ").append(result.get(i));
        }
        System.out.println(sb.toString());
    }




}
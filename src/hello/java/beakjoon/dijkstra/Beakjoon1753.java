package hello.java.beakjoon.dijkstra;

import java.io.*;
import java.util.*;



public class Beakjoon1753{


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] size = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int V = size[0];
        int E = size[1];

        int start = Integer.parseInt(br.readLine());

        List<Node>[] edges = new ArrayList[V+1];


        for(int i = 0; i <= V; i++){
            edges[i]  = new ArrayList<>();
        }

        for(int i = 0; i < E; i++){
            int[] edge = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int s = edge[0];
            int t = edge[1];
            int w = edge[2];

            edges[s].add(new Node(t, w));
        }

        int[] result = dijkstra(V, E , edges, start);
        for(int i = 1; i < result.length; i++){
            int weight = result[i];
            if(weight == Integer.MAX_VALUE){
                System.out.println("INF");
                continue;
            }

            System.out.println(weight);
        }
    }


    private static int[] dijkstra(
            int V,
            int E,
            List<Node>[] edges,
            int start
    ){
        int[] result = new int[V+1];
        Arrays.fill(result, Integer.MAX_VALUE);

        Queue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(start, 0));
        result[start] = 0;


        while(!queue.isEmpty()){
            Node pop = queue.poll();
            int v = pop.v;
            int w = pop.w;


            if(result[v] < w){
                continue;
            }

            List<Node> neighbors = edges[v];
            for(Node neighbor : neighbors){
                int nextVertex = neighbor.v;
                int nextDistance = neighbor.w + w;

                if(result[nextVertex] > nextDistance){
                    result[nextVertex] = nextDistance;
                    queue.add(new Node(nextVertex, nextDistance));
                }
            }
        }

        return result;
    }

    private static class Node implements Comparable<Node>{
        int v;
        int w;

        Node(int v, int w){
            this.v = v;
            this.w = w;
        }

        public int compareTo(Node o) {
            return Integer.compare(this.w, o.w);
        }
    }

}

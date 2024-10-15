package hello.java.beakjoon.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Beakjoon1238{

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] inputs = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int N = inputs[0];
        int M = inputs[1];
        int X = inputs[2] - 1;



        List<Edge>[] loads = new List[N];
        for (int i = 0; i < N; i++) {
            loads[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i++){
            int[] load = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int start = load[0] - 1;
            int dest = load[1] - 1;
            int weight = load[2];
            loads[start].add(new Edge(dest, weight));
        }

        int[] xToVertexs =  goParty(N, M, X, loads);

        int result = Integer.MIN_VALUE;
        for(int i = 0; i < N; i++){
            int[] vertexToXs = goParty(N, M, i, loads);
            int sum = xToVertexs[i] + vertexToXs[X];
            result = Math.max(result, sum);
        }

        System.out.println(result);
    }

    private static int[] goParty(int N, int M, int X, List<Edge>[] loads){

        int[] minDists = new int[N];
        int INF = Integer.MAX_VALUE;
        Arrays.fill(minDists, INF);
        minDists[X] = 0;

        PriorityQueue<Candidate> queue = new PriorityQueue<>();
        queue.add(new Candidate(X, 0));


        while(!queue.isEmpty()){
            Candidate pop = queue.poll();
            int vertex = pop.vertex;
            int dist = pop.distance;

            if (dist > minDists[vertex]){
                continue;
            }

            for (Edge edge : loads[vertex]){
                int nextVertex = edge.vertex;
                int nextDist = dist + edge.distance;

                if (minDists[nextVertex] <= nextDist){
                    continue;
                }

                minDists[nextVertex] = nextDist;
                queue.offer(new Candidate(nextVertex, nextDist));
            }
        }

        return minDists;
    }

    private  static class Edge{
        int vertex;
        int distance;

        public Edge(int vertex, int distance){
            this.vertex = vertex;
            this.distance = distance;
        }
    }

    private static class Candidate implements Comparable<Candidate>{
        int vertex;
        int distance;

        Candidate(int vertex, int distance){
            this.vertex = vertex;
            this.distance = distance;
        }

        public int compareTo(Candidate e){
            return this.distance - e.distance;
        }
    }
}

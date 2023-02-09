package SDS.DAY11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class B_10423 {
    static ArrayList<ArrayList<Edge>> arr;
    static int dist;
    static boolean[] visited;
    static PriorityQueue<Edge> pq;
    static int N, M, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        visited = new boolean[N + 1];
        pq = new PriorityQueue<>();
        arr = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            int bal = Integer.parseInt(st.nextToken());
            pq.offer(new Edge(bal, 0));
        }
        for (int i = 0; i < N + 1; i++) {
            arr.add(new ArrayList<>());
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            arr.get(a).add(new Edge(b, c));
            arr.get(b).add(new Edge(a, c));
        }
        dist = 0;

        while (!pq.isEmpty()) {
            Edge current = pq.poll();

            if (visited[current.node]) continue;
            visited[current.node] = true;
            dist += current.cost;

            for (Edge next : arr.get(current.node)) {
                pq.offer(new Edge(next.node, next.cost));
            }
        }
        System.out.println(dist);
    }

    static class Edge implements Comparable<Edge> {
        int node;
        int cost;

        public Edge(int node, int cost) {
            this.node = node;
            this.cost = cost;

        }

        @Override
        public int compareTo(Edge o) {
            return cost - o.cost;
        }
    }
}

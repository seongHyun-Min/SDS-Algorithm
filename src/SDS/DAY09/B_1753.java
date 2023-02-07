package SDS.DAY09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class B_1753 {
    static int V, E;
    static ArrayList<ArrayList<Node>> arr;
    static int[] dist;
    static boolean[] visited;
    static int start;

    static PriorityQueue<Node> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(br.readLine());
        dist = new int[V+1];
        visited = new boolean[V+1];
        arr =new ArrayList<>();
        for(int i=0; i< V+1; i++){
            arr.add(new ArrayList<>());
            dist[i] = Integer.MAX_VALUE;
        }
        for(int i=0; i<E; i++){
            st=  new StringTokenizer(br.readLine());
            int a =Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            arr.get(a).add(new Node(b, cost));
            // a -> b 로 가는 경로
        }
        pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        dist[start] =0;

        while(!pq.isEmpty()){
            //방문 체크
            Node current = pq.poll();

            if(visited[current.node]) continue;
            visited[current.node] = true;

            for(Node next: arr.get(current.node)){
                if(dist[next.node] > dist[current.node] + next.cost){
                    dist[next.node] = dist[current.node] +next.cost;

                    pq.offer(new Node(next.node, dist[next.node]));
                }

            }
        }
        for(int i=1; i<dist.length; i++){
            if(dist[i] ==Integer.MAX_VALUE){
                System.out.println("INF");
            }else System.out.println(dist[i]);
        }



    }
    static class Node implements Comparable<Node>{
        int node;
        int cost;

        public Node(int node, int cost){
            this.node =node;
            this.cost =cost;
        }

        @Override
        public int compareTo(Node o) {
            return cost -o.cost;
        }
    }
}

package SDS.DAY09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class B_1922 { //크루스칼 알고리즘
    static int[] parent;
    static ArrayList<Edge> edgeList;
    static int N, M;
    static int minSum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        edgeList = new ArrayList<>();
        parent = new int[N + 1];
        for(int i=1; i<N+1; i++){
            parent[i] = i;
        }
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            edgeList.add(new Edge(A, B, cost));
        }
        Collections.sort(edgeList); //간선의 가중치가 낮은거 부터 정렬
        minSum =0;
        for(int i=0; i<edgeList.size(); i++){
            Edge edge = edgeList.get(i);

            //사이클이 발생하는 간선은 제외
            if(find(edge.A) != find(edge.B)){
                minSum += edge.cost;
                union(edge.A, edge.B);
            }
        }
        System.out.println(minSum);
    }


    static void union(int a, int b){
        int rootA = find(a);
        int rootB = find(b);
        parent[rootB] = rootA;
    }

    static int find(int a) {
        if (parent[a] == a) {
            return a;
        }else{
            return parent[a] = find(parent[a]);
        }
    }

    static class Edge implements Comparable<Edge>{
        int A;
        int B;
        int cost;

        public Edge(int A, int B, int cost) {
            this.A = A;
            this.B = B;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return cost - o.cost; //오름 차순 정렬
        }
    }
}




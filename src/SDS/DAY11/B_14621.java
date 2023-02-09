package SDS.DAY11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//기본 프림알고리즘 응용
public class B_14621 {
    static ArrayList<ArrayList<Edge>> arr;
    static boolean[] visited;

    static String[] sex;
    static int N, M;

    static PriorityQueue<Edge> pq;
    static int dist;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visited=  new boolean[N +1];
        sex = new String[N +1];
        arr = new ArrayList<>();
        for(int i=0; i<N+1; i++){
            arr.add(new ArrayList<>());
        }
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<N+1; i++){
            sex[i] = st.nextToken();

        }
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            arr.get(a).add(new Edge(b ,c ,sex[b]));
            arr.get(b).add(new Edge(a, c, sex[a]));
        }
        //
        dist =0;
        pq = new PriorityQueue<>();
        pq.offer(new Edge(1 , 0, sex[1]));

        while(!pq.isEmpty()){
            Edge current = pq.poll();

            //여기서 방문했으면 두번은 방문안해
            if(visited[current.node]) continue;
            visited[current.node] =true;


            dist += current.cost;


            for(Edge next : arr.get(current.node)){
                if(!current.sex.equals(next.sex)){
                    pq.offer(new Edge(next.node, next.cost, sex[next.node]));
                }
            }
        }
        for(int i=1; i<N +1; i++){
            if(!visited[i]){
                dist = -1;
                break;
            }
        }
        // visited 모두 돌았는지 확인후 못돌았으면 -1 리턴
        System.out.println(dist);

    }

    static class Edge implements Comparable<Edge>{
        int node;
        int cost;

        String sex;

        public Edge(int node, int cost, String sex){
            this.node =node;
            this.cost =cost;
            this.sex = sex;

        }

        @Override
        public int compareTo(Edge o) {
            return cost -o.cost;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "node=" + node +
                    ", cost=" + cost +
                    '}';
        }
    }
}

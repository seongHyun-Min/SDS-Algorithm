package SDS.DAY08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class B_6497 { //프림 알고리즘
    static int m, n; //m 노드 n 엣지
    static ArrayList<ArrayList<Node>> arr;
    static int maxCost; //변수 항상 초기화
    static boolean[] visited;
    static PriorityQueue<Node> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            m = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());
            if (m == 0 && n == 0) {
                break;
            }
            arr = new ArrayList<>();
            for (int i = 0; i < m; i++) { //0번부터 시작하기 때문에 m+1까지 받을필요없음
                arr.add(new ArrayList<>());
            }
            visited = new boolean[m];
            maxCost = 0;
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());
                maxCost += cost; //최대비용에 cost 누적 - 최소거리
                //무향 그래프 삽입
                arr.get(a).add(new Node(b, cost));
                arr.get(b).add(new Node(a, cost));
            }
            pq = new PriorityQueue<>();
            pq.offer(new Node(0, 0));

            while (!pq.isEmpty()) {
                Node current = pq.poll();
                if (visited[current.node]) continue; //방문했으면 continue
                visited[current.node] = true;
                maxCost -= current.cost;

                for (Node next : arr.get(current.node)) {
                    if (!visited[next.node]) {
                        pq.offer(new Node(next.node, next.cost));
                    }
                }
            }
            System.out.println(maxCost);
        }


    }

    static class Node implements Comparable<Node> {
        int node;
        int cost;

        public Node(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return cost - o.cost;
        }
    }
}

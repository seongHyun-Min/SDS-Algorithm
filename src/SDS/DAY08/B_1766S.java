package SDS.DAY08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class B_1766S {
    //우선 순위 큐를 활용하자
    static int[] inDegree;
    static ArrayList<ArrayList<Integer>> arr;
    static PriorityQueue<Integer> q;
    static int N, M;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new ArrayList<>();
        for (int i = 0; i < N + 1; i++) {
            arr.add(new ArrayList<>());
        }
        inDegree = new int[N + 1];
        sb = new StringBuilder();
        //sb를 쓸지 result를 쓸지 ???
        for (int i = 1; i < M + 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            //B를 해결하기 위해서는 반드시 a를 풀어야해 a -> B 로 삽입
            arr.get(a).add(b);
            inDegree[b]++;
        }
        q = new PriorityQueue<>();
        for (int i = 1; i < N + 1; i++) {
            //inDegree가 없는것들을 먼저 큐에 담아야 해
            if (inDegree[i] == 0) {
                //여기서 하나만 넣어야해
                q.offer(i); // 숫자가 낮은거 부터 들어가고
            }
        }
        while (!q.isEmpty()) {
            int current = q.poll();
            sb.append(current + " ");

            for (int next : arr.get(current)) {
                inDegree[next]--;
                if (inDegree[next] == 0) {
                    q.offer(next);
                }
            }
        }
        System.out.println(sb.toString());
    }
}

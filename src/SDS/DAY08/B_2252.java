package SDS.DAY08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_2252 {
    static int[] outDegree;
    static ArrayList<ArrayList<Integer>> arr;
    static int N, M;

    static Queue<Integer> q;

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new ArrayList<>();
        for (int i = 0; i < N+1; i++) {
            arr.add(new ArrayList<>());
        }
        outDegree = new int[N + 1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr.get(a).add(b); // A <- B
            outDegree[b] ++;
        }
        q = new LinkedList<>();
        for(int i=1; i<=N; i++){
            if(outDegree[i] ==0){
                q.offer(i); //큐에 넣어서 출력
            }
        }
        while(!q.isEmpty()){
            int current = q.poll();
            sb.append(current + " "); //큐에서 뺀 순서로 넣어줘
            for(int i: arr.get(current)){
                outDegree[i] --;
                if(outDegree[i] ==0){
                    q.offer(i);
                }
            }
        }
        System.out.println(sb.toString());
    }
}

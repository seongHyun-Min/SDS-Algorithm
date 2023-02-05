package SDS.DAY01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B_15649 {
    //스트링은 더하기 금지

    static int N, M;
    static boolean[] visited;

    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visited = new boolean[N+1];
        arr = new int[M];
        sb.append("123");
        sb.append("안녕");
        System.out.println(sb.toString());
        visited[0] = true;
        BFS(0, 0);
    }
    static void BFS(int start, int count){
        if(count ==M){
            for(int i=0; i<M; i++){
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }
        else{
            for(int i=1; i<=N; i++){
                if(!visited[i]){
                    arr[count] = i;
                    visited[i] = true;
                    BFS(i+1, count +1);
                    visited[i] = false;
                }
            }
        }
    }
}

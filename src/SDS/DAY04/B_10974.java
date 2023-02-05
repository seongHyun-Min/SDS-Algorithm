package SDS.DAY04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B_10974 {
    static int N;
    static boolean[] visited;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N+1];
        visited = new boolean[N+1];
        DFS(0);
    }
    static void DFS(int count){
        if(N == count) {
            for (int i = 0; i < N; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }else{
            for(int i=1; i<=N; i++){
                if(!visited[i]){
                    visited[i] = true;
                    arr[count] = i;
                    DFS(count + 1);
                    visited[i] = false;

                }
            }
        }
    }
}

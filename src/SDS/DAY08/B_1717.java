package SDS.DAY08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B_1717 {
    static int N, M;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); //집합크기
        M = Integer.parseInt(st.nextToken());
        parent = new int[N + 1];
        for (int i = 1; i < N + 1; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if(a ==0){
                union(b, c);
            }else{
                if(check(b, c)){
                    System.out.println("yes");
                }else System.out.println("no");
            }
        }
    }
    static void union(int a, int b){
        int rootA = find(a);
        int rootB = find(b);
        parent[rootB] = rootA;
    }
    static int find(int a){
        if(parent[a] == a){
            return a;
        }else{
            return parent[a] = find(parent[a]);
        }
    }
    static boolean check(int a, int b){
        int rootA = find(a);
        int rootB = find(b);
        return rootA == rootB;
    }
}

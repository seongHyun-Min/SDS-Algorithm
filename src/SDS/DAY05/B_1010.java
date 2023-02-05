package SDS.DAY05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_1010 {
    static int[][] dp;
    static int T;
    static int N, M; //mCn
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());
        for(int i=0; i<T; i++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            dp = new int[M+1][N+1];
            System.out.println(solve(M,N));
        }
    }
    static int solve(int M, int N){
        if(dp[M][N] >0){
            return dp[M][N];
        }else if(M == N || N ==0){
            return 1;
        }else{
            return dp[M][N] = solve(M-1, N-1) + solve(M-1, N);
        }

    }
}

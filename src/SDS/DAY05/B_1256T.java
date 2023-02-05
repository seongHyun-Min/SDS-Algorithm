package SDS.DAY05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_1256T {
    static int N, M, K;
    static int[][] dp;

    static final int maxK = 1000000000;
    static StringBuilder sb = new StringBuilder();


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        dp = new int[201][201];

        if(K > combination(N + M , M)){
            System.out.println(-1);
        }else{
            query(N, M, K);
            System.out.println(sb.toString());
        }


    }
    public static void query(int n, int m, int k){
        if(n + m ==0){
            return ;
        }else if(n==0){
            sb.append("z");
            query(n, m-1, k);
        }else if(m ==0){
            sb.append("a");
            query(n-1 , m, k);
        }else{
            int limit = combination(n+m-1, m);
            if(limit >= k){
                sb.append("a");
                query(n -1 , m , k);
            }else{
                sb.append("z");
                query(n, m-1, k-limit);
            }
        }

    }

    public static int combination(int n, int r){
        if (n == r || r ==0){
            return dp[n][r] =1;
        } else if(dp[n][r] != 0){
            return dp[n][r];
        }else{
            return dp[n][r] = (int) Math.min(1e9,  combination(n-1, r-1 ) + combination(n -1, r));
        }
    }
}
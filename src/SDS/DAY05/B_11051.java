package SDS.DAY05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_11051 {
    static int N, K;

    static int DP[][];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        DP = new int[N+1][K+1];
        System.out.println(pascal(N, K));

    }
    static int pascal(int N, int K){
        if (DP[N][K] >0) {
            return DP[N][K];
        } else if (N ==K || K==0){
            return 1;
        }else{
            return DP[N][K] = (pascal(N-1, K-1) + pascal(N-1 ,K)) % 10007;
        }
    }
}

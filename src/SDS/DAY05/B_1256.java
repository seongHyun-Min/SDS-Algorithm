package SDS.DAY05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_1256 {
    static int N, M, K;
    static int[][] dp;

    static final int maxK = 1000000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
// 2 2
        dp = new int[N + M + 1][M + 1];
        if (pascal(N + M, M) < K) {
            System.out.println(-1);
        } else System.out.println(solve(N + M, M, K, ""));

    }

    static int pascal(int n, int r) {
        if (dp[n][r] > 0) {
            return dp[n][r];
        } else if (n == r || r == 0) {
            return dp[n][r] = 1;
        } else {
            int tmp = pascal(n - 1, r - 1) + pascal(n - 1, r);
            if (tmp >= maxK) {
                return dp[n][r] = maxK;
            } else return dp[n][r] = tmp;
        }
    }

    static String solve(int n, int r, int count, String str) {
        String tmp = "";
        if (n == 0 && r == 0 || count == 0) {
            return tmp + str;
        } else if (n == 0) {
            return solve(n, r - 1, count, str + "z");
        } else if (r == 0) {
            return solve(n - 1, r, count, str + "a");
        } else {
            //위로 가면 a
            if (count <= dp[n - 1][r]) {//경계 지점 limit
                str += "a";
                return solve(n - 1, r, count, str);
            } else { //아래로 가면 z
                //경계 지점만큼 빼줘야해
                str += "z";
                return solve(n - 1, r - 1, count - dp[n - 1][r], str);
            }
        }
    }
}

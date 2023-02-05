package SDS.DAY05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_1722 {
    static boolean[] visited;

    static int[] map;

    static long[] factorial;
    static int N;
    static int M;
    static long K;

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        map = new int[N];
        visited = new boolean[N + 1];
        factorial = new long[N + 1];
        factorial[0] = 1;
        for (int i = 1; i <= N; i++) {
            factorial[i] = factorial[i - 1] * i;
        }

        if (M == 1) {
            K = Long.parseLong(st.nextToken());
            query(K);
            for (int i : map) {
                System.out.print(i + " ");
            }
        } else {
            for (int i = 0; i < N; i++) {
                map[i] = Integer.parseInt(st.nextToken());
            }
            System.out.println(queryK());
        }
    }

    static void query(long K) {
        for (int i = 0; i < N; i++) {
            // 1~ N 확인
            for (int j = 1; j <= N; j++) {
                if (!visited[j]) {
                    if (factorial[N - i - 1] < K) {
                        K -= factorial[N - i - 1];
                    } else {
                        map[i] = j;
                        visited[j] = true;
                        break;
                    }
                }
            }
        }
    }

    static long queryK() {
        long ans = 1;
        for (int i = 0; i < N; i++) {
            // 1~ N 확인
            for (int j = 1; j < map[i]; j++) {
                if (!visited[j]) {
                    ans += factorial[N - i - 1];
                }
                }
            visited[map[i]] =true;
            }
        return ans;
    }
}

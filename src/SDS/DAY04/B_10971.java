package SDS.DAY04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_10971 {
    static int N;
    static int[][] map;
    static boolean[] visited;

    static int min;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        map = new int[N + 1][N + 1];
        visited = new boolean[N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        min = Integer.MAX_VALUE;
        for (int i = 1; i < N; i++) {
            visited[i] = true;
            DFS(i, i, 1, 0);
        }
        System.out.println(min);
    }

    static void DFS(int start, int now, int count, int dist) {
        if (count == N) {
            if (map[now][start] != 0) { //돌아오는길이 0이 될 수 도 있어
                min = Math.min(min, dist + map[now][start]);

            }

        } else {
            for (int i = 1; i <= N; i++) {//3. 갈수있는곳?
                //갈수 있니?
                if (!visited[i] && map[now][i] != 0) {
                    visited[i] = true;
                    DFS(start, i, count + 1, dist + map[now][i]);
                    visited[i] = false;
                }
            }
        }
    }
}




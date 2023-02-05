package SDS.DAY06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_17142 {
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] map;
    static int N, M;
    static ArrayList<Point> virus;
    static boolean[] visited;
    static int ans;
    static int blank;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        ans = Integer.MAX_VALUE;
        map = new int[N][N];
        virus = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int tmp = Integer.parseInt(st.nextToken());
                map[i][j] = tmp;
                if (tmp == 2) {
                    virus.add(new Point(i, j, 0)); //바이러스면 바이러스 리스트에 담기
                } else if (tmp == 0) {
                    blank++; //퍼트릴수 있는 공간 count
                }
            }
        }
        visited = new boolean[virus.size()];

        DFS(0, 0);
        if (ans == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else System.out.println(ans);


    }

    static void DFS(int start, int count) {
        //2. count 계수를 만나면
        if (count == M) { //바이러스 조합이 선택되면 BFS를 통해 바이러스가 있게 되는 시간을 뽑아내자
            BFS();
        } else {
            for (int i = start; i < virus.size(); i++) { //바이러스 탐색후 바이러스 투첫
                if (!visited[i]) {
                    visited[i] = true;
                    DFS(i + 1, count + 1);
                    visited[i] = false;
                }
            }
        }
    }

    static void BFS() {
        Queue<Point> q = new LinkedList<>();
        boolean[][] visit = new boolean[N][N];
        int value = 0;
        int zeroCount = 0;
        for (int i = 0; i < virus.size(); i++) {
            if (visited[i]) { //조합에서 고른 바이러스를 큐에 너어줘
                int x = virus.get(i).x;
                int y = virus.get(i).y;
                visit[x][y] = true;
                q.offer(new Point(x, y, 0));
            }
        }

        while (!q.isEmpty()) {
            Point current = q.poll();
            for (int i = 0; i < 4; i++) {
                int x = dx[i] + current.x;
                int y = dy[i] + current.y;
                int time = current.time;

                if (x >= 0 && x < N && y >= 0 && y < N && map[x][y] != 1) {
                    if (!visit[x][y]) { //방문 안한경우에만 DFS 실행
                        if (map[x][y] == 0) {
                            //퍼트릴수 있는공간 blank ==zeroCount가 항상 같아야함
                            zeroCount++;
                            value = time + 1;
                        }
                        visit[x][y] = true;
                        q.offer(new Point(x, y, time + 1));
                    }
                }
            }
            }
        if (zeroCount == blank) {
            ans = Math.min(ans, value);
        }
    }

    static class Point {
        int x;
        int y;
        int time;

        public Point(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }
}

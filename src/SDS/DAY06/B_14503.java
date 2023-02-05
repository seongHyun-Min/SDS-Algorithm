package SDS.DAY06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_14503 {
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int N, M;
    static int[][] map;
    static int r, c, d;

    static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        count = 1; //처음 로봇이 들어갈때 count 시켜줘야해
        DFS(r, c, d);
        System.out.println(count);
    }

    static void DFS(int r, int c, int direction) {
        map[r][c] = 2; //2로 방문처리
        for (int i = 0; i < 4; i++) { //2. 왼쪽 방향에 청소할 공간이 없다면 자동으로 그방향으로 회전
            direction = (direction + 3) % 4; //왼쪽으로 방향 탐색
            int x = dx[direction] + r;
            int y = dy[direction] + c;
            if (x >= 0 && x < N && y >= 0 && y < M && map[x][y] == 0) {
                //왼쪽방향에 아직 청소하지 않은 공간이 존재한다면
                // 그방향으로 회전한 다음 한 칸을 전진하고 1번부터다시 DFS 진행
                count++;
                DFS(x, y, direction); //direction 현재 그방향
                //1. 그 방향으로 회전한 다음 한 칸을 전진했으니
                //그 방향으로 회전하고 2번으로 돌아갈 필요 없어
                return;
            }
        }
        //네방향 모두 청소가 되어 있는경우거나 청소가 되어 있는 경우임
        //한칸 후진 해야돼 즉 아래를 보고 있으면 위로 한칸이동
        //오른쪽을 보고 있으면 오른쪽으로 이동
        int tmp = (direction + 2) % 4;
        int bx = dx[tmp] + r;
        int by = dy[tmp] + c;
        if (bx >= 0 && bx < N && by >= 0 && by < M && map[bx][by] != 1) {
            DFS(bx, by, direction);
        }
    }
}

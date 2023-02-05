package SDS.DAY01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_3055 {

    static String str = "KAKTUS";
    static int R, C;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static char[][] map;
    static int[][] dp; //고슴도치가 처리한 거리 0, 1 ,2 순으로 잡고
    //물이 이동한거리를 -1로 잡을수 있지만
    // map배열에서 . 대신에 *로 다이렉트로 체크하는것이 좋다.

    // 큐에 고슴도치가 아닌 물을 먼져 넣어야한다.
    // 그렇게 되면 고슴도치는 물이 찰 예정인 칸으로 이동할수 없기 때문이다.
    static Queue<Point> q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        dp = new int[R][C];
        q = new LinkedList<>();
        //배열을을 받을때는 r이랑 c로 선언하자 i랑 j는 현장에서 구분하기 힘들다.
        for (int i = 0; i < R; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = tmp.charAt(j);
                if (map[i][j] == '*') {
                    q.offer(new Point(i, j, '*'));
                    //큐에다 물을 우선으로 넣자

                }
            }
        }
        //큐에 물 집어넣었으면 고슴도치 집어넣자
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                dp[i][j] = -1; //초기 값을 -1로 해서 방문했는지 확인 처리
                if (map[i][j] == 'S') {
                    q.offer(new Point(i, j, 'S'));
                    dp[i][j] = 0; //고슴도치는 0부터 시작

                }
            }
        }


        //큐에서 꺼내기

        while (!q.isEmpty()) {


            //무한 루프 돌자나
            //1. 큐에서 꺼냄
            Point current = q.poll();
            //2. 목적지 인가?
            // 고슴도치만 D에 도착
            //3. 연결된 곳을 순회 -> 좌 우 위 아래
            for (int i = 0; i < 4; i++) {
                int x = dx[i] + current.x;
                int y = dy[i] + current.y;

                //4. 갈수 있는가
                //공통으로 갈 수없는곳 생략
                if (x < 0 || x >= R || y < 0 || y >= C || map[x][y] == 'X') continue;
                //현재 물인가?
                //물이면서 D가 아니면 다갈수있어
                if (current.type == '*' && map[x][y] == '.') {
                    map[x][y] = '*'; //5. 체크인
                    q.offer(new Point(x, y, '*'));
                }//현재 고슴도치인가?
                else if (current.type == 'S' && dp[x][y] == -1 && map[x][y] != '*') {//간 방향은 가면 안돼
                    dp[x][y] = dp[current.x][current.y] + 1; //5.체크인
                    q.offer(new Point(x, y, 'S'));
                }
            }
        }
        //2 목적지 인가??
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 'D') {
                    if (dp[i][j] == -1) {
                        System.out.println(str);
                    } else System.out.println(dp[i][j]);
                }
            }
        }


    }

    static class Point {
        int x;
        int y;
        char type; //물인지 고슴도치인지 구분해야지

        public Point(int x, int y, char type) {
            this.x = x;
            this.y = y;
            this.type = type;
        }
    }
}

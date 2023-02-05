package SDS.DAY01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_1987 {

    static int R, C;
    static int[][] map;
    static boolean[] visit = new boolean[26];
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };
    static int ans = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = str.charAt(j) - 'A';
            }
        }
        visit[map[0][0]] =true;
        dfs(0, 0, 0);
        System.out.println(ans);
    }
    public static void dfs(int x, int y, int count) {
        //2. 조건에 만족하냐
        ans = Math.max(ans, count +1);
         //3.갈수있는곳 for문
            for (int i = 0; i < 4; i++) {
                int cx = x + dx[i];
                int cy = y + dy[i];

                if (cx >= 0 && cy >= 0 && cx < R && cy < C) { //4.갈수있냐?
                    //5. 간다
                    if (!visit[map[cx][cy]]){
                        visit[map[cx][cy]] = true;
                        dfs(cx, cy, count + 1);
                        visit[map[cx][cy]] = false;
                    }
                }
            }
        }
    }

package SDS.DAY01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class B_1062 {
    static int N, K;
    //a n t i c
    static boolean[] visited;

    static String[] words;

    static int possible;

    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        if (K < 5) {
            System.out.println(0);
            System.exit(0);
        }
        visited = new boolean[26];
        visited['a' - 'a'] = true;
        visited['n' - 'a'] = true;
        visited['t' - 'a'] = true;
        visited['i' - 'a'] = true;
        visited['c' - 'a'] = true;

        words = new String[N];

        for (int i = 0; i < N; i++) {
            String tmp = br.readLine();
            words[i] = tmp.substring(4, tmp.length() - 4);
        }

        possible = K - 5;
        answer = 0;

        DFS(0, 0);

        System.out.println(answer);
    }

    public static void DFS(int start, int depth) {
        //2. 정답을 만났을때 출력
        if (depth == possible) {
            int max = 0;
            for (int i = 0; i < N; i++) {
                boolean isTrue = true;
                for (int j = 0; j < words[i].length(); j++) {
                    if (!visited[words[i].charAt(j) - 'a']) {
                        isTrue = false;
                        break;
                    }
                }
                if (isTrue) max++;
            }
            answer = Math.max(answer, max);
        } else { //3.for문을 통해 갈 수 있는 방향 탐색
            for (int i = start; i < 26; i++) {
                if (!visited[i]) { //4. 갈수 있냐?
                    System.out.println("i = " + i);
                    //ident 반드시 중요 DFS 전에 true
                    //DFS 전에 1번 체크인
                    visited[i] = true;
                    //5. 간다
                    DFS(i + 1, depth + 1);
                    //6. 체크아웃
                    visited[i] = false;
                }
            }

        }

    }

}

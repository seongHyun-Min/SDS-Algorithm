package SDS.DAY01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B_1062T {
    static boolean[] visited;
    static int N, K;
    static String[] word;

    static int selectedCount;

    static int max;


    public static void main(String[] args) throws IOException {
        //6.체크 아웃
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        word = new String[N];
        max = 0;
        if (K < 5) {
            System.out.println(0);
            System.exit(0);
        }
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String str = st.nextToken();
            word[i] = str.replaceAll("[antic]", "");
        }
        visited = new boolean[26]; //알파벳 26개
        //방문 할필요 없는 a, c, i, n, t
        //아스키 코드를 사용하여 'a' 를 0번으로 사용
        visited['a' - 'a'] = true;
        visited['c' - 'a'] = true;
        visited['i' - 'a'] = true;
        visited['n' - 'a'] = true;
        visited['t' - 'a'] = true;

        selectedCount = K - 5;


        DFS(0, 0);

        System.out.println(max);
    }



    static public void DFS(int start, int count) {
        //1. 체크인 -> visited, selectedCount (체크인과 체크아웃의 indent는 같아야한다)
        //2. 목적지인가 -> SelectedCount == k -> 읽을 수 있는 단어 계산
        if (count == selectedCount) { //모든 경우의수가 여기로 도착
            max = Math.max(countReadable(), max);

        } else {
            //2. 여기서 체크인?
            visited[start] = true;
            System.out.println("B_1062T.DFS");
            //3. 연결된 곳을 순회
            for (int i = start + 1; i < 26; i++) { //abcd를 고른것과 bacd를 고른것의 결과가 같기 때문에 현재 start부터 시작하는것과 결과가 같다.
                //4. 갈수 있는가 visited
                if (!visited[i]) {
                    //5. 간다
                    DFS(i, count + 1);
                    System.out.println("B_1062T.DFS");
                }
            }
            visited[start] = false;

        }
        //6.체크 아웃 -> visited, selectedCount

    }
    static int countReadable(){
        int count = 0;
        for(int i=0; i<N; i++){
            boolean isTrue = true;
            for(int j=0; j<word[i].length(); j++){
                if(!visited[word[i].charAt(j) - 'a']){
                    isTrue =false;
                    break;
                }
            }
            if(isTrue){
                count ++;
            }
        }
        return count;
    }
}


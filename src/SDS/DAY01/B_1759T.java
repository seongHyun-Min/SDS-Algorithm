package SDS.DAY01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B_1759T {
    static int L, C;
    static char[] word;




    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        word = new char[C];
        for (int i = 0; i < C; i++) {
            word[i] = st.nextToken().charAt(0);
        }
        Arrays.sort(word);
        for (int i = 0; i < C; i++) {
            if (word[i] == 'a' || word[i] == 'e' || word[i] == 'i'
                    || word[i] == 'o' || word[i] == 'u') { //자음이니??
                dfs(i, 1, 1, 0 , "" + word[i]);
            } else {
                dfs(i, 1, 0, 1, "" + word[i]);
            }
        }

    }








    //start를 제외한 값들을 static으로 뺼수 있으며 1체크인과 6 체크아웃에서 설정해줘야한다
    static void dfs(int start, int length, int vowels, int consonants, String pwd) {
        //1. 체크인 -> 생략가능
        //생략가능

        //2. 목적지인가 length == L -> 자, 모 개수
        if (length == L) {
            if (vowels >= 1 && consonants >= 2) {
                System.out.println(pwd);
            }
        } else {
            //3. 연결된곳 현재보다 큰것부터 마지막까지
            for (int i = start + 1; i < C; i++) {
                //4. 갈수 있는가 - 생략가능
                //5 간다
                if (word[i] == 'a' || word[i] == 'e' || word[i] == 'i'
                        || word[i] == 'o' || word[i] == 'u') { //자음이니??
                    dfs(i, length + 1, vowels + 1, consonants, pwd + word[i]);
                } else dfs(i, length + 1, vowels, consonants + 1, pwd + word[i]);

            }
        }
    }
    //6 체크아웃 -생략가능
}

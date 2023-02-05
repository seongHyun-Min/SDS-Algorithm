package SDS.DAY01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B_1759 {
    static int L, C;
    static char[] word;

    static int depth;

    static ArrayList<String> answer;

    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        word= new char[C];
        visited = new boolean[C];
        answer = new ArrayList<>();
        for(int i=0; i<C; i++){
            word[i] = st.nextToken().charAt(0);
        }
        Arrays.sort(word);
        depth =0;
        for(int i=0; i<C; i++){
            if(!visited[i]){
                if(word[i] =='a' || word[i] == 'e' || word[i] =='i'
                        || word[i] =='o'|| word[i] =='u'){
                    dfs(i, 1, 0);
                }else dfs(i, 0, 1);
            }
        }
    }

    static void dfs(int start, int vowels, int consonants){
        //1. 체크인
        visited[start] =true;
        depth ++;
        //생략가능

        //2. 목적지인가
        if(depth ==L){
            if(vowels >= 1 && consonants >= 2){
                for(int i=0; i<C; i++){
                    if(visited[i]){
                        System.out.print(word[i]);
                    }
                }
                System.out.println();

            }
        }
        else{
            //3. 연결된곳
            for(int i=start+1; i<C; i++){
                if(!visited[i]){        //4. 갈수 있는가
                    //5 간다
                    if(word[i] =='a' || word[i] == 'e' || word[i] =='i'
                            || word[i] =='o'|| word[i] =='u'){
                        dfs(i, vowels+1, consonants);
                    }else dfs(i, vowels, consonants+1);
                }
            }
        }
        //6 체크아웃
        visited[start] =false;
        depth --;

    }
}

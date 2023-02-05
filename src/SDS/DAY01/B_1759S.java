package SDS.DAY01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B_1759S {
    static int L, C;
    static char[] word;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        word = new char[C];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<C; i++){
            word[i] = st.nextToken().charAt(0);
        }
        Arrays.sort(word);
        DFS(-1, 0, 0, 0, "");


    }
    public static void DFS(int start, int depth, int vow, int con, String tmp){
        if(depth ==L){
            //목적지면 정답 출력
            if(vow >= 1 && con >= 2){
                System.out.println(tmp);
            }
        }
        else{
            for(int i= start+1; i<C; i++){ //백트래킹
                if(word[i] == 'a' || word[i] == 'e' || word[i] == 'i' || word[i] == 'o' || word[i] == 'u'){
                    DFS(i, depth +1 , vow +1 , con, tmp + word[i]);
                }else DFS(i, depth +1, vow, con+1, tmp + word[i]);
            }
        }
    }
}

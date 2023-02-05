package SDS.DAY02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B_2805 {
    static int[] tree;
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        tree = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            tree[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(tree);

        int start =0;
        int end = tree[N-1];
        int ans = 0;

        while(start <= end){
            long sum =0;
            int mid = (start + end) /2;
            for(int i=0; i<N; i++){
                long tmp = tree[i] - mid;
                if(tmp >0) sum += tmp;
            }
            if(sum >= M){
                ans = Math.max(mid, ans);
                start = mid+1;
            }else{
                end = mid-1;
            }

        }
        System.out.println(ans);

    }
}

package SDS.DAY03.indextree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B_2042N {
    static long[] tree;
    static int N, M, K;
    static int S;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        S = 1;
        while(S < N){
            S *= 2;
        }
        tree = new long[S * 2];
        for(int i=S; i<S + N; i++){
            st = new StringTokenizer(br.readLine());
            tree[i] = Integer.parseInt(st.nextToken());
        }
        for(int i=S-1; i>0; i--){
            tree[i] = tree[i * 2] + tree[i *2 + 1];
        }
        System.out.println(Arrays.toString(tree));
        //문제 없음



        for(int i=0; i<M+K; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if(a ==1){
                //이 경우에서 right 값을 확인해야하는데
                long diff = c - tree[b + S -1]; //여기에서 right가 변경값

                update(1, S, 1, b, diff); //여기에서 left가 target
                System.out.println(Arrays.toString(tree));
            }else System.out.println(query(1, S, 1, b, c));
        }
    }
    static long query(int left, int right, int node, int queryLeft, int queryRight){
        // 1. 연관 없음
        if(queryRight <left || right < queryLeft){
            return 0;
        }
        // 2. 판단 가능( 쏙 들어감 )
        else if(queryLeft <= left && right <= queryRight){
            return tree[node];
        }
        // 3. 판단 불가 (걸쳐 있음)
        else{
            int mid = (left + right) / 2;
            long leftResult = query(left, mid, node * 2, queryLeft, queryRight);
            long rightResult = query(mid+1, right, node* 2 +1 , queryLeft, queryRight);
            return leftResult + rightResult;
        }
    }
    static void update(int left, int right, int node, int target, long diff){ //diff는 구해야함 함수 실행전에
        // 1. 연관 없음
        if(target < left || right < target){
            return;
        }else {        // 2. 연관 있음
            tree[node] += diff;
            if(left != right){
                int mid = (left + right) /2;
                update(left, mid, node*2, target, diff);
                update(mid+1, right, node*2+1, target, diff);
            }

            // 2-1 내부 노드냐?
            // 2-2 리프 노드냐?
        }

    }
}

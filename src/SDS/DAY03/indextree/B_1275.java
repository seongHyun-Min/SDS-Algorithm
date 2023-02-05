package SDS.DAY03.indextree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_1275 {
    static long[] tree;
    static int N, M;
    static int S;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=  new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        S =1;
        while(S < N){
            S *=2;
        }
        tree = new long[2 * S];
        st = new StringTokenizer(br.readLine());

        for(int i=S; i<S+N; i++){
            tree[i] = Integer.parseInt(st.nextToken());
        }
        for(int i=S-1; i>0; i--){
            tree[i] = tree[i * 2] + tree[i * 2+ 1];
        }
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if(x > y){
                System.out.println(query(1, S, 1, y, x));
            }else {
                System.out.println(query(1, S, 1, x, y));
            }
            long diff = b - tree[S + a -1];
            update(1, S, 1, a, diff);
        }
    }
    static long query(int left, int right, int node, int queryLeft, int queryRight){
        if(right < queryLeft || queryRight < left){
            return 0;
        }else if (queryLeft <= left && right <= queryRight){
            return tree[node];
        }else{
            int mid = (left + right) /2;
            long leftNode = query(left, mid, node *2, queryLeft, queryRight);
            long rightNode = query(mid +1, right, node *2 +1 , queryLeft, queryRight);
            return leftNode + rightNode;
        }
    }
    static void update(int left, int right, int node, int target, long diff){
        if(left <= target && target <= right){
            tree[node] +=diff;
            if(left != right){
                int mid =(left + right) / 2;
                update(left, mid, node *2, target, diff);
                update(mid +1, right, node *2 +1, target, diff);
            }
        }
    }
}

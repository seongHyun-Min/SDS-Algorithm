package SDS.DAY06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B_10868 {
    static int N, M;
    static int S;
    static long[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());


        S = 1;
        while (S < N) {
            S *= 2;
        }

        tree = new long[S * 2];
        for(int i=1; i<tree.length; i++){
            tree[i] = Long.MAX_VALUE;
        }
        for (int i = S; i < S + N; i++) {
            st = new StringTokenizer(br.readLine());
            tree[i] = Long.parseLong(st.nextToken());
        }

        for (int i = S - 1; i > 0; i--) {
            // 구간 합 구하는게 아니라 최소값 구하는 것이기 때문에
            // 부모노드를 자식 노드중 최소값으로 리턴
            tree[i] = Math.min(tree[i * 2] , tree[i * 2 + 1]);
        }

       // System.out.println(Arrays.toString(tree));

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int leftQuery = Integer.parseInt(st.nextToken());
            int rightQuery = Integer.parseInt(st.nextToken());
            System.out.println(query(leftQuery, rightQuery));
        }
    }

    static long query(int leftQuery, int rightQuery) {
        long min = Long.MAX_VALUE;
        int left = leftQuery + S - 1;
        int right = rightQuery + S - 1;
        // 1~ 10
        // 16~ 25
        // 75 30 100 38 50 51 52 20 81 5
        while (left <= right) {
            //left =16 둘다 해당 안됨
            // right = 25 둘다 해당 안됨


            if (left % 2 == 1) {
                min = Math.min(tree[left], min);
                left++;
            }
            if (right % 2 == 0){
                min = Math.min(tree[right] , min);
                right --;

            }
            right /= 2;
            left /= 2;
        }
        return min;
    }
}

package SDS.DAY03.indextree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B_2042 {
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
        while (S < N) {
            S *= 2;
        }
        tree = new long[S * 2];
        for (int i = S; i < S + N; i++) {
            st = new StringTokenizer(br.readLine());
            tree[i] = Long.parseLong(st.nextToken());
        }
        for (int i = S - 1; i > 0; i--) {
            tree[i] = tree[i * 2] + tree[i * 2 + 1];
        }
        System.out.println(Arrays.toString(tree));


        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());
            if (a == 1) {
                //이 경우에서 right 값을 확인해야하는데
                long diff = c - tree[b + S -1]; //여기에서 right가 변경값
                update(1, S, 1, b, diff); //여기에서 left가 target
            } else System.out.println(query(1, S, 1, b, c));
        }
    }

    static long query(int left, int right, int node, long leftQuery, long rightQuery) {
        //1.연관 없음
        //처음엔 left가 최소에서 시작해서 right가 최대에서 시작
        //수정전
        if (rightQuery < left || right < leftQuery) {
            return 0;
        } else if (leftQuery <= left &&  right <= rightQuery) {
            //2. 딱 맞아 떨어지는가?
            System.out.println("딱맞아 떨어지는가");
            System.out.println("tree[node] = " + tree[node]);
            return tree[node];
        } else {
            //3. 걸쳐있는가? //탐색 해야지
            int mid = (left + right) / 2;


            long leftNode = query(left, mid, node * 2, leftQuery, rightQuery);
            long RightNode = query(mid + 1, right, node * 2 + 1, leftQuery, rightQuery);
            return leftNode + RightNode;

        }
    }

    static void update(int left, int right, int node, long target, long diff) {
        //연관 없음 범위에 target이 없으면 return
       if(left <= target && target <= right) {
            //연관 있으면 노드 값 바꿔
            tree[node] += diff;

            if (left != right) {//리프노드가 아닐때 까지 값 변경해
                int mid = (left + right) / 2;
                //이 과정에선 left right가 같아질수 있어 그러면 tree[node] += diff만 하고 빠저나가는거야
                update(left, mid, node * 2, target, diff);
                update(mid + 1, right, node * 2 + 1, target, diff);
            }
        }
    }
}

package SDS.DAY03.indextree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_11505 {
    static long[] tree;
    static int N, M, K;
    static int S;
    static final int INF = 1000000007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        //1. 구간곱 트리 만들기
        S = 1;
        while (S < N) {
            S *= 2;
        }
        tree = new long[S * 2];
        //기본값은 항상 1로 고정 (곱셈이니까) 0이 나오는 것을 방지
        for (int i = 1; i < tree.length; i++) {
            tree[i] = 1;
        }
        for (int i = S; i < S + N; i++) {
            st = new StringTokenizer(br.readLine());
            tree[i] = Long.parseLong(st.nextToken());
        }
        for (int i = S - 1; i > 0; i--) {

            tree[i] = tree[i * 2] * tree[i * 2 + 1] % INF;
            //곱셈연산의 성질
            // (A * B) & C = (A % C) * (B % C) % C
            // 즉 곱셈연산후에 나눗셈을 하는것과 결과값은 동일하다
        }
        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());
            // 변경값 c는 long 값으로 입력받는다.
            if (a == 1) { //update
                //변경은 바텀 업으로 구현
                update(b, c);

            } else {
                // 쿼리는 업 다운 로 구현
                System.out.println(query(1, S, 1, b, c));
                //System.out.println(queryBu(b, (int) c));
            }
        }
    }

    // 2. 쿼리 구현 (업 다운)
    static long query(int left, int right, int node, int leftQuery, long rightQuery) {
        if (right < leftQuery || rightQuery < left) {
            return 1;
        } else if (leftQuery <= left && right <= rightQuery) {
            return tree[node];
        } else {
            int mid = (left + right) / 2;
            long leftNode = query(left, mid, node * 2, leftQuery, rightQuery);
            long rightNode = query(mid + 1, right, node * 2 + 1, leftQuery, rightQuery);
            return leftNode * rightNode % INF;
        }
    }

    static long queryBu(int leftQuery, int rightQuery) {
        long mul = 1;
        int left = leftQuery + S - 1;
        int right = rightQuery + S - 1;
        while (left <= right) {
            if (left % 2 == 1) {
                mul = mul * tree[left] % INF;
                // mul *= tree[left] % INF 로 작성하면
                //  곱셈전에 나눗셈 연산을 먼져 하게 되므로
                // 출력초과가 발생한다.
                left++;
            }
            if (right % 2 == 0) {
                mul = mul * tree[right] % INF;
                right--;
            }
            left /= 2;
            right /= 2;
        }
        return mul;
    }

    // 3. 바텀 업 구현
    static void update(int target, long diff) {
        int node = target + S - 1;
        tree[node] = diff;
        node /= 2;
        while (node > 0) {
            tree[node] = tree[node * 2] % INF * tree[node * 2 + 1] % INF;
            node /= 2;
        }
    }
}

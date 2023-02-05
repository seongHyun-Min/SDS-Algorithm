package SDS.DAY07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class B_2243 {
    static long[] tree;
    static int N;
    static int S;
    static int A, B, C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        S = 1;
        //사탕의 맛이 1~1000000까지로 구분 되기 때문에 배열의 크기를 굉장히 높게 잡아야하는데...
        //효율적으로 계산 할수 없을까???
        while (S < 1000000) {
            S *= 2;
        }
        tree = new long[S * 2]; //트리는 처음에 비어있어
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            if (A == 1) {
                int candy = query(1, S, 1, B);
                int diff = (int) tree[candy + S -1]; //그 맛의자리에 있던 값
                System.out.println(candy);
                //그 순위를 꺼냇으면 그 타겟에 대해서 항상 -1 해줘야해
                update(candy, diff -1);


            } else { // A==2면 타겟 B를 C만큼 넣거나 빼는것
                C = Integer.parseInt(st.nextToken());
                int diff = (int) tree[B +S -1] + C;
                update(B, diff);

            }
        }

    }

    static void update(int target, int diff) {
        int node = S + target - 1;
        tree[node] = diff;
        node /= 2;
        while (node > 0) {
            tree[node] = tree[node * 2] + tree[node * 2 + 1];
            node /= 2;
        }
    }

    static int query(int left, int right, int node, int target) {
        if (left == right) {
            return left;
        } else {
            int mid = (left + right) / 2;
            if (target <= tree[node * 2]) {
                return query(left, mid, node * 2, target);
            } else return query(mid + 1, right, node * 2 + 1, target - (int)tree[node * 2]);
            //오른쪽으로 가는 경우 왼쪽 자식의 값을 빼줘야 탐색할수 있어
        }
    }
}

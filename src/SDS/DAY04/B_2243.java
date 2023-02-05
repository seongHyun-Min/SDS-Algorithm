package SDS.DAY04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class B_2243 {
    static int[] tree;
    static int S;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());



        S = 1;
        tree = new int[16];

        int answer = Nquery(1, S, 1, 2);
        update(answer, -1);

    }

    static int Nquery(int left, int right, int node, int target) {
        if (left == right) {
            //사탕 찾음
            return left;
        }
        // 내부 노드 인 경우
        else {
            int mid = (left + right) / 2;
            if (tree[node * 2] >= target) {
                //왼쪽에 있는경우
                return Nquery(left, mid, node * 2, target);
            } else return Nquery(mid + 1, right, node * 2 + 1, target - tree[node * 2]);
        }
    }

    static long query(int leftQuery, int rightQuery) {
        long sum = 0;
        int left = leftQuery + S - 1;
        int right = rightQuery + S - 1;
        while (left <= right) {
            if (left % 2 == 1) {
                sum += tree[left++];
            }
            if (right % 2 == 0) {
                sum += tree[right--];
            }
            left /= 2;
            right /= 2;
        }
        return sum;
    }

    static void update(int target, int value) {
        int node = S + target - 1;
        tree[node] = value;

        node /= 2;
        // 여기서 node = 나눴지만 부모
        while (node > 0) {
            tree[node] = (tree[node * 2]) + (tree[node * 2 + 1]);
            node /= 2;
        }

    }
}

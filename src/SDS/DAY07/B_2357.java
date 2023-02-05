package SDS.DAY07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_2357 {
    static int[] minTree;
    static int[] maxTree;
    static int N, M;
    static int S;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        S = 1;
        while (S < N) {
            S *= 2;
        }
        minTree = new int[S * 2];
        maxTree = new int[S * 2];
        for (int i = 1; i < S * 2; i++) {
            minTree[i] = Integer.MAX_VALUE;
            maxTree[i] = Integer.MIN_VALUE;
            //초기값 세팅
        }
        for (int i = S; i < S + N; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            minTree[i] = num;
            maxTree[i] = num;
        }
        for (int i = S - 1; i > 0; i--) {
            minTree[i] = Math.min(minTree[i * 2], minTree[i * 2 + 1]);
            maxTree[i] = Math.max(maxTree[i * 2], maxTree[i * 2 + 1]);
        }
        //기본 트리 세팅 끝
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int leftQuery = Integer.parseInt(st.nextToken());
            int rightQuery = Integer.parseInt(st.nextToken());
            System.out.println(minQuery(leftQuery, rightQuery) + " " + maxQuery(leftQuery, rightQuery));
        }
    }

    static int minQuery(int leftQuery, int rightQuery) {
        int min = Integer.MAX_VALUE;
        int left = leftQuery + S - 1;
        int right = rightQuery + S - 1;
        while (left <= right) {
            if (left % 2 == 1) {
                min = Math.min(min, minTree[left]);
                left++;
            }
            if (right % 2 == 0) {
                min = Math.min(min, minTree[right]);
                right--;
            }
            left /= 2;
            right /= 2;
        }
        return min;
    }

    static int maxQuery(int leftQuery, int rightQuery) {
        int max = Integer.MIN_VALUE;
        int left = leftQuery + S - 1;
        int right = rightQuery + S - 1;
        while (left <= right) {
            if (left % 2 == 1) {
                max = Math.max(max, maxTree[left]);
                left++;
            }
            if (right % 2 == 0) {
                max = Math.max(max, maxTree[right]);
                right--;
            }
            left /= 2;
            right /= 2;
        }
        return max;
    }
}

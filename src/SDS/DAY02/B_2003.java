package SDS.DAY02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B_2003 {
    static int N, M;

    static int[] nums;

    static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        count = 0;
        nums = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        int start =0;
        int sum =0;
        int end =0;

        while (start < N) {
            if (sum > M || end == N) {
                sum -= nums[start];
                start++;
            } else { // sum <= num && end != N
                sum += nums[end];
                end++;
            }
            if (sum == M)
                count++;
        }
        System.out.println(count);
    }
}





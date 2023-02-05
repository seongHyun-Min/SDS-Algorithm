package SDS.DAY02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_2003T {
    static int N, M;

    static int[] nums;

    static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        count = 0;
        nums = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        int low =0;
        int high =0;

        int sum = nums[0];
        while(true){
            if(sum == M){
                count ++;
                sum -= nums[low++];
            }else if(sum > M){
                sum -= nums[low++];
            }else{
                sum += nums[++high];
            }
            //탈출 조건
            if(high == N){
                break;
            }
        }

        System.out.println(count);

    }
}

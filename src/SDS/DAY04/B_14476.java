package SDS.DAY04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.io.*;
import java.util.StringTokenizer;

public class B_14476 {

    static int N;
    static int[] nums;
    static int[] gcdLtoR;
    static int[] gcdRtoL;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        nums = new int[N];
        gcdLtoR = new int[N];
        gcdRtoL = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i=0; i<N; i++){
            nums[i] = Integer.parseInt(st.nextToken());
        }

        gcdLtoR[0] = nums[0];
        for(int i=1; i<N; i++){
            gcdLtoR[i] = gcd(gcdLtoR[i -1] , nums[i]);
        }
        gcdRtoL[N -1] =  nums[N -1];
        for(int i= N-2; i>=0; i--){
            gcdRtoL[i] = gcd(gcdRtoL[i +1] , nums[i]);
        }
        int max =0;
        int maxIndex =0;
        for(int i=0; i< N; i++){
            int temp =0;
            //왼쪽 끝
            if(i ==0){
                temp = gcdRtoL[1];
            }
            //오른쪽 끝
            else if(i == N - 1){
                temp = gcdLtoR[N -2];
            }
            else {
                temp = gcd(gcdLtoR[i -1] , gcdRtoL[i +1]);

            }
            if(nums[i] !=0 && temp > max){
                max = temp;
                maxIndex = i;
            }
            // 중간
        }

    }


    static int gcd(int a, int b){
        // gcd(a, b) == gcd(b, a & b)
        // a % b가 0일 때 b가 0
        while(b!=0){
            int r = a % b;
            a = b;
            b = r;
        }
        return a;
    }

}

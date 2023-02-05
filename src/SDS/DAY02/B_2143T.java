package SDS.DAY02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class B_2143T {
    static long[] A;
    static long[] B;
    static List<Long> sA; //부배열
    static List<Long> sB; //부배열
    static int T, n, m;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());
        n = Integer.parseInt(br.readLine());
        A = new long[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        m = Integer.parseInt(br.readLine());
        B = new long[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        } //입력받기

        //부분합 만들기
        sA = new ArrayList<>();
        sB = new ArrayList<>();


        for (int i = 0; i < n; i++) {
            long sum = 0;
            for (int j = i; j < n; j++) {
                sum += A[j];
                sA.add(sum);
            }
        }

        for (int i = 0; i < m; i++) {
            long sum = 0;
            for (int j = i; j < m; j++) {
                sum += B[j];
                sB.add(sum);
            }
        }
        Collections.sort(sA);
        Collections.sort(sB, Collections.reverseOrder());



        //투포인터
        int ptA = 0;
        int ptB = 0;
        long result = 0;
        while (true) {
            long currentA = sA.get(ptA);
            long target = T - currentA; //이 타겟을 B에서 찾겠다
            if (sB.get(ptB) > target){
                ptB ++;

            }else if(sB.get(ptB) < target){
                ptA++;

            }else { // sB.get(ptB) == target
                long countA = 0;
                long countB = 0;
                while(ptA < sA.size() &&  sA.get(ptA) == currentA){
                    ptA++;
                    countA++;
                }
                while(ptB < sB.size() &&  sB.get(ptB) == target){
                    ptB++;
                    countB++;
                }
                result += countA * countB;
            }

            if(ptA == sA.size() || ptB == sB.size()){
                break;
            }

        }


        System.out.println(result);
    }
}


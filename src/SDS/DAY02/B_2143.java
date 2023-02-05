package SDS.DAY02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class B_2143 {
    static int[] A;
    static int[] B;
    static List<Integer> sA; //부배열
    static List<Integer> sB; //부배열
    static int T, n, m;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());
        n = Integer.parseInt(br.readLine());
        A = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            A[i] = Integer.parseInt(st.nextToken());
        }
        m = Integer.parseInt(br.readLine());
        B = new int[m];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<m; i++){
            B[i] = Integer.parseInt(st.nextToken());
        } //입력받기

        //부분합 만들기
        sA = new ArrayList<>();
        sB = new ArrayList<>();


        for(int i=0; i<n; i++){
            int sum =0;
            for(int j=i; j<n; j++){
                sum +=A[j];
                sA.add(sum);
            }
        }

        for(int i=0; i<m; i++){
            int sum =0;
            for(int j=i; j<m; j++){
                sum +=B[j];
                sB.add(sum);
            }
        }
        Collections.sort(sA);
        Collections.sort(sB, Collections.reverseOrder());


        //투포인터
        int pa =0;
        int pb =0;
        long count =0;
        while(true){
            long sum = sA.get(pa) + sB.get(pb);
            if(sum == T){
                    long a =1;
                    long b =1;
                    for(int i=pa+1; i<sA.size(); i++){
                        if(sA.get(pa) ==sA.get(i)){
                            a++;
                            pa++;
                        }else break;
                    }
                    for(int i=pb+1; i<sB.size(); i++){
                        if(sB.get(pb) ==sB.get(i)){
                            b++;
                            pb++;
                        }else break;
                    }
                    count += (a * b);
                    pa++;
                    pb++;

            }else if(sum > T){
                pb ++;
            }else{
                pa ++;
            }
            //탈출 조건
            if(pa == sA.size() || pb == sB.size()){
                break;
            }
        }

        System.out.println(count);
    }
}


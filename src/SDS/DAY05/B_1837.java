package SDS.DAY05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class B_1837 {
    static String N;
    static int K;
    static boolean[] isNotPrime;

    static ArrayList<Integer> prime;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = st.nextToken();
        K = Integer.parseInt(st.nextToken());

        isNotPrime = new boolean[K + 1];
        prime = new ArrayList<>();
        //11?
        //1. 소수 리스트 만들기
        for (int i = 2; i < K; i++) {
            if (!isNotPrime[i]) { //소수가 맞으면
                for (int j = i * 2; j < K; j += i) {
                    //그 수의 배수는 소수 처리
                    isNotPrime[j] = true;
                }
            }
        }
        for (int i = 2; i < K; i++) {
            if (!isNotPrime[i]) {
                prime.add(i);
            }
        }
        boolean isGood = true;
        int smallPrime = 0;
        for (int i = 0; i < prime.size(); i++) {
            if (bigDv(N, prime.get(i)) == 0) {
                isGood = false;
                smallPrime = prime.get(i);
                break;
            }

        }
        if (isGood) {
            System.out.println("GOOD");
        } else System.out.println("BAD" + " " + smallPrime);
    }


    static int bigDv(String num, int prime) {
        int remain = 0;
        for (int i = 0; i < num.length(); i++) {
            int numchar = num.charAt(i) - '0';
            remain = (remain * 10 + (numchar)) % prime;
        }
        return remain;

    }
}

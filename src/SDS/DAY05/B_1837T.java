package SDS.DAY05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class B_1837T {
        static char[] P;
        static int K;
        static boolean[] checked;

        static ArrayList<Integer> prime = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        P = st.nextToken().toCharArray();
        K = Integer.parseInt(st.nextToken());

        checked = new boolean[K +1];
        for(int i=2; i<K ; i++){
            if(!checked[i]) {
                prime.add(i);
                for(int j= i *2; j<K; j +=i){
                    checked[j] = true;
                }
            }
        }
        boolean is = true;
        int smallPrime = 0;
        for(int i=0; i<prime.size(); i++){
            if(checkIsBad(prime.get(i))){
                is =false;
                smallPrime = prime.get(i);
                break;
            }
        }
        if(is){
            System.out.println("GOOD");
        }else System.out.println("BAD " + smallPrime);

    }
    static boolean checkIsBad(int x) {
        int r = 0;
        for (int i = 0; i < P.length; i++) {
            r = (r * 10 + (P[i] - '0')) % x;
        }
        if (r == 0) {
            return true;
        } else {
            return false;
        }
    }
}



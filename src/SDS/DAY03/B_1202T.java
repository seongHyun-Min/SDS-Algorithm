package SDS.DAY03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class B_1202T {
    static int N, K;

    static int[] bags;

    static jewel[] jewels;

    static PriorityQueue<jewel> pq;

    static int max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        bags = new int[K];
        jewels = new jewel[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int price = Integer.parseInt(st.nextToken());
            jewels[i] = new jewel(weight, price);
        }
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int bagWeight = Integer.parseInt(st.nextToken());
            bags[i] = bagWeight;
        }
        Arrays.sort(bags);

        Arrays.sort(jewels, Comparator.comparingInt(jewel::getWeight));

        PriorityQueue<jewel>  pq = new PriorityQueue<>(Comparator.comparingInt(jewel::getValue).reversed());

        int jIndex =0;
        long result = 0;
        for(int i=0; i< K; i++){
            int currentBag = bags[i];
            while(jIndex < N && jewels[jIndex].weight <= currentBag){
                pq.add(jewels[jIndex++]);
            }
            if(!pq.isEmpty()){
                result += pq.poll().value;
            }
        }
        System.out.println(result);
    }


    static class jewel{
        int weight;

        int value;

        public jewel(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }

        public int getWeight() {
            return weight;
        }

        public int getValue() {
            return value;
        }
    }
}

package SDS.DAY03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class B_1202 {
    static int N, K;
    static List<Integer> bag;

    static List<Gem> gem;
    static PriorityQueue<Gem> pq;

    static int max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        bag = new ArrayList<>();
        gem = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int price = Integer.parseInt(st.nextToken());
            gem.add(new Gem(weight, price));
        }
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int bagWeight = Integer.parseInt(st.nextToken());
            bag.add(bagWeight);
        }
        pq = new PriorityQueue<>();

        //가방 정렬
        Collections.sort(bag);
        Collections.sort(gem);
        max = 0;
        for(int i=0; i<bag.size(); i++) {
            int bagWeight = bag.remove(0);
            for (int j = 0; j < gem.size(); j++) {
                if (bagWeight >= gem.get(j).weight) {
                    pq.add(gem.get(j)); //가방에 지우면서 큐에 넣고
                }
            }
            max += Objects.requireNonNull(pq.poll()).price;
        }
        System.out.println(max);
    }


    static class Gem implements Comparable<Gem> {
        int weight;
        int price;

        public int getWeight() {
            return weight;
        }

        public int getPrice() {
            return price;
        }

        public Gem(int weight, int price){
            this.weight = weight;
            this.price =price;
        }


        @Override
        public int compareTo(Gem o) {
            int comp = o.price - price;
            if(comp ==0){
                 return weight - o.weight;
            }
            return comp;

        }
    }
}


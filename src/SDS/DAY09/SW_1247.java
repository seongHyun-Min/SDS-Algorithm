package SDS.DAY09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SW_1247 { //N!로 구현
    static int T;
    static int N; //고객의 수
    static boolean[] visited;  //고객 경우의수 저장
    static ArrayList<Customer> customers;
    static int companyX;
    static int companyY;
    static int homeX;
    static int homeY;

    static int minDist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            visited = new boolean[N];
            customers = new ArrayList<>();

            st = new StringTokenizer(br.readLine());
            companyX = Integer.parseInt(st.nextToken());
            companyY = Integer.parseInt(st.nextToken());
            homeX = Integer.parseInt(st.nextToken());
            homeY = Integer.parseInt(st.nextToken());
            for(int i=0; i<N; i++){
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                customers.add(new Customer(x, y));
            }
            minDist = Integer.MAX_VALUE;
            DFS(companyX, companyY ,0, 0, 0);
            System.out.println("#"+t + " " + minDist);
        }
    }

    static void DFS(int x, int y, int start, int count, int dist){
        if(count ==N){
            //집과의 거리 구해서 최소값 뽑자
            int sumDist = getDistance(x, homeX, y, homeY) +dist;
            minDist = Math.min(sumDist, minDist);
        }else{
            for(int i=0; i<customers.size(); i++){ //0부터 해야 팩토리얼 완전탐색 가지치기 x
                if(!visited[i]){
                    visited[i] =true;
                    int dx = customers.get(i).x;
                    int dy = customers.get(i).y;
                    int tmp = getDistance(x, dx, y, dy);
                    DFS(dx, dy, i+1, count+1, dist +tmp);
                    visited[i] =false;
                }
            }
        }
    }


    static int getDistance(int x1, int x2, int y1, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    static class Customer {
        int x;
        int y;

        public Customer(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}


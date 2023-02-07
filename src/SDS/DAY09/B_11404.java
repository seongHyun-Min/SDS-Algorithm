package SDS.DAY09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B_11404 {
    static int N, M;
    static int[][] dist;

    //플로이드 워샬 알고리즘
    //Integer.MAXVALUE로 받을 경우 점화식과정에서 int 범위를 넘어가서 계산이 이상해지기 때문에
    //상수로 max값을 선언한다.
    static final int max = 10000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        dist = new int[N + 1][N + 1];
        //dist 배열 초기화
        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < N + 1; j++) {
                if (i != j) {
                    dist[i][j] = max;
                }
            }
        }
        // 최단 거리 갱신 (입력 받는 순간에 가장 최단 거리 갱신


        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            dist[a][b] = Math.min(dist[a][b], cost);
        }


        //for문 3번 돌려가면서 최단 거리 갱신
        for (int k = 1; k < N + 1; k++) {
            for (int i = 1; i < N + 1; i++) {
                for (int j = 1; j < N + 1; j++) {
                    if (dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        //갱신
                    }
                }
            }
        }


        for(int i=1; i< N+1; i++){
            for(int j=1; j<N+1; j++){
                if(i !=j){
                    if(dist[i][j] == max){
                        dist[i][j] =0;
                    }
                }
            }
        }
        for(int i=1; i<N+1; i++){
            for(int j=1; j<N+1; j++){
                System.out.print(dist[i][j] + " ");
            }
            System.out.println();
        }


    }
}




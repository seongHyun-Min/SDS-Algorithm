package SDS.DAY06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class B_15686 {
    static int N, M;
    static int ans; //도시의 치킨 거리가 가장 작게 되는 값
    static ArrayList<Point> home;
    static ArrayList<Point> chicken;

    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        home = new ArrayList<>();
        chicken = new ArrayList<>();
        ans = Integer.MAX_VALUE;
        for(int r=0; r<N; r++){
            st = new StringTokenizer(br.readLine());
            for(int c=0; c<N; c++){
                int tmp =Integer.parseInt(st.nextToken());
                //tmp로 치킨집인지 집인지 구분
                if(tmp ==1){
                    home.add(new Point(r, c));
                }else if(tmp ==2){
                    chicken.add(new Point(r, c));
                }
            }
        }
        visited= new boolean[chicken.size()]; //치킨집 갯수만큼 방문 배열
        DFS(0, 0);
        System.out.println(ans);
    }


    static void DFS(int start, int count){
        if(count ==M){
            //치킨집이 M만큼 열었으면 dist에 최소값 저장
            int sum =0; // M의 경우의수가 나올때마다 집에서 치킨 거리를 저장해놓고 static의 dist와 최소값 리ㅓㄴ
            for(int i=0; i<home.size(); i++){
                int dist = Integer.MAX_VALUE;
                for(int j=0; j<chicken.size(); j++){
                    if(visited[j]){ //문 연 치킨집에서만 거리를 구할꺼야
                        //문 연 치킨집에서 거리를 구한다음에 집마다 치킨집 거리의 최소값을 바꿔
                        dist = Math.min(dist, Math.abs(home.get(i).x - chicken.get(j).x) + Math.abs( home.get(i).y - chicken.get(j).y));
                    }
                }
                sum +=dist; //최소의 거리를 구한 값을 sum에 누적 시켜
            }
            ans = Math.min(ans, sum); //M의 경우에서 나온 치킨거리 sum중 가장 적은 값을 ans에 저장
        }else{
            //치킨집이 m만큼 안열었으면 백트래킹을 통해 치킨집 문 열어줘
            for(int i= start; i< chicken.size(); i++){
                if(!visited[i]){ //치킨집이 문을 안 열엇으면 문을 열어줘
                    visited[i] = true; //문을연 치킨집 방문처리
                    DFS(i+1 ,count +1); //start +1로 해주는 이유는 1 2 3 4와 2 1 3 4 의 값이 같기 떄문
                    visited[i] = false;
                }

            }
        }
    }

    static class Point{
        int x;
        int y;
        public Point(int x, int y) {
            this.x =x;
            this.y =y;
        }
    }
}


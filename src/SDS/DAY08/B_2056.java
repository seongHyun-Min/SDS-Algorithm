package SDS.DAY08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class B_2056 {
    static int[] inDegree;

    static int[] result; //각각의 작업을 수행하는데 걸리는 시간
    static int[] time;
    static int N;
    static ArrayList<ArrayList<Integer>> arr;
    static int minTime;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        arr = new ArrayList<>();
        for (int i = 0; i < N + 1; i++) {
            arr.add(new ArrayList<>());
        }
        inDegree = new int[N + 1];
        time = new int[N+1];
        for(int i=1; i<N+1; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            time[i] = a;
            if(b >0){
                for(int j=0; j<b; j++){
                    int node =Integer.parseInt(st.nextToken()); //선수 노드
                    arr.get(node).add(i);
                    inDegree[i] ++;
                }
            }
        }
        minTime =0;
        result = new int[N+1];
        Queue<Integer> q = new LinkedList<>();
        for(int i=1; i<N+1; i++){
            if(inDegree[i] ==0){
                result[i] = time[i];
                q.offer(i); //선수 노드가 없는것들을 넣어줌
            }
        }
        while(!q.isEmpty()){
            int current = q.poll();
            int tmp =0;
            for(int i: arr.get(current)){
                inDegree[i] --; //간선 제거

                
                //result 배열에 현재 까지를 작업하는데 걸리는 시간을 누적 시킴
                //처음에는 if(inDegree[i] ==0) 인 경우에서만 result값을 변경시키면
                // 조금 더 효율적일것 이라 생각했지만
                // 1 -> 2 ->6 으로 가는데 220초 가 걸린다고 생각했을때 ex) //2 ->6 은 200
                // 1- > 3 -> 4-> 5-> 6 인 경우에 각 10초라고 생각하면
                // 1-> 3 (20) 3-> 4 (30) 4-> 5 (40) 5-> 6(50)으로 끝나게 된다.
                result[i] = Math.max(result[i] , result[current] + time[i]);


                //반례 입력 코드
                //6
                //10 0
                //200 1 1
                //10 2 1 4
                //10 1 1
                //10 2 3 4
                //10 3 2 3 5

                if(inDegree[i] ==0){

                    q.offer(i);
                }
            }

        }
        for(int i=1; i<result.length; i++){
            minTime = Math.max(minTime, result[i]);
        }
        System.out.println(minTime);
    }
}

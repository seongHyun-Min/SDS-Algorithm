package SDS.DAY08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_1516 { // 1516번 게임개발
    static ArrayList<ArrayList<Integer>> arr;
    static Queue<Integer> q;
    static int[] inDegree;
    static int[] cost; //비용 배열
    static int[] result; //결과 값 담기
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        arr = new ArrayList<>();
        for (int i = 0; i < N + 1; i++) {
            arr.add(new ArrayList<>());
        }
        inDegree = new int[N + 1];
        result = new int[N + 1];
        cost = new int[N + 1];
        q = new LinkedList<>();
        for (int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            cost[i] = time;
            while (true) {
                int PriorNode = Integer.parseInt(st.nextToken());
                if (PriorNode == -1) {
                    break;
                }
                inDegree[i]++; //들어오는 엣지 겟수 추가
                arr.get(PriorNode).add(i);
            }

        }
        for (int i = 1; i < N + 1; i++) {
            if (inDegree[i] == 0) {
                result[i] = cost[i];
                q.offer(i); //들어오는 엣지가 없는것은 모두 큐에 담기
            }
        }
        while (!q.isEmpty()) {
            int current = q.poll();
            for (int next : arr.get(current)) {
                inDegree[next]--;
                //엣지 하나씩 마이너스 시켜주면서 걸리는 시간 갱신 시켜
                result[next] = Math.max(result[next], result[current] + cost[next]);

                if (inDegree[next] == 0) {
                    q.offer(next);
                }
            }
        }
        for (int i = 1; i < result.length; i++) {
            System.out.println(result[i]);
        }
    }

}

package SDS.DAY05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_1722T {
    static int N;
    static int[] nums;
    static long[] fact = new long[21];
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        fact[0] = 1;
        for (int i = 1; i <= 20; i++) {
            fact[i] = fact[i - 1] * i;
        }
        N = Integer.parseInt(br.readLine());
        visited = new boolean[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        int command = Integer.parseInt(st.nextToken());

        if (command == 1) {
            long target = Long.parseLong(st.nextToken());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < N; i++) {
                //자릿수
                for (int j = 1; j <= N; j++) {
                    if (!visited[j]) {
                        //target이 경계 보다 클 경우
                        if (target > fact[N - i - 1]) {
                            target -= fact[N - i - 1];
                        } else { //target이 경계 이하인 경우
                            //이 타겟을 사용할 것이다
                            sb.append(j);
                            sb.append(" ");
                            visited[j] = true;
                            break;
                        }
                    }
                }
            }
            System.out.println(sb.toString());
        } else {
            nums = new int[N];
            for (int i = 0; i < N; i++) {
                nums[i] = Integer.parseInt(st.nextToken());
            }
            long result = 0; //나보다 앞에 있는게 몇개인지 찾을꺼야
            for (int i = 0; i < N; i++) {
                for (int j = 1; j < nums[i]; j++) {
                    if (!visited[j]) {
                        result += fact[N - i - 1];
                    }
                }
                visited[nums[i]] = true;
            }


            System.out.println(result + 1);
        }
    }
}

package SDS.DAY03.indextree;

import java.util.Arrays;

public class indexTree {

    static int N, M, K;

    static long[] nums = {1, 2, 3, 4, 5};

    static long[] tree; //실제 만들 트리

    static int S;

    public static void main(String[] args) {

        N = 5;
        S = 1;
        while(S < N){
            S *= 2;
        }
        tree = new long[S * 2];

        init();
        System.out.println(Arrays.toString(tree));

    }
    static void init(){
        //leaf는 데이터로 넣고
        for(int i=0; i< N; i++){
            tree[S + i] = nums[i];
        }
        for(int i= S-1; i> 0; i--){
            tree[i] = tree[i * 2] + tree[i * 2 + 1];
        }
    }


    static long query(int left, int right, int node, int queryLeft, int queryRight){
        // 1. 연관 없음
        if(queryRight <left || right < queryLeft){
            return 0;
        }
        // 2. 판단 가능( 쏙 들어감 )
        else if(queryLeft <= left && right <= queryRight){
            return tree[node];
        }
        // 3. 판단 불가 (걸쳐 있음)
        else{
            int mid = (left + right) / 2;
            long leftResult = query(left, mid, node * 2, queryLeft, queryRight);
            long rightResult = query(mid+1, right, node* 2 +1 , queryLeft, queryRight);
            return leftResult + rightResult;
        }
    }
    static void update(int left, int right, int node, int target, long diff) { //diff는 구해야함 함수 실행전에
        // 1. 연관 없음
        System.out.println(" right, node, target, diff = " + left + " " + right + " " + node + " " + target + " " + diff);

        if (left != right) {//리프노드가 아닐때 까지 값 변경해

            int mid = (left + right) / 2;
            update(left, mid, node * 2, target, diff);
            update(mid + 1, right, node * 2 + 1, target, diff);
        }
    }

            // 2-1 내부 노드냐?
            // 2-2 리프 노드냐?

    static long queryBU(int queryLeft, int queryRight){
        long sum =0;
        int left = S + queryLeft -1;
        int right = S + queryRight -1;

        while(left <= right){
            if(left % 2 ==1){ //왼쪽경우에서 오른쪽자식일 경우 사용 하고 +1
                sum += tree[left++];
            }
            if(right % 2 ==0){// 오른쪽 경우에서 왼쪽자식일 경우 사용하고 --
                sum += tree[right--];
            }
            left /= 2;
            right /= 2;
        }

        return sum;
    }
    static void updateBU(int target, long value){
        int node = S + target - 1;
        tree[node] = value;
        //마지막꺼 부터 바꿧어
        node /=2;
        while(node > 0){ //노드에 도달할때 까지 while문
            tree[node] = tree[node * 2] + tree[node * 2] + 1;
            node /= 2;
        }
    }
}

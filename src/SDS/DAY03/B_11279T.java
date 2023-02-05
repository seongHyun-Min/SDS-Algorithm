package SDS.DAY03;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class B_11279T {

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Integer> pq = new PriorityQueue<>();


    }

    static class MaxHeap{
        List<Integer> list;


        public MaxHeap(){
            list = new ArrayList<>(100001);
            list.add(0);
        }

        public void insert(int val){
            //1.마지막에 추가
            list.add(val);
            //2. 부모랑 조건 비교, 교환 (인덱스를 알아야해)
            int current = list.size() -1; //현재 제일 마지막에 있는 노드 번호
            int parent = current / 2;
            while(true){
                // 1. 탈출조건 current =1 (root)
                // 2. 부모, 자식 조건을 만족하면 parent >= curernt
                if(current == 1 || list.get(parent) >= list.get(current)){
                    break;
                }
                //조건을 만족하지 않으면 바꿔줘 SWAP
                int temp = list.get(parent);
                list.set(parent , list.get(current));
                list.set(current, temp);
                //부모 자식을 바꿔줫으면
                //현재가 부모가 되고 그 부모는 현재의 / 2가 된다
                current = parent;
                parent = current/2;
             }
        }

        public int delete(){
            //1. root제거
            int top = list.get(1);
            //2. 마지막 노드를 root로 이동시킨다.
            list.set(1, list.get(list.size() -1 ));
            list.remove(list.size() -1 );
            //2. 왼쪽, 오른쪽 중 조건이 안맞는 것을 선택

            int current = 1;
            while(true){
                int left = current * 2;
                int right = current * 2 + 1;

                //왼쪽이 없으면 당연히 오른쪽이 없기 때문에 왼쪽만 체크
                if(left >= list.size()){
                    break; //왼쪽이 없으면 자식이 없다.
                }

                int targetValue = list.get(left);
                int targetNode = left;

                if(right < list.size() && targetValue < list.get(right)){ //right가 존재하고 right가 더 큰 경우
                    targetNode = right;
                    targetValue = list.get(right);
                }

                if(list.get(current) < targetNode){
                    //SWAP 과정
                    int temp = list.get(current);
                    list.set(current, list.get(targetNode));
                    list.set(targetNode, temp);

                    current = targetNode;
                }else{
                    break;
                }




            }


            return top;
        }









    }
}

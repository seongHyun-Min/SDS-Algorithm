package SDS.DAY02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class B_1713T {
    static int N, K;

    static Person[] people;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());

        people = new Person[101]; // 0번은 버리고 인덱스 사용

        List<Person> list = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i=0; i<K; i++){
            int num = Integer.parseInt(st.nextToken());
            if(people[num] == null){
                //실제 불리는 사람만 객체 생성
                people[num] = new Person(num, 0, 0, false);
            }
            if(people[num].isIn){//현재 사진틀에 계시되어 있는가?
                people[num].count++;
            }else { //계시 안되 있는데
                if(list.size() == N){ //가득 차 있으면
                    Collections.sort(list);
                    //list를 정렬해서 조건에 따라 리스트의 가장 끝을 제거해준다
                    Person removeTarget = list.remove(N-1);
                    removeTarget.isIn = false;
                    removeTarget.count =0;
                }
                people[num].count = 1;
                people[num].isIn =true;
                people[num].timeStamp = i;
                list.add(people[num]);
            }
        }
        Collections.sort(list, (o1, o2) -> o1.num - o2.num);
        for(int i=0; i<list.size(); i++){
            System.out.print(list.get(i).getNum() + " ");
        }


    }


    static class Person implements Comparable<Person>{
        int num;
        int count;
        int timeStamp;
        boolean isIn;

        public int getNum(){
            return num;
        }

        public Person(int num, int count, int timeStamp, boolean isIn) {
            this.num = num;
            this.count = count;
            this.timeStamp = timeStamp;
            this.isIn = isIn;
        }

        @Override
        public int compareTo(Person o) {
            //ArrayList에 제일 뒤에 있는걸 먼저 뺄것이다.
            int comp1 = o.count - count;
            if(comp1 ==0){
                return o.timeStamp - timeStamp;
            }return comp1;
        }
    }
}

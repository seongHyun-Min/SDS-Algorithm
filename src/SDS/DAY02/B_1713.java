package SDS.DAY02;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class B_1713 {
    static int N, K;

    static Student[] students;

    static ArrayList<Student> person;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        person = new ArrayList<>();
        students = new Student[101];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            int num = Integer.parseInt(st.nextToken());
            if (students[num] == null) {
                students[num] = new Student(num, 0, 0, false);
            }
            if (students[num].isIn) {
                students[num].count++;
                //이미 게시 되어있으면 count ++
            } else {
                //게시 안되있으면 add
                if (person.size() == N) {
                    //가득 찼으면
                    Collections.sort(person);
                    Student remove = person.remove(N - 1);
                    remove.isIn = false;
                    remove.count = 0;
                }
                //추천 add
                students[num].isIn = true;
                students[num].count = 1;
                students[num].num = num;
                students[num].time = i;
                person.add(students[num]);
            }
        }
        Collections.sort(person, (o1, o2) -> o1.num - o2.num);
        for(int i=0; i<person.size(); i++){
            System.out.print(person.get(i).getNum() + " ");
        }
        }


     static class Student implements Comparable<Student>{
        int num;
        int count;
        int time;
        boolean isIn;

        public int getNum(){
            return num;
        }

        public Student(int num, int count, int time, boolean isIn){
            this.num =num;
            this.count = count;
            this.time =time;
            this.isIn = isIn;
        }

        @Override
        public int compareTo(Student o) {
            int comp = o.count - count;
            if(comp ==0){
                return o.time - time;
            }return comp;
        }
    }
}


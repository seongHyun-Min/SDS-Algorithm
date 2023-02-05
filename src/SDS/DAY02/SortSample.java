package SDS.DAY02;

import java.util.Arrays;
import java.util.Comparator;

public class SortSample {

    public static void main(String[] args) {
        Integer [] nums = {1, 5, 3, 4, 5};

        //오름 차순 정렬
        System.out.println(Arrays.toString(nums));
        Arrays.sort(nums);

        System.out.println(Arrays.toString(nums));


        //역순 정렬
        Arrays.sort(nums, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 -o1;
            }
        });

        // 위에서 만든 sort와 똑같음
        Arrays.sort(nums, Comparator.reverseOrder());


        //객체 배열
        Item[] items = new Item[5];
        items[1] = new Item(1, 5);
        items[2] = new Item(2, 4);
        items[3] = new Item(3, 3);
        items[4] = new Item(4, 2);
        items[5] = new Item(5, 1);


        System.out.println(Arrays.toString(items));

        Arrays.sort(items, (o1, o2) -> {
            if(o1.value1 == o2.value1){
                return o2.value2 - o1.value1; //value1의 값이 같다면 o2가 낮은순으로 정렬해라
            }else{
                return o1.value1 - o2.value1;
            }
        });


    }


    //Comparable 기본 정렬 속성
    // 문제에서 기본 정렬 속성이 있다면 Comparable로 구현하는것이 좋고
    // 문제에서 출력을 해서 보여달라 하면 Comparator로 특정조건을 쒸워서 덮어주는 것이 좋다.

    static class Item implements Comparable<Item>{
        int value1;
        int value2;

        public Item(int value1, int value2){
            this.value1 =value1;
            this.value2 =value2;
        }

        @Override
        public String toString() {
            return "Item{" +
                    "value1=" + value1 +
                    ", value2=" + value2 +
                    '}';
        }

        @Override
        public int compareTo(Item o) {
            return value1 - o.value1;
        }
    }

}

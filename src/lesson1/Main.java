package lesson1;

import java.util.ArrayList;
import java.util.Arrays;

public class Main<E> {
    public static void main(String[] args) {
        Integer[] i = new Integer[]{1,2,3};
        String[] s = new String[]{"First", "Second", "Third"};
        System.out.println(Arrays.asList(i));
        new Main<>().changeElementOf(i,0,1);
        System.out.println(Arrays.asList(i));

        System.out.println(Arrays.asList(s));
        new Main<>().changeElementOf(s,0,1);
        System.out.println(Arrays.asList(s));

        System.out.println(new Main<>().toArrayList(i));
    }

    public  void changeElementOf(E[] arr, int first, int second) {
        E temp = arr[first];
        arr[first] = arr[second];
        arr[second] = temp;
    }
    public ArrayList<E> toArrayList(E[] arr){
        ArrayList arrayList = new ArrayList<E>();
        arrayList.addAll(Arrays.asList(arr));
        return arrayList;
    }
}

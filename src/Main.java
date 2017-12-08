import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    private final static int LIST_SIZE = 1024;
    private final static int MAX_NUMBER = 1000;

    public static List<Integer> merge(List<Integer> list1, List<Integer> list2) {

        List<Integer> result = new ArrayList<>();

        int counter1 = 0;
        int counter2 = 0;

        while (counter1 < list1.size() && counter2 < list2.size()) {
            if (list1.get(counter1) < list2.get(counter2)) {
                result.add(list1.get(counter1));
                counter1++;
            } else {
                result.add(list2.get(counter2));
                counter2++;
            }
        }

        for (int i = counter1; i < list1.size(); i++) {
            result.add(list1.get(i));
        }

        for (int j = counter2; j < list2.size(); j++) {
            result.add(list2.get(j));
        }

        return result;

    }

    public static List<Integer> mergeSort(List<Integer> list) {

        if (list.size() == 1) {
            return list;
        }
        else {
            int middle = list.size() / 2;
            List<Integer> list1 = mergeSort(list.subList(0, middle));
            List<Integer> list2 = mergeSort(list.subList(middle, list.size()));
            return merge(list1, list2);
        }
    }

    public static int partition(List<Integer> list, int left, int right) {

        int middle = (left + right) / 2;
        int pivot = list.get(middle);

        int i = left - 1;
        int j = right + 1;

        while(true) {

            do {
                i += 1;
            }
            while (list.get(i) < pivot);

            do {
                j -= 1;
            }
            while (list.get(j) > pivot);

            if (j <= i) {
                return j;
            }

            int temp = list.get(i);
            list.set(i, list.get(j));
            list.set(j, temp);
        }

    }


    public static List<Integer> quickSort(List<Integer> list, int left, int right) {

        if (left <= right) {
            return list;
        }
        else {

            int pivot = partition(list, left, right);
            quickSort(list, left, pivot);
            quickSort(list, left, pivot);
        }

    }


    public static void main(String[] args) {

        Random rnd = new Random(System.currentTimeMillis());

        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < LIST_SIZE; i++) {
            list.add(rnd.nextInt(MAX_NUMBER));
        }

        List<Integer> mergeSortResult = mergeSort(list);

        List<Integer> quickSortResult = quickSort(list);

        for (int x: list) {
            System.out.print(x + " ");
        }
        System.out.println();

        for (int x: mergeSortResult) {
            System.out.print(x + " ");
        }
        System.out.println();

        for (int x: quickSortResult) {
            System.out.print(x + " ");
        }
        System.out.println();


    }
}

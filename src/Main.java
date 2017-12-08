import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    private final static int LIST_SIZE = 100000;
    private final static int MAX_NUMBER = 1000000;

    public static boolean check(List<Integer> list) {
        int x = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            if (x > list.get(i)) return false;
            x = list.get(i);
        }
        return true;
    }

    public static List<Integer> bubbleSort(List<Integer> list) {

        for (int i = 0; i < list.size()-1; i++) {

            for (int j = i+1; j < list.size(); j++) {
                if (list.get(i) > list.get(j)) {
                    int temp = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, temp);
                }
            }
        }
        return list;

    }

    public static List<Integer> insertionSort(List<Integer> list) {

        for (int i = 0; i < list.size(); i++) {

            int j = i - 1;
            int key = list.get(i);
            while (j >= 0 && list.get(j) > key) {
                list.set(j+1, list.get(j));
                j--;
            }
            list.set(j+1, key);
        }
        return list;

    }

    public static List<Integer> doMerge(List<Integer> list1, List<Integer> list2) {

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
            return doMerge(list1, list2);
        }
    }

    public static int doPartition(List<Integer> list, int left, int right) {

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

        if (left >= right) {
            return list;
        }
        else {

            int pivot = doPartition(list, left, right);
            quickSort(list, left, pivot);
            quickSort(list, pivot + 1, right);
        }

        return list;

    }


    public static void main(String[] args) {

        Random rnd = new Random(System.currentTimeMillis());

        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < LIST_SIZE; i++) {
            list.add(rnd.nextInt(MAX_NUMBER));
        }

        System.out.println("Starting...");

        if (!check(list)) System.out.println("Initial list confirmed, " + list.size() + " elements unsorted.");

        long start = System.currentTimeMillis();
        List<Integer> bubbleSortResult = bubbleSort(new ArrayList<>(list));
        if (check(bubbleSortResult)) System.out.println("Bubble sort verified. " + (System.currentTimeMillis() - start) + "ms");

        start = System.currentTimeMillis();
        List<Integer> insertionSortResult = insertionSort(new ArrayList<>(list));
        if (check(insertionSortResult)) System.out.println("Insertion sort verified. " + (System.currentTimeMillis() - start) + "ms");

        start = System.currentTimeMillis();
        List<Integer> mergeSortResult = mergeSort(new ArrayList<>(list));
        if (check(mergeSortResult)) System.out.println("Merge sort verified. " + (System.currentTimeMillis() - start) + "ms");

        start = System.currentTimeMillis();
        List<Integer> quickSortResult = quickSort(new ArrayList<>(list), 0, list.size()-1);
        if (check(quickSortResult)) System.out.println("Quick sort verified. " + (System.currentTimeMillis() - start) + "ms");
    }
}

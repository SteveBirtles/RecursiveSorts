import java.util.*;

public class Main {

    private final static int LIST_SIZE = 40000;
    private final static int MAX_NUMBER = 100000;

    public static boolean check(List<Integer> list) {
        int x = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            if (x > list.get(i)) return false;
            x = list.get(i);
        }
        return true;
    }

    public static List<Integer> bubbleSort(List<Integer> list) {

        for (int i = 1; i < list.size(); i++) {
            for (int j = 0; j < list.size()-1; j++) {
                if (list.get(j+1) < list.get(j)) {
                    int temp = list.get(j);
                    list.set(j, list.get(j+1));
                    list.set(j+1, temp);
                }
            }
        }
        return list;

    }

    public static List<Integer> optimisedBubbleSort(List<Integer> list) {

        for (int i = 1; i < list.size(); i++) {
            boolean swapped = false;
            for (int j = 0; j < list.size()-1; j++) {
                if (list.get(j+1) < list.get(j)) {
                    int temp = list.get(j);
                    list.set(j, list.get(j+1));
                    list.set(j+1, temp);
                    swapped = true;
                }
            }
            if (!swapped) break;
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

    public static List<Integer> iterativeMergeSort(List<Integer> list) {

        List<List<Integer>> listOfLists = new ArrayList<>();

        for (Integer x : list) {
            List<Integer> subList = new ArrayList<>();
            subList.add(x);
            listOfLists.add(subList);
        }

        while (listOfLists.size() > 1) {
            List<Integer> alpha = listOfLists.get(listOfLists.size()-1);
            listOfLists.remove(listOfLists.size()-1);
            List<Integer> beta = listOfLists.get(listOfLists.size()-1);
            listOfLists.remove(listOfLists.size()-1);

            List<Integer> subList = new ArrayList<>();

            while (alpha.size() > 0 && beta.size() > 0) {
                if (alpha.get(alpha.size() - 1) > beta.get(beta.size() - 1)) {
                    subList.add(0, alpha.get(alpha.size() - 1));
                    alpha.remove(alpha.size() - 1);
                } else {
                    subList.add(0, beta.get(beta.size() - 1));
                    beta.remove(beta.size() - 1);
                }
            }

            subList.addAll(0, alpha);
            subList.addAll(0, beta);

            listOfLists.add(0, subList);
        }

        return listOfLists.get(0);
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

    public static List<Integer> recursiveMergeSort(List<Integer> list) {

        if (list.size() == 1) {
            return list;
        }
        else {
            int middle = list.size() / 2;
            List<Integer> list1 = recursiveMergeSort(list.subList(0, middle));
            List<Integer> list2 = recursiveMergeSort(list.subList(middle, list.size()));
            return doMerge(list1, list2);
        }
    }




    public static int doPartition(List<Integer> list, int left, int right) {

        int pivot = (left + right) / 2;
        int value = list.get(pivot);

        int i = left - 1;
        int j = right + 1;

        while(true) {

            do {
                i += 1;
            }
            while (list.get(i) < value);

            do {
                j -= 1;
            }
            while (list.get(j) > value);

            if (j <= i) {
                return j;
            }

            int temp = list.get(i);
            list.set(i, list.get(j));
            list.set(j, temp);
        }

    }


    public static List<Integer> recursiveQuicksort(List<Integer> list, int left, int right) {

        if (left >= right) {
            return list;
        } else {

            int pivot = doPartition(list, left, right);
            recursiveQuicksort(list, left, pivot);
            recursiveQuicksort(list, pivot + 1, right);
        }

        return list;

    }

    public static List<Integer> iterativeQuicksort(List<Integer> list) {

        Stack<Integer> leftStack = new Stack<>();
        leftStack.push(0);
        Stack<Integer> rightStack = new Stack<>();
        rightStack.push(list.size() - 1);

        while (leftStack.size() > 0) {
            int left = leftStack.pop();
            int right = rightStack.pop();

            int wall = left;
            int pivot = list.get(right);

            for (int cursor = left; cursor < right; cursor++) {
                if (list.get(cursor) < pivot) {

                    int temp = list.get(cursor);
                    list.set(cursor, list.get(wall));
                    list.set(wall, temp);

                    wall++;
                }
            }

            int temp = list.get(right);
            list.set(right, list.get(wall));
            list.set(wall, temp);

            if (wall - 1 > left) {
                leftStack.push(left);
                rightStack.push(wall - 1);
            }

            if (wall + 1 < right) {
                leftStack.push(wall + 1);
                rightStack.push(right);
            }
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

        long start;

        start = System.currentTimeMillis();
        List<Integer> bubbleSortResult = bubbleSort(new ArrayList<>(list));
        if (check(bubbleSortResult)) System.out.println("Bubble sort verified. " + (System.currentTimeMillis() - start) + "ms");

        start = System.currentTimeMillis();
        List<Integer> optimisedBubbleSortResult = optimisedBubbleSort(new ArrayList<>(list));
        if (check(optimisedBubbleSortResult)) System.out.println("Optimised bubble sort verified. " + (System.currentTimeMillis() - start) + "ms");

        start = System.currentTimeMillis();
        List<Integer> insertionSortResult = insertionSort(new ArrayList<>(list));
        if (check(insertionSortResult)) System.out.println("Insertion sort verified. " + (System.currentTimeMillis() - start) + "ms");

        start = System.currentTimeMillis();
        List<Integer> iterativeMergeSortResult = iterativeMergeSort(new ArrayList<>(list));
        if (check(iterativeMergeSortResult)) System.out.println("Iterative merge sort verified. " + (System.currentTimeMillis() - start) + "ms");

        start = System.currentTimeMillis();
        List<Integer> recursiveMergeSortResult = recursiveMergeSort(new ArrayList<>(list));
        if (check(recursiveMergeSortResult)) System.out.println("Recursive merge sort verified. " + (System.currentTimeMillis() - start) + "ms");

        start = System.currentTimeMillis();
        List<Integer> iterativeQuicksortResult = iterativeQuicksort(new ArrayList<>(list));
        if (check(iterativeQuicksortResult)) System.out.println("Iterative quicksort verified. " + (System.currentTimeMillis() - start) + "ms");

        start = System.currentTimeMillis();
        List<Integer> recursiveQuicksortResult = recursiveQuicksort(new ArrayList<>(list), 0, list.size()-1);
        if (check(recursiveQuicksortResult)) System.out.println("Recursive quicksort verified. " + (System.currentTimeMillis() - start) + "ms");

        start = System.currentTimeMillis();
        Collections.sort(list);
        if (check(list)) System.out.println("Java default sort verified. " + (System.currentTimeMillis() - start) + "ms");

    }
}

import java.util.Arrays;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Random rand = new Random();

        int[] arr0 = new int[10000];
        for (int i = 0; i < arr0.length; i++) {
            arr0[i] = rand.nextInt(100000);
        }
        int[] arr1 = Arrays.copyOf(arr0, arr0.length);
        int[] arr2 = Arrays.copyOf(arr0, arr0.length);
        int[] arr3 = Arrays.copyOf(arr0, arr0.length);

        long startbuble = System.nanoTime();
        buble_sort(arr0);
        long endbuble = System.nanoTime();


        long startselect = System.nanoTime();
        selection_sort(arr1);
        long endselect = System.nanoTime();

        long startmerge = System.nanoTime();
        mergeSort(arr2, 0, arr2.length - 1);
        long endmerge = System.nanoTime();

        long startsort = System.nanoTime();
        Arrays.sort(arr3);
        long endsort = System.nanoTime();

        System.out.println("Time taken Bublesort  (nanoseconds): " + (endbuble - startbuble));
        System.out.println("Time taken selectsort (nanoseconds): " + (endselect - startselect));
        System.out.println("Time taken mergersort (nanoseconds): " + (endmerge - startmerge));
        System.out.println("Time taken array.sort (nanoseconds): " + (endsort - startsort));






    }

    public static void buble_sort(int[] uarr) {
        for (int i = 0; i < uarr.length; i++) {
            for (int k = 0; k < uarr.length - 1; k++) {
                if (uarr[k] > uarr[k + 1]) {
                    int tmp = uarr[k + 1];
                    uarr[k + 1] = uarr[k];
                    uarr[k] = tmp;
                }
            }
        }
    }

    public static void selection_sort(int[] uarr) {
        for (int i = 0; i < uarr.length - 1; i++) {
            int min_index = i;
            for (int k = i + 1; k < uarr.length; k++) {
                if (uarr[k] < uarr[min_index]) {
                    min_index = k;
                }
            }
            int tmp = uarr[min_index];
            uarr[min_index] = uarr[i];
            uarr[i] = tmp;
        }
    }

    //nicht von mir
    public static void mergeSort(int[] array, int left, int right) {
        if (left >= right) return;

        int mid = (left + right) / 2;
        mergeSort(array, left, mid);
        mergeSort(array, mid + 1, right);

        int[] temp = new int[right - left + 1];
        int i = left, j = mid + 1, k = 0;

        while (i <= mid && j <= right) {
            temp[k++] = (array[i] <= array[j]) ? array[i++] : array[j++];
        }
        while (i <= mid) temp[k++] = array[i++];
        while (j <= right) temp[k++] = array[j++];

        System.arraycopy(temp, 0, array, left, temp.length);
    }
}

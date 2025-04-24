public class Main {

    public static void main(String[] args) {
        int[] arr={23,7,1,42,8};
        buble_sort(arr);

        for (int j : arr) {
            System.out.println(j);
        }


    }
    public static void buble_sort(int[] uarr){
        for (int i = 0; i < uarr.length; i++) {
            for (int k = 0; k < uarr.length-1; k++) {
                if(uarr[k] > uarr[k+1]){
                    int tmp = uarr[k+1];
                    uarr[k+1] = uarr[k];
                    uarr[k]=tmp;
                }
            }
        }
    }

    
}

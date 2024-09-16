
public class Main {

    static int[] arr =new int[100];
    static boolean sorted(int[]ar){
        for (int i =0;i < ar.length-1;++i){
            if (ar[i]>ar[i+1])
                return false;
        }
        return true;
    }

    static int[] sort(int[] ar) {
        while (!sorted(ar)){
            for (int i = 0; i < ar.length-1; i++) {
                if (ar[i] > ar[i+1]) {
                    ar[i] = ar[i] ^ ar[i+1] ^ (ar[i+1] = ar[i]);
                }
            }
        }
        return ar;
        }

    public static void main(String[] args) {
        for(int i = 0; i < 100; i++){
            arr[i]= (int)(Math.random() * 101);
        }
        int[] ns=sort(arr);
        for (int i =0;i<arr.length;++i)
            System.out.println(ns[i]);
    }
}



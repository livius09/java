mport java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Area calculator");
        System.out.println("Please Enter the length: ");
        long lengt=sc.nextLong();
        System.out.println("Please Enter the whith: ");
        long whith=sc.nextLong();

        if (lengt>0 && whith>0) {
            long area = lengt * whith;
            System.out.println("The area is: ");
            System.out.println(area);
        }else{
            System.out.println("eingabe ungültig");
        }

    }
}

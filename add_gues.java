import java.util.Random;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand= new Random();

        int rando1=rand.nextInt(11);
        int rando2 =rand.nextInt(11);
        System.out.printf("zahl 1: %d%n zahl2: %d%n",rando1,rando2);
        int rando=rando1+rando2;


        while (true){
            System.out.print("Add the numbers: ");
            int user_guess =sc.nextInt();
            if (user_guess==rando){
                System.out.println("you got it right it was: "+rando);
                break;
            } else if (user_guess<rando) {
                System.out.println(user_guess+" is to small");
            } else {
                System.out.println(user_guess+" is to big");
            }
        }
    }
}

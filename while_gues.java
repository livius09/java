import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("please select a range from 0-x");
        int range = sc.nextInt();
        int rand = (int) (Math.random()*range);

        while (true){
            System.out.println("Guess the number");
            int user_guess =sc.nextInt();
            if (user_guess==rand){
                System.out.println("you won you got it right it was: "+rand);
                break;
            } else if (user_guess<rand) {
                System.out.println(user_guess+" is to small");
            } else {
                System.out.println(user_guess+" is to big");
            }
        }
    }
}

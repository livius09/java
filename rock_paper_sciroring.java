import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        Random rand=new Random();

        System.out.println("----------");
        System.out.println("Schere Stein Papier");
        System.out.println("----------");
        byte user;

        do {
            System.out.print("wähle schere(1), stein(2) oder Papier(3): ");
            user= sc.nextByte();
        }while (!(user<3 && user>0));
        int computer=rand.nextInt(3)+1;
        System.out.println("computer hat "+computer+" gewählt");

        if (user==computer){
            System.out.println("patt keiner hat gewonen");

        }else if (user==1&&computer==2){
            System.out.println("computer hat gewonnen");

        }else if (user==2&&computer==1){
            System.out.println("user hat gewonnen");
        }



    }
}

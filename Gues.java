import java.util.Scanner; // import the Scanner class 
import java.util.Random;

class Main {
  public static void main(String[] args) {
    Random rand = new Random();
    int rando = rand.nextInt(42+1);
    
    Scanner sca = new Scanner(System.in);
    System.out.println("Enter a number 0-42"); 
    int uin = sca.nextInt();   
    
   
    if (rando==uin){
      System.out.println("du hast die richtige numer eraten");
    }else{
      System.out.println("falsch es war "+rando);
    }
  }
}

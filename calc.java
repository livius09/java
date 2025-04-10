import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int chose;
        double a;
        double b;
        Scanner sc=new Scanner(System.in);
        do {
            System.out.println("was Wilst du tun: \n1.Adiren \n2.Subtrahieren\n3.Multiplizieren\n4.Dividiren\n5.Quadrieren\n6.Wurzel Zihen");
            chose= sc.nextInt();
        }while (chose>0 && chose<7);
        System.out.println("zahl eingeben");
        a=sc.nextDouble();
        System.out.println("Zahl Zwei eingeben");
        b=sc.nextDouble();
    }
    
    
    
    
    
    public static double addiren(double a,double b){
        return a+b;
    }
    public static double subtrahieren(double a,double b){
        return a-b;
    }
    public static double multiply(double a,double b){
        return a*b;
    }
    public static double Divide(double a,double b){
        return a/b;
    }
    public static double root(double a){
        return Math.sqrt(a);
    }
    public static double sqr(double a){
        return a*a;
    }





}

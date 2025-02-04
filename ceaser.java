import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("wilkomen zum ceaser cipher");

        byte in;
        do {
            System.out.printf("was möchtest du tun: %n 1.Verschlüsenl %n 2.Entschlüseln %n");
            in=sc.nextByte();

        }while (!(in==1 || in==2));

        System.out.println("mit welchen shift möchtest du Ent/Ver schlüseln");
        int shift=sc.nextInt();
        System.out.println("gib den text zum Ent/Ver schüseln ein:");
        String txt = sc.next().toLowerCase();

        if (in==1){
            int base = 97;

            for (int i = 0; i < txt.length(); i++) {
                char c=txt.charAt(i);
                c= (char) ((((c-base)+shift)%26)+base);

                System.out.print(c);
            }
            System.out.println();

        }else{
            int base = 97;

            for (int i = 0; i < txt.length(); i++) {
                char c=txt.charAt(i);
                c= (char) ((((c-base-shift)%26+26)%26)+base);

                System.out.print(c);
            }
            System.out.println();
        }


    }
}

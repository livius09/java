import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("wilkomen zum ceaser cipher");

        byte in;
        do {
            System.out.printf("was möchtest du tun: %n 1.Verschlüsenl %n 2.Entschlüseln %n 3.Brute Force");
            in=sc.nextByte();

        }while (in>4 || in<0);
        int shift=0;
        if(in!=3){
            System.out.println("mit welchen shift möchtest du Ent/Ver schlüseln");
            shift=sc.nextInt();
        }
        System.out.println("gib den text zum Ent/Ver schüseln ein:");
        String txt = sc.next().toLowerCase();

        int base = 97;
        if (in==1){

            for (int i = 0; i < txt.length(); i++) {
                char c=txt.charAt(i);
                c= (char) ((((c-base)+shift)%26)+base);

                System.out.print(c);
            }

        }else if(in==2){

            for (int i = 0; i < txt.length(); i++) {
                char c=txt.charAt(i);
                c= (char) ((((c-base-shift)%26+26)%26)+base);

                System.out.print(c);
            }
        }else{
            char[] word= new char[txt.length()];
            String[] shifts=new String[26];
            for (int j = 1; j < 26; j++) {
                for (int i = 0; i < txt.length(); i++) {
                    char c=txt.charAt(i);
                    c= (char) ((((c-base-j)%26+26)%26)+base);
                    word[i]=c;
                }
                shifts[j]=new String(word);
            }
            for (int k = 1; k < 26; k++) {
                System.out.printf("shift(%d): ",k);
                System.out.println(shifts[k]);
            }
            int idmax=0;
            int maxes=0;
            for (int o = 1; o < 25; o++) {
                int es=0;
                for (int i = 0; i < txt.length(); i++) {
                    if(shifts[o].charAt(i)=='e'){
                        es++;
                    }
                }
                if (maxes<es){
                    maxes=es;
                    idmax=o;
                }
            }
            System.out.println("warscheinlichste entschlüselung");
            System.out.println(shifts[idmax]);
        }
    }
}

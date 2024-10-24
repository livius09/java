import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static char[][] arr = {{'#', '#','#'}, {'#', '#','#'}, {'#', '#','#'}};

    public static void main(String[] args) {

        System.out.println("--------------------");
        System.out.println("tick tack toe");
        System.out.println("--------------------");

        draw();

        main:
        while (true) {
           if (logic(input('x'),'x')){
               break main;
           }
           draw();
            if (logic(input('O'),'O')){
                break main;
            }
            draw();
        }
    }


    public static byte[] input(char zeich) {
        byte y;
        byte x;
        x_loop:
        while (true) {
            System.out.println("--------------------");
            System.out.println(zeich + " ist an der reihe");

            try {
                System.out.print("reihe wo du dein " + zeich + " plazieren wilst(1-3): ");
                y = sc.nextByte();

                System.out.print("spalte wo du dein " + zeich + " plazieren wilst(1-3): ");
                x = sc.nextByte();

                x--;
                y--;

                if (x >= 0 && x < 3 && y >= 0 && y < 3 && arr[y][x] == '#') {
                    arr[y][x] = zeich;
                    break;
                }else {
                    throw new IllegalArgumentException();
                }
              
            } catch (Exception e) {
                System.out.println("du must eine zahl zwieschen 1 und 3 eingeben");
                sc.next();
                continue x_loop;
            }
        }

        return (new byte[]{x,y});
    }

    public static void draw() {
        for(int i = 0; arr.length > i;++i){
            for(int j = 0; arr.length > j;++j){
                System.out.print(arr[i][j]+"|");
            }
            System.out.println();
            System.out.println("-|-|-|-");
        }
    }

    public static boolean logic(byte[] ari,char zeich){
        byte x=ari[0];
        byte y=ari[1];

        // Check the row
        if (arr[x][0] == arr[x][1] && arr[x][0] == arr[x][2] && arr[x][0] != '#') {
            System.out.println(zeich + " hat gewonnen");
            return true;
        }

        // Check the column
        if (arr[0][y] == arr[1][y] && arr[0][y] == arr[2][y] && arr[0][y] != '#') {
            System.out.println(zeich + " hat gewonnen");
            return true;
        }

        // Check the main diagonal
        if (arr[0][0] == arr[1][1] && arr[0][0] == arr[2][2] && arr[0][0] != '#') {
            System.out.println(zeich + " hat gewonnen");
            return true;
        }

        // Check the anti-diagonal
        if (arr[2][0] == arr[1][1] && arr[2][0] == arr[0][2] && arr[2][0] != '#') {
            System.out.println(zeich + " hat gewonnen");
            return true;
        }

        // Check for a draw
        if (count_2d() == 0) {
            System.out.println("Niemand hat gewonnen, unentschieden");
            return true;
        }

        return false;
    }

    public static byte count_2d(){
        byte u = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int a = 0; a < arr[0].length; a++) {
                if (arr[i][a]=='#'){
                    ++u;
                }

            }

        }
        return u;
    }
}

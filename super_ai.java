import java.util.Random;
import java.util.Scanner;

public class Main {
    public static Scanner sc = new Scanner(System.in);
    public static char[][] arr = {{'#', '#', '#'}, {'#', '#', '#'}, {'#', '#', '#'}};

    public static void main(String[] args) {


        System.out.println("--------------------");
        System.out.println("tick tack toe");
        System.out.println("--------------------");

        Game.draw();
        byte[] aiarr={-1,-1};

        main:
        while (true) {
            if (Game.logic(Game.input('x'), 'x')) {
                break main;
            }
            Game.draw();

            aiarr[0]=-1;
            aiarr[1]=-1;

            aiarr=AI.atacker();

            if (aiarr[0]==-1){
                aiarr=AI.defender();
            }
            if (aiarr[0]==-1) {
                aiarr=AI.random();
            }
            System.out.println("AI chose:");
            System.out.println(aiarr[0]+1);
            System.out.println(aiarr[1]+1);

            Game.draw();

            if (Game.logic(aiarr, '0')) {
                break main;
            }

        }
    }
}




class Game{
    public static byte count_2d(){
        byte u = 0;
        for (int i = 0; i < Main.arr.length; i++) {
            for (int a = 0; a < Main.arr[0].length; a++) {
                if (Main.arr[i][a]=='#'){
                    ++u;
                }

            }

        }
        return u;
    }

    public static boolean logic(byte[] ari,char zeich){
        byte x=ari[0];
        byte y=ari[1];

        if (x == -1 || y == -1) return false;

        // Check the row
        if (Main.arr[x][0] == Main.arr[x][1] && Main.arr[x][0] == Main.arr[x][2] && Main.arr[x][0] != '#') {
            System.out.println(zeich + " hat gewonnen");
            return true;
        }

        // Check the column
        if (Main.arr[0][y] == Main.arr[1][y] && Main.arr[0][y] == Main.arr[2][y] && Main.arr[0][y] != '#') {
            System.out.println(zeich + " hat gewonnen");
            return true;
        }

        // Check the main diagonal
        if (Main.arr[0][0] == Main.arr[1][1] && Main.arr[0][0] == Main.arr[2][2] && Main.arr[0][0] != '#') {
            System.out.println(zeich + " hat gewonnen");
            return true;
        }

        // Check the anti-diagonal
        if (Main.arr[2][0] == Main.arr[1][1] && Main.arr[2][0] == Main.arr[0][2] && Main.arr[2][0] != '#') {
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
    public static void draw() {
        for(int i = 0; Main.arr.length > i;++i){
            for(int j = 0; Main.arr.length > j;++j){
                System.out.print(Main.arr[i][j]+"|");
            }
            System.out.println();
            System.out.println("-|-|-|-");
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
                y = Main.sc.nextByte();

                System.out.print("spalte wo du dein " + zeich + " plazieren wilst(1-3): ");
                x = Main.sc.nextByte();

                x--;
                y--;

                if (x >= 0 && x < 3 && y >= 0 && y < 3 && Main.arr[y][x] == '#') {

                    Main.arr[y][x] = zeich;
                    break;

                } else {
                    throw new IllegalArgumentException();
                }


            } catch (Exception e) {
                System.out.println("du must eine zahl zwieschen 1 und 3 eingeben");
                Main.sc.next();
                continue x_loop;
            }

        }


        return (new byte[]{x, y});
    }
}
class AI{
    public static Random rn= new Random();

    public static byte[] random() {
        byte x;
        byte y;
        for (int i = 0; i < 18; i++) { //try finding randomly free spaces
            x= (byte) rn.nextInt(3);
            y= (byte) rn.nextInt(3);

            if(Main.arr[x][y]=='#'){
                Main.arr[x][y]='0';
                return (new byte[]{x,y});
            }
        }
        for (byte j = 0; j < Main.arr.length; j++) { //if the random does not find any just go through them all
            for (byte k = 0; k < Main.arr[j].length; k++) {
                if(Main.arr[j][k]=='#'){
                    Main.arr[j][k]='0';
                    return (new byte[]{j,k});
                }
            }
        }
        throw  new RuntimeException("some thing went very wrong");
    }
    public static byte[] defender() {

        for (byte i = 0; i < 3; i++) {
            if (Main.arr[0][i]=='#'&&Main.arr[1][i]=='x'&&Main.arr[2][i]=='x'){
                Main.arr[0][i]='0';
                return (new byte[]{0,i});
            }
            if (Main.arr[i][0]=='#' && Main.arr[i][1]=='x' && Main.arr[i][2]=='x'){
                Main.arr[i][0]='0';
                return (new byte[]{i,0});
            }


            if (Main.arr[0][i]=='x'&&Main.arr[1][i]=='#'&&Main.arr[2][i]=='x'){
                Main.arr[1][i]='0';
                return (new byte[]{1,i});
            }
            if (Main.arr[i][0]=='x' && Main.arr[i][1]=='#' && Main.arr[i][2]=='x'){
                Main.arr[i][1]='0';
                return (new byte[]{i,1});
            }


            if (Main.arr[0][i]=='x'&&Main.arr[1][i]=='x'&&Main.arr[2][i]=='#'){
                Main.arr[2][i]='0';
                return (new byte[]{2,i});
            }
            if (Main.arr[i][0]=='x' && Main.arr[i][1]=='x' && Main.arr[i][2]=='#'){
                Main.arr[i][2]='0';
                return (new byte[]{i,2});
            }
        }
        if (Main.arr[0][0]=='#' && Main.arr[1][1]=='x' && Main.arr[2][2]=='x'){
            Main.arr[0][0]='0';
            return (new byte[]{0,0});
        }

        if (Main.arr[0][0]=='x' && Main.arr[1][1]=='#' && Main.arr[2][2]=='x'){
            Main.arr[1][1]='0';
            return (new byte[]{1,1});
        }

        if (Main.arr[0][0]=='x' && Main.arr[1][1]=='x' && Main.arr[2][2]=='#'){
            Main.arr[2][2]='0';
            return (new byte[]{2,2});
        }



        if (Main.arr[2][0]=='#' && Main.arr[1][1]=='x' && Main.arr[0][2]=='x'){
            Main.arr[2][0]='0';
            return (new byte[]{2,0});
        }

        if (Main.arr[2][0]=='x' && Main.arr[1][1]=='#' && Main.arr[0][2]=='x'){
            Main.arr[1][1]='0';
            return (new byte[]{1,1});
        }

        if (Main.arr[2][0]=='x' && Main.arr[1][1]=='x' &&Main.arr[0][2]=='#'){
            Main.arr[2][2]='0';
            return (new byte[]{0,2});
        }
        return (new byte[]{-1,-1});
    }
    public static byte[] atacker() {

        for (byte i = 0; i < 3; i++) {
            if (Main.arr[0][i]=='#'&&Main.arr[1][i]=='0'&&Main.arr[2][i]=='0'){
                Main.arr[0][i]='0';
                return (new byte[]{0,i});
            }
            if (Main.arr[i][0]=='#' && Main.arr[i][1]=='0' && Main.arr[i][2]=='0'){
                Main.arr[i][0]='0';
                return (new byte[]{i,0});
            }



            if (Main.arr[0][i]=='0'&&Main.arr[1][i]=='#'&&Main.arr[2][i]=='0'){
                Main.arr[1][i]='0';
                return (new byte[]{1,i});
            }
            if (Main.arr[i][0]=='0' && Main.arr[i][1]=='#' && Main.arr[i][2]=='0'){
                Main.arr[i][1]='0';
                return (new byte[]{i,1});
            }


            if (Main.arr[0][i]=='0'&&Main.arr[1][i]=='0'&&Main.arr[2][i]=='#'){
                Main.arr[2][i]='0';
                return (new byte[]{2,i});
            }
            if (Main.arr[i][0]=='0' && Main.arr[i][1]=='0' && Main.arr[i][2]=='#'){
                Main.arr[i][2]='0';
                return (new byte[]{i,2});
            }
        }
        if (Main.arr[0][0]=='#' && Main.arr[1][1]=='0' && Main.arr[2][2]=='0'){
            Main.arr[0][0]='0';
            return (new byte[]{0,0});
        }

        if (Main.arr[0][0]=='0' && Main.arr[1][1]=='#' && Main.arr[2][2]=='0'){
            Main.arr[1][1]='0';
            return (new byte[]{1,1});
        }

        if (Main.arr[0][0]=='0' && Main.arr[1][1]=='0' && Main.arr[2][2]=='#'){
            Main.arr[2][2]='0';
            return (new byte[]{2,2});
        }



        if (Main.arr[2][0]=='#' && Main.arr[1][1]=='0' && Main.arr[0][2]=='0'){
            Main.arr[2][0]='0';
            return (new byte[]{2,0});
        }

        if (Main.arr[2][0]=='0' && Main.arr[1][1]=='#' && Main.arr[0][2]=='0'){
            Main.arr[1][1]='0';
            return (new byte[]{1,1});
        }

        if (Main.arr[2][0]=='0' && Main.arr[1][1]=='0' &&Main.arr[0][2]=='#'){
            Main.arr[2][2]='0';
            return (new byte[]{0,2});
        }


        return (new byte[]{-1,-1});
    }
}

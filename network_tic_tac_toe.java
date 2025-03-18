import java.io.*;
import java.net.*;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class Main {
    public static Scanner sc = new Scanner(in);
    public static char[][] arr = {{'#', '#', '#'}, {'#', '#', '#'}, {'#', '#', '#'}};
    public static byte mod;

    public static void main(String[] args) throws IOException {
        int port = 5010;
        Socket socket = null;
        InputStream in = null;
        OutputStream out = null;

        do {
            System.out.println("Wähle Modus: 1. Client 2. Server");
            mod = sc.nextByte();
        } while (mod != 1 && mod != 2);

        String IP;
        if (mod == 1) {  // Client mode
            while (true) {
                System.out.print("Input an IP to connect to: ");
                IP = sc.next();
                try {
                    socket = new Socket(IP, port);
                    in = socket.getInputStream();
                    out = socket.getOutputStream();
                    System.out.println("Connected to server.");
                    break;
                } catch (IOException e) {
                    System.out.println("Error connecting to " + IP + ". Error: " + e);
                }
            }
        } else {  // Server mode
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server is waiting for a connection...");

            socket = serverSocket.accept();
            in = socket.getInputStream();
            out = socket.getOutputStream();
            System.out.println("Client connected.");
        }

        System.out.println("--------------------");
        System.out.println("Tic-Tac-Toe");
        System.out.println("--------------------");

        Game.draw();

        if (mod == 2) {  // Server starts the game
            while (true) {
                byte[] tmp = Game.input('X');
                out.write(tmp);
                out.flush();


                if (Game.logic(tmp, 'X')){
                    in.close();
                    out.close();
                    Game.draw();
                    break;
                }
                System.out.println();
                Game.draw();

                System.out.println("waiting for other players move");
                byte[] buffer = new byte[2];
                int bytesRead = in.read(buffer);
                if (bytesRead < 2) break;

                if (Game.logic(buffer, 'O')){
                    Game.draw();
                    in.close();
                    out.close();
                    break;
                }
                Game.draw();
            }
        } else {  // Client starts the game
            while (true) {
                System.out.println("waiting for other players move");
                byte[] buffer = new byte[2];
                int bytesRead = in.read(buffer);
                if (bytesRead < 2) break;


                if (Game.logic(buffer, 'O')){
                    in.close();
                    out.close();
                    Game.draw();
                    break;
                }
                Game.draw();

                byte[] tmp = Game.input('X');
                out.write(tmp);
                out.flush();


                if (Game.logic(tmp, 'X')){
                    in.close();
                    out.close();
                    Game.draw();
                    break;
                }
                Game.draw();
            }
        }

        System.out.println("Game over.");
        socket.close();
    }
}

class Game {
    public static byte count_2d() {
        byte u = 0;
        for (char[] row : Main.arr) {
            for (char cell : row) {
                if (cell == '#') {
                    u++;
                }
            }
        }
        return u;
    }

    public static boolean logic(byte[] ari, char symbol) {
        byte x = ari[0], y = ari[1];

        if (x == -1 || y == -1) return false;

        // Fix: Use y as row index
        Main.arr[y][x] = symbol;

        // Check row
        if (Main.arr[y][0] == Main.arr[y][1] && Main.arr[y][0] == Main.arr[y][2] && Main.arr[y][0] != '#') {
            System.out.println(symbol + " hat gewonnen");
            return true;
        }

        // Check column
        if (Main.arr[0][x] == Main.arr[1][x] && Main.arr[0][x] == Main.arr[2][x] && Main.arr[0][x] != '#') {
            System.out.println(symbol + " hat gewonnen");
            return true;
        }

        // Check diagonals
        if ((Main.arr[0][0] == Main.arr[1][1] && Main.arr[0][0] == Main.arr[2][2] && Main.arr[0][0] != '#') ||
                (Main.arr[2][0] == Main.arr[1][1] && Main.arr[2][0] == Main.arr[0][2] && Main.arr[2][0] != '#')) {
            System.out.println(symbol + " hat gewonnen");
            return true;
        }

        // Check for draw
        if (count_2d() == 0) {
            System.out.println("Niemand hat gewonnen, Unentschieden!");
            return true;
        }

        return false;
    }

    public static void draw() {
        for (char[] row : Main.arr) {
            for (char cell : row) {
                System.out.print(cell + " | ");
            }
            System.out.println();
            System.out.println("- + - + -");
        }
    }

    public static byte[] input(char symbol) {
        byte x, y;
        while (true) {
            System.out.println("--------------------");
            System.out.println(symbol + " ist an der Reihe");

            try {
                System.out.print("Reihe (1-3): ");
                y = (byte) (Main.sc.nextByte() - 1);

                System.out.print("Spalte (1-3): ");
                x = (byte) (Main.sc.nextByte() - 1);

                if (x >= 0 && x < 3 && y >= 0 && y < 3 && Main.arr[y][x] == '#') {
                    return new byte[]{x, y};
                } else {
                    System.out.println("Ungültige Eingabe. Feld belegt oder außerhalb des Bereichs!");
                }
            } catch (Exception e) {
                System.out.println("Bitte eine Zahl zwischen 1 und 3 eingeben!");
                Main.sc.nextLine();  // Clear invalid input
            }
        }
    }
}

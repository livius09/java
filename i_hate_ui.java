import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import static java.lang.System.in;

public class Main extends Application {
    public static Scanner sc = new Scanner(in);
    public static char[][] arr = {{'#', '#', '#'}, {'#', '#', '#'}, {'#', '#', '#'}};
    public static byte mod;
    public static Label Top_label = new Label("-");
    public static byte[] tmp = {-1, -1};

    @Override
    public void start(Stage primaryStage) {
        int rows = 3, cols = 3;

        // Create top label

        Top_label.setStyle("-fx-font-size: 16px; -fx-padding: 10px;");

        GridPane gridPane = new GridPane();

        // Create and add Labels to the grid
        for (byte row = 0; row < rows; row++) {
            for (byte col = 0; col < cols; col++) {
                Label label = new Label(arr[row][col] + "  "); // Add some spacing
                label.setStyle("-fx-border-color: black; -fx-padding: 10px; -fx-background-color: lightgray;");

                final byte r = row, c = col; // Capture row and column index
                label.setOnMouseClicked((MouseEvent event) -> {
                    System.out.println("event: " + r + " " + c);
                    if (Main.arr[r][c] == '#') {
                        tmp[0] = r;
                        tmp[1] = c;

                    }
                });

                gridPane.add(label, col, row);
            }
        }

        // Use VBox to place Top_label above the grid
        VBox root = new VBox(Top_label, gridPane);
        root.setSpacing(10); // Add some spacing between label and grid

        Scene scene = new Scene(root, 300, 300);
        primaryStage.setTitle("Tic Tac Toe UI");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

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

        launch(args);  // Start the JavaFX UI

        Game.draw();

        if (mod == 2) {  // Server starts the game
            while (true) {
                tmp[0] = -1;
                tmp[1] = -1;
                Platform.runLater(() -> Main.Top_label.setText("Your Move"));

                // We now wait for the click asynchronously
                synchronized (tmp) {
                    while (tmp[0] == -1 && tmp[1] == -1) {
                        try {
                            tmp.wait();  // Wait until the click is registered
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }

                arr[tmp[0]][tmp[1]] = 'X';
                out.write(tmp);
                out.flush();

                if (Game.logic(tmp, 'X')) {
                    in.close();
                    out.close();
                    Game.draw();
                    break;
                }

                Game.draw();
                Platform.runLater(() -> Main.Top_label.setText("waiting for other player's move"));

                byte[] buffer = new byte[2];
                int bytesRead = in.read(buffer);
                if (bytesRead < 2) break;

                if (Game.logic(buffer, 'O')) {
                    Game.draw();
                    in.close();
                    out.close();
                    break;
                }

                Game.draw();
            }
        } else {  // Client starts the game
            while (true) {
                Platform.runLater(() -> Main.Top_label.setText("waiting for other player's move"));

                byte[] buffer = new byte[2];
                int bytesRead = in.read(buffer);
                if (bytesRead < 2) break;

                if (Game.logic(buffer, 'O')) {
                    in.close();
                    out.close();
                    Game.draw();
                    break;
                }

                Game.draw();

                tmp[0] = -1;
                tmp[1] = -1;

                Platform.runLater(() -> Main.Top_label.setText("Your Move"));

                // Wait for the click asynchronously
                synchronized (tmp) {
                    while (tmp[0] == -1 && tmp[1] == -1) {
                        try {
                            tmp.wait();  // Wait until the click is registered
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }

                out.write(tmp);
                out.flush();

                if (Game.logic(tmp, 'X')) {
                    in.close();
                    out.close();
                    Game.draw();
                    break;
                }

                Game.draw();
            }
        }

        Platform.runLater(() -> Main.Top_label.setText("Game over"));
        socket.close();
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

            Main.arr[y][x] = symbol;

            // Check row
            if (Main.arr[y][0] == Main.arr[y][1] && Main.arr[y][0] == Main.arr[y][2] && Main.arr[y][0] != '#') {
                Platform.runLater(() -> Main.Top_label.setText(symbol + "hat gewonnen"));
                return true;
            }

            // Check column
            if (Main.arr[0][x] == Main.arr[1][x] && Main.arr[0][x] == Main.arr[2][x] && Main.arr[0][x] != '#') {
                Platform.runLater(() -> Main.Top_label.setText(symbol + "hat gewonnen"));
                return true;
            }

            // Check diagonals
            if ((Main.arr[0][0] == Main.arr[1][1] && Main.arr[0][0] == Main.arr[2][2] && Main.arr[0][0] != '#') ||
                    (Main.arr[2][0] == Main.arr[1][1] && Main.arr[2][0] == Main.arr[0][2] && Main.arr[2][0] != '#')) {
                Platform.runLater(() -> Main.Top_label.setText(symbol + "hat gewonnen"));
                return true;
            }

            // Check for draw
            if (count_2d() == 0) {
                Platform.runLater(() -> Main.Top_label.setText("Nobody Wins its a Draw"));
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
    }
}

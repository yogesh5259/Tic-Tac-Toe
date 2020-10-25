package tictactoe;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        TicTacToe ticTacToe = new TicTacToe();
        ticTacToe.play();

    }
}

class TicTacToe {
    private static Scanner scanner = new Scanner(System.in);
    private static char[][] array = new char[3][3];
    private static int countO = 0;
    private static int countX = 0;

    public void play(){
        System.out.print("Input command: ");
        String[] input = scanner.nextLine().split(" ");
        while (true){
            if (input.length == 3){
                if(input[0].equals("start") && input[1].equals("easy") && input[2].equals("easy") ||
                        input[0].equals("start") && input[1].equals("user") && input[2].equals("easy") ||
                        input[0].equals("start") && input[1].equals("easy") && input[2].equals("user") ||
                        input[0].equals("start") && input[1].equals("user") && input[2].equals("user")) {

//                  call the print method
                    printTable();
                    if (input[0].equals("start") && input[1].equals("easy") && input[2].equals("easy")){

//                      call the easy and easy method
                        easyToEasy();
                    } else if (input[0].equals("start") && input[1].equals("user") && input[2].equals("easy")) {

//                      call the user and easy
                        userToEasy();
                        scanner.nextLine();
                    } else if (input[0].equals("start") && input[1].equals("user") && input[2].equals("user")){

//                      call the user and user method
                        useToUser();
                        scanner.nextLine();
                    } else if (input[0].equals("start") && input[1].equals("easy") && input[2].equals("user")){
//                      call the easy and user method
                        easyToUser();
                        scanner.nextLine();
                    }
                    System.out.print("Input command: ");
                    input = scanner.nextLine().split(" ");
                    array = new char[3][3];
                }
            } else if (input[0].equals("exit")){
                break;
            } else {
                System.out.println("Bad parameters!");
                System.out.print("Input command: ");
                input = scanner.nextLine().split(" ");
            }
        }

    }


    public void printTable(){
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                if (array[i][j] == 'X' || array[i][j] == 'O') {

                    System.out.print(array[i][j] + " ");
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    public char checkForWin() {
        for (int i = 0; i < 3; i++) {
            if (array[i][0] == array[i][1] && array[i][0] == array[i][2]) {
                return array[i][0];
            }
        }
        for (int i = 0; i < 3; i++) {
            if (array[0][i] == array[1][i] && array[0][i] == array[2][i]) {
                return array[0][i];
            }
        }

        if (array[0][0] == array[1][1] && array[0][0] == array[2][2]) {
            return array[0][0];
        }

        if (array[0][2] == array[1][1] && array[0][2] == array[2][0]) {
            return array[0][2];
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (array[i][j] != 'X' && array[i][j] != 'O'){
                    return 'C';
                }
            }
        }

        return 'N';
    }

    public void plotElement(int x, int y) {
        if (countX > countO ) {
            array[3 - y][x - 1] = 'O';
            countO++;
        } else {
            array[3 - y] [x -1] = 'X';
            countX++;
        }
        printTable();
    }


    public void copmuterPlay(){

            System.out.println("Making move level \"easy\"");
            Random random = new Random();
            int x = random.nextInt(3) + 1;
            int y = random.nextInt(3) + 1;
            while (array[3 - y][x - 1] == 'X' || array[3 - y][x - 1] == 'O'){
                x = random.nextInt(3) + 1;
                y = random.nextInt(3) + 1;
            }
            plotElement(x, y);
    }

    public void userPlay() {
        while (true){
            System.out.print("Enter the coordinates: ");
            int x = 0;
            int y = 0;
            try {
                String s1 = scanner.next();
                x = Integer.parseInt(s1);


            } catch (Exception e) {
                scanner.nextLine();
                System.out.println("You should enter numbers!");
            }
            String s2 = scanner.next();
            y = Integer.parseInt(s2);

            if (x > 3 || x < 1 || y > 3 || y < 1) {
                System.out.println("Coordinates should be from 1 to 3!");
            } else if (array[3 - y][x - 1] == 'X' || array[3 - y][x - 1] == 'O') {
                System.out.println("This cell is occupied! Choose another one!");
            } else {
                plotElement(x, y);
                break;
            }
        }

    }

    public void easyToEasy(){
        while (true){
            char c = checkForWin();
            if (c == 'X') {
                System.out.println("X wins");
                break;
            } else if (c == 'O') {
                System.out.println("O wins");
                break;
            } else if (c == 'N'){
                System.out.println("Draw");
                break;
            }
            copmuterPlay();
        }
    }

    public void userToEasy(){
        boolean round = true;
        while (true){
            char c = checkForWin();
            if (c == 'X') {
                System.out.println("X wins");
                break;
            } else if (c == 'O') {
                System.out.println("O wins");
                break;
            } else if (c == 'N'){
                System.out.println("Draw");
                break;
            }
            if (round) {
                userPlay();
                round = false;
            } else {
                copmuterPlay();
                round = true;
            }

        }
    }

    public void easyToUser(){

        boolean round = true;
        while (true){
            char c = checkForWin();
            if (c == 'X') {
                System.out.println("X wins");
                break;
            } else if (c == 'O') {
                System.out.println("O wins");
                break;
            } else if (c == 'N'){
                System.out.println("Draw");
                break;
            }
            if (round) {
                copmuterPlay();
                round = false;
            } else {
                userPlay();
                round = true;
            }

        }

    }

    public void useToUser(){
        while (true){
            char c = checkForWin();
            if (c == 'X') {
                System.out.println("X wins");
                break;
            } else if (c == 'O') {
                System.out.println("O wins");
                break;
            } else if (c == 'N'){
                System.out.println("Draw");
                break;
            }
            userPlay();
        }
    }

}

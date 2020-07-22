package tictactoe;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int movecount = 0;
        Scanner scanner = new Scanner(System.in);
        char[][] array = new char[3][3];

        printCell(array);
        char input= 'X';
        while (true) {
            System.out.print("Enter the coordinates: ");
            String x = scanner.next();
            String y = scanner.next();
            int i = 0;
            int j = 0;
            try{
                i = Integer.parseInt(x);
                j = Integer.parseInt(y);
            } catch (Exception e){
                System.out.println("You should enter numbers!");
                continue;
            }
            if ( i > 3 || i < 1 || j > 3 || j < 1) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            } else {
                if (array[Math.abs(3 - j)][i - 1] == 'X' || array[Math.abs(3 - j)][i - 1] == 'O') {
                    System.out.println("This cell is occupied! Choose another one!");

                } else {
                    if (input == 'X') {
                        array[Math.abs(3 - j)][i - 1] = 'X';
                        movecount++;
                        input = 'O';
                    }else {
                        array[Math.abs(3 - j)][i - 1] = 'O';
                        movecount++;
                        input = 'X';
                    }

                }
            }
            printCell(array);
            char result = CheckWin(array);

            if (result == 'X') {
                System.out.println("X wins");
                break;
            } else if (result == 'O') {
                System.out.println("O wins");
                break;
            }if (result == 'D') {
                System.out.println();
            }
            if (movecount == 9) {
                System.out.println("Draw");
            }

            System.out.println();
        }
    }

    public static void printCell(char[][] array){
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                if ('O' == array[i][j] || 'X' == array[i][j] || '0' == array[i][j]){
                    System.out.print(array[i][j] + " ");
                } else {
                    System.out.print("  ");
                }

            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    public static char CheckWin(char[][] array) {

        for (int i = 0; i < 3; i++) {
            if (array[i][0] == array[i][1] && array[i][0] == array[i][2]){
                return array[i][0];
            }
        }
        for (int i = 0; i < 3; i++) {
            if (array[0][i] == array[1][i] && array[0][i] == array[2][i]){
                return array[0][i];
            }
        }

        if (array[0][0] == array[1][1] && array[0][0] == array[2][2]) {
            return array[0][0];
        }
        if (array[0][2] == array[1][1] && array[0][2] == array[2][0]) {
            return array[0][2];
        }


        return 'N';
    }
}


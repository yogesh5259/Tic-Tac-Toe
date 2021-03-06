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
    static char player = 'X', opponent = 'O';

    static class Move
    {
        int row, col;
    };



    public void play() {
        System.out.print("Input command: ");
        String[] input = scanner.nextLine().split(" ");

        while (true) {
            //check for 3 input and the provided three is correct or not
            if (input.length == 3) {
                if (input[0].equals("start") && input[1].equals("easy") && input[2].equals("easy") ||
                        input[0].equals("start") && input[1].equals("user") && input[2].equals("easy") ||
                        input[0].equals("start") && input[1].equals("easy") && input[2].equals("user") ||
                        input[0].equals("start") && input[1].equals("user") && input[2].equals("user") ||
                        input[0].equals("start") && input[1].equals("user") && input[2].equals("medium") ||
                        input[0].equals("start") && input[1].equals("medium") && input[2].equals("medium") ||
                        input[0].equals("start") && input[1].equals("hard") && input[2].equals("user") ||
                        input[0].equals("start") && input[1].equals("user") && input[2].equals("hard") ||
                        input[0].equals("start") && input[1].equals("hard") && input[2].equals("hard")




                ) {

//                  call the print method
                    printTable();
                    if (input[0].equals("start") && input[1].equals("easy") && input[2].equals("easy")) {

//                      call the easy and easy method
                        easyToEasy();
                    } else if (input[0].equals("start") && input[1].equals("user") && input[2].equals("easy")) {

//                      call the user and easy
                        userToEasy();
                        scanner.nextLine();
                    } else if (input[0].equals("start") && input[1].equals("user") && input[2].equals("user")) {

//                      call the user and user method
                        useToUser();
                        scanner.nextLine();
                    } else if (input[0].equals("start") && input[1].equals("easy") && input[2].equals("user")) {
//                      call the easy and user method
                        easyToUser();
                        scanner.nextLine();
                    } else if (input[0].equals("start") && input[1].equals("user") && input[2].equals("medium")) {

                        //call the user and medium method
                        mediumToUser();
                        scanner.nextLine();
                    } else if(input[0].equals("start") && input[1].equals("medium") && input[2].equals("medium")){
                        mediumToMedium();
                        scanner.nextLine();
                    } else if(input[0].equals("start") && input[1].equals("hard") && input[2].equals("user")){
                        hardToUser();
                        scanner.nextLine();
                    } else if(input[0].equals("start") && input[1].equals("user") && input[2].equals("hard")){
                        userToHard();
                        scanner.nextLine();
                    }else if(input[0].equals("start") && input[1].equals("hard") && input[2].equals("hard")){
                        hardToHard();
                        scanner.nextLine();
                    }
                    System.out.print("Input command: ");
                    input = scanner.nextLine().split(" ");
                    array = new char[3][3];
                }
            } else if (input[0].equals("exit")) {
                break;
            } else {
                System.out.println("Bad parameters!");
                System.out.print("Input command: ");
                input = scanner.nextLine().split(" ");
            }
        }

    }




    public void printTable() {
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
                if (array[i][j] != 'X' && array[i][j] != 'O') {
                    return 'C';
                }
            }
        }

        return 'N';
    }

    public void plotElement(int x, int y) {
        if (countX > countO) {
            array[x - 1][y - 1] = 'O';
            countO++;
        } else {
            array[x - 1][y - 1] = 'X';
            countX++;
        }
        printTable();
    }

    public int[] generateRandom() {
        int[] temp = new int[2];
        Random random = new Random();
        int x = random.nextInt(3) + 1;
        int y = random.nextInt(3) + 1;
        while (array[x - 1][y - 1] == 'X' || array[x - 1][y - 1] == 'O') {
            x = random.nextInt(3) + 1;
            y = random.nextInt(3) + 1;
        }
        temp[0] = x;
        temp[1] = y;
        return temp;
    }

    public void copmuterPlay(String name) {
        int[] temp = new int[2];
        if (name.equals("easy")) {
            temp = generateRandom();
        } else if (name.equals("medium")) {
            temp = OWin();

            if (temp[0] == 5){
                temp = checkPossibleMove();
            }

            //System.out.println("Got checkpossible array " + temp[0] + " " + temp[1]);

            if (temp[0] != 5) {
                temp[0] += 1;
                temp[1] += 1;
            }

            if (temp[0] == 5) {
                temp = generateRandom();
            }
        } else if(name.equals("hard")){

            Move move = findBestMove(array);
            System.out.println("Best moves: " + move.row + " " + move.col);
            temp[0] = move.row + 1;
            temp[1] = move.col + 1;
        }
        //System.out.println("Passing x: " + temp[0] + " pasing y: " + temp[1]);
        plotElement(temp[0], temp[1]);
    }

    public void userPlay() {
        while (true) {
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
            } else if (array[x - 1][y - 1] == 'X' || array[x - 1][y - 1] == 'O') {
                System.out.println("This cell is occupied! Choose another one!");
            } else {
                plotElement(x, y);
                break;
            }
        }

    }

    public void easyToEasy() {
        while (true) {
            char c = checkForWin();
            if (c == 'X') {
                System.out.println("X wins");
                break;
            } else if (c == 'O') {
                System.out.println("O wins");
                break;
            } else if (c == 'N') {
                System.out.println("Draw");
                break;
            }
            System.out.println("Making move level \"easy\"");
            copmuterPlay("easy");
        }
    }

    public void userToEasy() {
        boolean round = true;
        while (true) {
            char c = checkForWin();
            if (c == 'X') {
                System.out.println("X wins");
                break;
            } else if (c == 'O') {
                System.out.println("O wins");
                break;
            } else if (c == 'N') {
                System.out.println("Draw");
                break;
            }
            if (round) {
                userPlay();
                round = false;
            } else {
                System.out.println("Making move level \"easy\"");
                copmuterPlay("easy");
                round = true;
            }

        }
    }

    public void easyToUser() {

        boolean round = true;
        while (true) {
            char c = checkForWin();
            if (c == 'X') {
                System.out.println("X wins");
                break;
            } else if (c == 'O') {
                System.out.println("O wins");
                break;
            } else if (c == 'N') {
                System.out.println("Draw");
                break;
            }
            if (round) {
                System.out.println("Making move level \"easy\"");
                copmuterPlay("easy");
                round = false;
            } else {
                userPlay();
                round = true;
            }

        }

    }

    public void useToUser() {
        while (true) {
            char c = checkForWin();
            if (c == 'X') {
                System.out.println("X wins");
                break;
            } else if (c == 'O') {
                System.out.println("O wins");
                break;
            } else if (c == 'N') {
                System.out.println("Draw");
                break;
            }
            userPlay();
        }
    }

    public void mediumToUser() {
        boolean round = false;
        while (true) {
            char c = checkForWin();
            if (c == 'X') {
                System.out.println("X wins");
                break;
            } else if (c == 'O') {
                System.out.println("O wins");
                break;
            } else if (c == 'N') {
                System.out.println("Draw");
                break;
            }
            if (round) {
                System.out.println("Making move level \"medium\"");
                copmuterPlay("medium");
                round = false;
            } else {
                userPlay();
                round = true;
            }
        }
    }

    public int[] checkPossibleMove() {
        int[] temp = new int[2];
        //System.out.println("check possible called....");
        for (int i = 0; i < 3; i++) {
            if ((array[i][0] == array[i][1] && (array[i][0] != '\u0000')) && (array[i][2] == '\u0000')) {
                //System.out.println("from 1: ");
                temp[0] = i;
                temp[1] = 2;
                return temp;
            } else if ((array[i][0] == array[i][2] && (array[i][0] != '\u0000')) && (array[i][1] == '\u0000')) {
                //System.out.println("from 2: ");

                temp[i] = i;
                temp[1] = 1;
                return temp;
            } else if ((array[i][1] == array[i][2] && (array[i][1] != '\u0000')) && (array[i][0] == '\u0000')) {
                //System.out.println("from 3: ");

                temp[0] = i;
                temp[1] = 0;
                return temp;
            }
        }
        for (int i = 0; i < 3; i++) {
            if ((array[0][i] == array[1][i] && (array[0][1] != '\u0000')) && (array[2][i] == '\u0000')) {
                //System.out.println("from 4: ");
                temp[0] = 2;
                temp[1] = i;
                return temp;
            } else if ((array[0][i] == array[2][i] && (array[0][i] != '\u0000')) && (array[1][i] == '\u0000')) {
                ////System.out.println("from 5: ");
                temp[0] = 1;
                temp[1] = i;
                return temp;
            } else if ((array[1][i] == array[2][i] && (array[1][i] != '\u0000')) && (array[0][i] == '\u0000')) {
                //System.out.println("from 6: ");

                temp[0] = 0;
                temp[1] = i;
                return temp;
            }
        }
        if ((array[0][0] == array[1][1] && (array[0][0] != '\u0000')) && (array[2][2] == '\u0000')) {
            //System.out.println("from 7: ");

            temp[0] = 2;
            temp[1] = 2;
            return temp;
        } else if ((array[0][0] == array[2][2] && (array[0][0] != '\u0000')) && (array[1][1] == '\u0000')) {
            //System.out.println("from 8: ");

            temp[0] = 1;
            temp[1] = 1;
            return temp;
        } else if ((array[1][1] == array[2][2] && (array[1][1] != '\u0000')) && (array[0][0] == '\u0000')) {
            //System.out.println("from 9: ");

            temp[0] = 0;
            temp[1] = 0;
            return temp;
        }

        if ((array[0][2] == array[1][1] && (array[0][2] != '\u0000')) && (array[0][1] == '\u0000')) {
           // System.out.println("from 10: ");

            temp[0] = 2;
            temp[1] = 0;
            return temp;
        } else if ((array[0][2] == array[2][0] && (array[0][2] != '\u0000')) && (array[1][1] == '\u0000')) {
            //System.out.println("from 11: ");

            temp[0] = 1;
            temp[1] = 1;
            return temp;
        } else if ((array[1][1] == array[2][0] && (array[1][0] != '\u0000')) && (array[0][2] == '\u0000')) {
            //System.out.println("from 13: ");

            temp[0] = 0;
            temp[1] = 2;
            return temp;
        }

        temp[0] = 5;
        temp[1] = 5;

        return temp;
    }

    public int[] OWin() {
        int[] temp = new int[2];
        //System.out.println("check possible called....");
        for (int i = 0; i < 3; i++) {
            if ((array[i][0] == array[i][1] && (array[i][0] == 'O')) && (array[i][2] == '\u0000')) {
                System.out.println("from 1: ");
                temp[0] = i;
                temp[1] = 2;
                return temp;
            } else if ((array[i][0] == array[i][2] && (array[i][0] == 'O')) && (array[i][1] == '\u0000')) {
                System.out.println("from 2: ");

                temp[0] = i;
                temp[1] = 1;
                return temp;
            } else if ((array[i][1] == array[i][2] && (array[i][1] == 'O')) && (array[i][0] == '\u0000')) {
                System.out.println("from 3: ");

                temp[0] = i;
                temp[1] = 0;
                return temp;
            }
        }
        for (int i = 0; i < 3; i++) {
            if ((array[0][i] == array[1][i] && (array[0][1] == 'O')) && (array[2][i] == '\u0000')) {
                System.out.println("from 4: ");
                temp[0] = 2;
                temp[1] = i;
                return temp;
            } else if ((array[0][i] == array[2][i] && (array[0][i] == 'O')) && (array[1][i] == '\u0000')) {
                System.out.println("from 5: ");
                temp[0] = 1;
                temp[1] = i;
                return temp;
            } else if ((array[1][i] == array[2][i] && (array[1][i] == 'O')) && (array[0][i] == '\u0000')) {
                System.out.println("from 6: ");

                temp[0] = 0;
                temp[1] = i;
                return temp;
            }
        }
        if ((array[0][0] == array[1][1] && (array[0][0] == 'O')) && (array[2][2] == '\u0000')) {
            System.out.println("from 7: ");

            temp[0] = 2;
            temp[1] = 2;
            return temp;
        } else if ((array[0][0] == array[2][2] && (array[0][0] == 'O')) && (array[1][1] == '\u0000')) {
            System.out.println("from 8: ");

            temp[0] = 1;
            temp[1] = 1;
            return temp;
        } else if ((array[1][1] == array[2][2] && (array[1][1] == 'O')) && (array[0][0] == '\u0000')) {
            System.out.println("from 9: ");

            temp[0] = 0;
            temp[1] = 0;
            return temp;
        }

        if ((array[0][2] == array[1][1] && (array[0][2] == 'O')) && (array[0][1] == '\u0000')) {
            System.out.println("from 10: ");

            temp[0] = 2;
            temp[1] = 0;
            return temp;
        } else if ((array[0][2] == array[2][0] && (array[0][2] == 'O')) && (array[1][1] == '\u0000')) {
            System.out.println("from 11: ");

            temp[0] = 1;
            temp[1] = 1;
            return temp;
        } else if ((array[1][1] == array[2][0] && (array[1][0] == 'O')) && (array[0][2] == '\u0000')) {
            System.out.println("from 13: ");

            temp[0] = 0;
            temp[1] = 2;
            return temp;
        }

        temp[0] = 5;
        temp[1] = 5;

        return temp;
    }

    public void mediumToMedium() {
        while (true) {
            char c = checkForWin();
            if (c == 'X') {
                System.out.println("X wins");
                break;
            } else if (c == 'O') {
                System.out.println("O wins");
                break;
            } else if (c == 'N') {
                System.out.println("Draw");
                break;
            }
                System.out.println("Making move level \"medium\"");
                copmuterPlay("medium");

        }
    }


    public void hardToUser(){
        boolean round = true;
        while (true) {
            char c = checkForWin();
            if (c == 'X') {
                System.out.println("X wins");
                break;
            } else if (c == 'O') {
                System.out.println("O wins");
                break;
            } else if (c == 'N') {
                System.out.println("Draw");
                break;
            }
            if (round) {
                System.out.println("Making move level \"Hard\"");
                copmuterPlay("hard");
                round = false;
            } else {
                userPlay();
                round = true;
            }
        }
    }
    public void userToHard(){
        boolean round = false;
        while (true) {
            char c = checkForWin();
            if (c == 'X') {
                System.out.println("X wins");
                break;
            } else if (c == 'O') {
                System.out.println("O wins");
                break;
            } else if (c == 'N') {
                System.out.println("Draw");
                break;
            }
            if (round) {
                System.out.println("Making move level \"Hard\"");
                copmuterPlay("hard");
                round = false;
            } else {
                userPlay();
                round = true;
            }
        }
    }

    public void hardToHard(){
        while (true) {
            char c = checkForWin();
            if (c == 'X') {
                System.out.println("X wins");
                break;
            } else if (c == 'O') {
                System.out.println("O wins");
                break;
            } else if (c == 'N') {
                System.out.println("Draw");
                break;
            }
                System.out.println("Making move level \"Hard\"");
                copmuterPlay("hard");

        }
    }



    public Boolean isMovesLeft(char[][] board)
    {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board[i][j] == ' ')
                    return true;
        return false;
    }


    // Evaluation for AI minimax
    public int evaluate(char b[][])
    {
        // Checking for Rows for X or O victory.
        for (int row = 0; row < 3; row++)
        {
            if (b[row][0] == b[row][1] && b[row][1] == b[row][2])
            {
                if (b[row][0] == 'X')
                    return +10;
                else if (b[row][0] == 'O')
                    return -10;
            }
        }

        // Checking for Columns for X or O victory.
        for (int col = 0; col < 3; col++)
        {
            if (b[0][col] == b[1][col] && b[1][col] == b[2][col])
            {
                if (b[0][col] == 'X')
                    return +10;
                else if (b[0][col] == 'O')
                    return -10;
            }
        }

        // Checking for Diagonals for X or O victory.
        if (b[0][0] == b[1][1] && b[1][1] == b[2][2])
        {
            if (b[0][0] == 'X')
                return +10;
            else if (b[0][0] == 'O')
                return -10;
        }
        if (b[0][2] == b[1][1] && b[1][1] == b[2][0])
        {
            if (b[0][2] == 'X')
                return +10;
            else if (b[0][2] == 'O')
                return -10;
        }

        // Else if none of them have won then return 0
        return 0;
    }

    //minimax algo
    public int minimax(char[][] board,
                       int depth, Boolean isMax)
    {
        int score = evaluate(board);

        // If Maximizer has won the game
        // return his/her evaluated score
        if (score == 10)
            return score;

        // If Minimizer has won the game
        // return his/her evaluated score
        if (score == -10)
            return score;

        // If there are no more moves and
        // no winner then it is a tie
        if (!isMovesLeft(board))
            return 0;

        // If this maximizer's move
        if (isMax)
        {
            int best = -1000;

            // Traverse all cells
            for (int i = 0; i < 3; i++)
            {
                for (int j = 0; j < 3; j++)
                {
                    // Check if cell is empty
                    if (board[i][j]=='\u0000')
                    {
                        // Make the move
                        board[i][j] = player;

                        // Call minimax recursively and choose
                        // the maximum value
                        best = Math.max(best, minimax(board,
                                depth + 1, !isMax));

                        // Undo the move
                        board[i][j] = '\u0000';
                    }
                }
            }
            return best;
        }

        // If this minimizer's move
        else
        {
            int best = 1000;

            // Traverse all cells
            for (int i = 0; i < 3; i++)
            {
                for (int j = 0; j < 3; j++)
                {
                    // Check if cell is empty
                    if (board[i][j] == '\u0000')
                    {
                        // Make the move
                        board[i][j] = opponent;

                        // Call minimax recursively and choose
                        // the minimum value
                        best = Math.min(best, minimax(board,
                                depth + 1, !isMax));

                        // Undo the move
                        board[i][j] = '\u0000';
                    }
                }
            }
            return best;
        }
    }

    //each step find best move
    public Move findBestMove(char[][] board)
    {
        int bestVal = -1000;
        Move bestMove = new Move();
        bestMove.row = -1;
        bestMove.col = -1;

        // Traverse all cells, evaluate minimax function
        // for all empty cells. And return the cell
        // with optimal value.
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                // Check if cell is empty
                if (board[i][j] == '\u0000')
                {
                    // Make the move
                    board[i][j] = player;

                    // compute evaluation function for this
                    // move.
                    int moveVal = minimax(board, 0, false);

                    // Undo the move
                    board[i][j] = '\u0000';

                    // If the value of the current move is
                    // more than the best value, then update
                    // best/
                    if (moveVal > bestVal)
                    {
                        bestMove.row = i;
                        bestMove.col = j;
                        bestVal = moveVal;
                    }
                }
            }
        }



        return bestMove;
    }

}

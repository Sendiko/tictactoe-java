import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        char[][] board = {
                {' ', ' ', ' '},
                {' ', ' ', ' '},
                {' ', ' ', ' '}
        };

        Scanner scanner = new Scanner(System.in);
        int currentPlayer = 1;
        boolean gameWon = false;

        while (!gameWon){
            displayBoard(board);
            System.out.println("Player " + currentPlayer + ", enter row and column (e.g., 1 2): ");

            // - 1 to adjust to array
            int row = scanner.nextInt() - 1;
            int column = scanner.nextInt() - 1;

            if (isValidMove(row, column, board)){
                board[row][column] = (currentPlayer == 1) ? 'X' : 'O';
                currentPlayer = 3 - currentPlayer;

                if (checkForWin(board)){
                    gameWon = true;
                    displayBoard(board);
                    // 3 to indicate who's playing
                    System.out.println("Player " + (3 - currentPlayer) + " wins!");
                } else if (isBoardFull(board)) {
                    gameWon = true;
                    displayBoard(board);
                    System.out.println("It's a draw!");
                }
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }

        scanner.close();
    }

    public static boolean isValidMove(int row, int col, char[][] board) {
        return (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ');
    }

    public static boolean checkForWin(char[][] board) {
        // check the rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != ' ' && board[i][0] == board[i][1] && board[i][0] == board[i][2]) {
                return true;
            }
        }

        // check the columns
        for (int j = 0; j < 3; j++) {
            if (board[0][j] != ' ' && board[0][j] == board[1][j] && board[0][j] == board[2][j]) {
                return true;
            }
        }

        // check the diagonals
        if (board[0][0] != ' ' && board[0][0] == board[1][1] && board[0][0] == board[2][2]) {
            return true;
        }
        if (board[0][2] != ' ' && board[0][2] == board[1][1] && board[0][2] == board[2][0]) {
            return true;
        }

        return false;
    }

    public static boolean isBoardFull(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public static void displayBoard(char[][] board){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j]);
                if (j < 2){
                    System.out.print(" | ");
                }
            }
            System.out.println();
            if(i < 2){
                System.out.println("---------");
            }
        }
    }
}
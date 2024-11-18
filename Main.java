import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain = true;

        while (playAgain) {
            char[][] board = initializeBoard();
            char currentPlayer = 'X';
            boolean gameOver = false;
            int moves = 0;

            while (!gameOver) {
                printBoard(board);
                System.out.println("Player " + currentPlayer + ", enter your move (row and column, 0-2): ");

                int row = -1, col = -1;
                boolean validInput = false;

                // Input validation loop
                while (!validInput) {
                    if (scanner.hasNextInt()) {
                        row = scanner.nextInt();
                        col = scanner.nextInt();
                        if (row >= 0 && row < 3 && col >= 0 && col < 3) {
                            if (board[row][col] == ' ') {
                                validInput = true;
                            } else {
                                System.out.println("Cell already occupied. Try again:");
                            }
                        } else {
                            System.out.println("Invalid indices. Enter numbers between 0 and 2:");
                        }
                    } else {
                        System.out.println("Invalid input. Please enter two integers:");
                        scanner.next();
                    }
                }

                // check for win/draw
                board[row][col] = currentPlayer;
                moves++;
                if (haveWon(board, currentPlayer)) {
                    printBoard(board);
                    System.out.println("Player " + currentPlayer + " has won!");
                    gameOver = true;
                } else if (moves == 9) {
                    printBoard(board);
                    System.out.println("The game is a draw!");
                    gameOver = true;
                } else {
                    currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                }
            }

            // Ask if players want to play again
            // Ask if players want to play again
             System.out.println("Do you want to play again? (yes/no)");
             scanner.nextLine(); // Clear the buffer before reading the next line
             playAgain = scanner.nextLine().equalsIgnoreCase("yes");

        }

        System.out.println("Thanks for playing!");
        scanner.close();
    }

    // Initializes the game board
    public static char[][] initializeBoard() {
        char[][] board = new char[3][3];
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = ' ';
            }
        }
        return board;
    }

    // Check if the current player has won
    public static boolean haveWon(char[][] board, char player) {
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == player && board[i][1] == player && board[i][2] == player) ||
                    (board[0][i] == player && board[1][i] == player && board[2][i] == player)) {
                return true;
            }
        }


        if ((board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
                (board[0][2] == player && board[1][1] == player && board[2][0] == player)) {
            return true;
        }

        return false;
    }

    // Prints game board
    public static void printBoard(char[][] board) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                System.out.print(" " + board[row][col]);
                if (col < 2) System.out.print(" |"); // Column
            }
            System.out.println();
            if (row < 2) System.out.println("---+---+---"); // Row
        }
    }
}

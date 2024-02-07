package tictactoe;

import java.util.Scanner;

public class TicTacToe {
    public static final char[] BOX = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};
    public static final String SEPARATION_BETWEEN_LINES = "-----------";
    public static final int NUMBER_OF_BOXES = BOX.length;
    public static final char SYMBOL_FOR_PLAYER = 'X';
    public static final char SYMBOL_FOR_COMPUTER = 'O';
    public static final byte PLAYER = 1;
    public static final byte COMPUTER = 2;
    public static final byte DRAW = 3;
    private static byte winner = 0;
    private static boolean boxIsEmpty = false;
    private static boolean gameComing = true;
    private static final Scanner scan = new Scanner(System.in);


    public void gameStart() {
        System.out.println("Enter box number to select. Enjoy!");
        while (gameComing) {
            drawPlayingField();
            fillBoxes();
            if (!checkWinner()) {
                break;
            }
            checkIfBoxFree();
            checkWinningCombinations(SYMBOL_FOR_PLAYER, PLAYER);
            if (isThisADraw()) {
                continue;
            }
            computerMove();
            checkWinningCombinations(SYMBOL_FOR_COMPUTER, COMPUTER);
        }
    }

    public void drawPlayingField() {
        int counter = 1;
        for (int i = 0; i < BOX.length; i++) {
            System.out.print(BOX[i] + " | ");
            if (counter % 3 == 0) {
                System.out.println();
                System.out.println(SEPARATION_BETWEEN_LINES);
            }
            counter++;
        }
    }

    public void fillBoxes() {
        if (!boxIsEmpty) {
            for (byte indexBox = 0; indexBox < NUMBER_OF_BOXES; indexBox++)
                BOX[indexBox] = ' ';
            boxIsEmpty = true;
        }
    }

    public boolean checkWinner() {
        return switch (winner) {
            case PLAYER -> {
                System.out.println("You won the game!\nCreated by Shreyas Saha. Thanks for playing!");
                yield false;
            }
            case COMPUTER -> {
                System.out.println("You lost the game!\nCreated by Shreyas Saha. Thanks for playing!");
                yield false;
            }
            case DRAW -> {
                System.out.println("It's a draw!\nCreated by Shreyas Saha. Thanks for playing!");
                yield false;
            }
            default -> true;
        };
    }

    public void checkIfBoxFree() {

        while (true) {
            byte cellNumber = scan.nextByte();
            if (cellNumber > 0 && cellNumber <= NUMBER_OF_BOXES) {
                if (BOX[cellNumber - 1] == 'X' || BOX[cellNumber - 1] == 'O')
                    System.out.println("That one is already in use. Enter another.");
                else {
                    BOX[cellNumber - 1] = 'X';
                    break;
                }
            } else
                System.out.println("Invalid input. Enter again.");

        }
    }

    public void checkWinningCombinations(char symbol, byte identifier) {
        if (horizontalLines(symbol)
                || verticalLines(symbol)
                || diagonalLines(symbol)) {
            winner = identifier;
        }
    }

    public boolean horizontalLines(char symbol) {
        return (BOX[0] == symbol && BOX[1] == symbol && BOX[2] == symbol)
                || (BOX[3] == symbol && BOX[4] == symbol && BOX[5] == symbol)
                || (BOX[6] == symbol && BOX[7] == symbol && BOX[8] == symbol);
    }

    public boolean verticalLines(char symbol) {
        return (BOX[0] == symbol && BOX[3] == symbol && BOX[6] == symbol)
                || (BOX[1] == symbol && BOX[4] == symbol && BOX[7] == symbol)
                || (BOX[2] == symbol && BOX[5] == symbol && BOX[8] == symbol);
    }

    public boolean diagonalLines(char symbol) {
        return (BOX[0] == symbol && BOX[4] == symbol && BOX[8] == symbol)
                || (BOX[2] == symbol && BOX[4] == symbol && BOX[6] == symbol);
    }

    public void computerMove() {
        while (true) {
            byte randomCellForComputer = (byte) (Math.random() * BOX.length + 1);
            if (BOX[randomCellForComputer - 1] != 'X' && BOX[randomCellForComputer - 1] != 'O') {
                BOX[randomCellForComputer - 1] = 'O';
                break;
            }
        }
    }

    public boolean isThisADraw() {
        byte indexBox;
        boolean boxAvailable = false;
        for (indexBox = 0; indexBox < BOX.length; indexBox++) {
            if (BOX[indexBox] != 'X' && BOX[indexBox] != 'O') {
                boxAvailable = true;
                break;
            }
        }
        if (!boxAvailable) {
            winner = DRAW;
            return true;
        }
        return false;
    }
}

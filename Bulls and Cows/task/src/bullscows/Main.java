package bullscows;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static int inputLength;
    private static int inputSymbols;
    private static StringBuilder code = new StringBuilder();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Input the length of the secret code:");
        try {
            String firstInput = sc.nextLine();
            inputLength = Integer.parseInt(firstInput);
            if (inputLength == 0) {
                System.out.printf("Error: %s isn't a valid number.",inputLength);
                System.exit(0);
            }
        } catch (RuntimeException e) {
            System.out.printf("Error: %s isn't a valid number.",inputLength);
            System.exit(0);
        }
        System.out.println("Input the number of possible symbols in the code:");

        try {
            String secondInput = sc.next();
            inputSymbols = Integer.parseInt(secondInput);

            if (inputSymbols == 0) {
                System.out.printf("Error: %s isn't a valid number.",inputSymbols);
                System.exit(0);
            } else if (inputSymbols > 36) {
                System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
            } else if (inputLength > inputSymbols) {
                System.out.printf("Error: it's not possible to generate a code with a length of %d with %d unique symbols.", inputLength, inputSymbols);
                    System.exit(0);
            } else {
                while (code.length() != inputLength) {
                    code = generateCode(inputLength, inputSymbols);
                    checkUnique(code);
                    }
                }
        } catch (RuntimeException e ) {
            System.out.printf("%s isn't a valid number.",inputSymbols);
            System. exit(0);
        }
        playGame(code,inputLength,inputSymbols);
    }

    // Gameplay method
    private static void playGame(StringBuilder code,int inputLength, int inputSymbols) {
        Scanner sc = new Scanner(System.in);
        String guess = "";
        System.out.printf("The secret is prepared: %s (%s).%n",generateAsterisk(inputLength),alphaNum(inputSymbols));
        int turn = 1;
        while (!code.toString().equals(guess)) {
            System.out.printf("Turn %d: %n", turn);
            guess = sc.next();
            int cow = getCows(code, guess);
            int bull = getBulls(code, guess);
            printResult(cow, bull);
            if (bull != code.length()) {
                turn++;
            } else {
                System.out.println("Congratulations! You guessed the secret code.");
                break;
            }
        }
    }
    // Method to generate secret code
    private static StringBuilder generateCode (int inputLength,int inputSymbols) {
        StringBuilder symbolString = new StringBuilder();
        Random random = new Random();
        char x = 48;

        if (inputSymbols < 11) {
            for (int i = 0; i < inputSymbols; i++) {
                symbolString.append(x);
                x++;
            }
        } else {
                for (int i = 0; i < 10; i++) {
                    symbolString.append(x);
                    x++;
                }
                x = 97;
                for (int j = 0; j < inputSymbols-10; j++) {
                    symbolString.append(x);
                    x++;
            }
        }
        while (code.length() != inputLength) {
            code.append(symbolString.charAt(random.nextInt(inputSymbols)));
        }
        return code;
    }

    //Method for generating asterisks
    private static StringBuilder generateAsterisk(int inputLength) {
        StringBuilder asterisks = new StringBuilder();
        asterisks.append("*".repeat(Math.max(0, inputLength)));
        return asterisks;
    }

    //Method for generating num/alpha range
    private static StringBuilder alphaNum (int inputSymbols) {
        StringBuilder alphaNum = new StringBuilder();
        char x;
        if (inputSymbols == 1) {
            alphaNum.append('0');
        } else if (inputSymbols > 1 && inputSymbols < 11) {
            alphaNum.append('0');
            alphaNum.append('-');
            if (inputSymbols == 10) {
                alphaNum.append('9');
            } else {
                alphaNum.append(inputSymbols);
            }
        } else if (inputSymbols == 11) {
            alphaNum.append('0');
            alphaNum.append('-');
            alphaNum.append('9');
            alphaNum.append(',');
            alphaNum.append(' ');
            x = 97;
            alphaNum.append(x);
        } else if (inputSymbols > 11) {
            alphaNum.append('0');
            alphaNum.append('-');
            alphaNum.append('9');
            alphaNum.append(',');
            alphaNum.append(' ');
            alphaNum.append('a');
            alphaNum.append('-');
            x = 97;
            x += (char) (inputSymbols-11);
            alphaNum.append(x);
        } return alphaNum;
    }


    //Method to check for uniqueness
    private static void checkUnique (StringBuilder code) {
        for (int i = 0; i < code.length(); i++) {
            for (int j = i + 1; j < code.length(); j++) {
                if (code.charAt(i) == code.charAt(j)) {
                    code.deleteCharAt(j);
                }
            }
        }
    }

    // Method that prints the result of every guess
    private static void printResult(int cow, int bull) {
        if (bull == 0 && cow != 0) {
            System.out.printf("Grade: %d cow(s).%n",cow);
        } else if (cow == 0 && bull != 0) {
            System.out.printf("Grade: %d bull(s).%n",bull);
        } else if (bull > 0 && cow > 0) {
            System.out.printf("Grade: %d bull(s) and %d cow(s).%n",bull,cow);
        } else {
            System.out.printf("Grade: None.%n");
        }
    }

    private static int getBulls(StringBuilder code,String inputLength) {
        int counter = 0;
        for (int i = 0; i < inputLength.length(); i++) {
            for (int j = 0; j < code.length(); j++) {
                if (inputLength.charAt(i) == code.charAt(j) && i == j) {
                    counter++;
                }
                }
            } return counter;
        }
    private static int getCows(StringBuilder code,String inputLength) {
        int counter = 0;
        for (int i = 0; i < inputLength.length(); i++) {
            for (int j = 0; j < code.length(); j++) {
                if (inputLength.charAt(i) == code.charAt(j) && i != j) {
                    counter++;
                }
            }
        } return counter;
    }
}


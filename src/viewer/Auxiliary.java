package viewer;

import java.util.Scanner;

/**
 * This class allows a user to validate an input of a
 * certain data type or print aesthetic elements
 * in their code.
 * 
 * @author Sean Benedict Bernardo
 */
public class Auxiliary {
    private static Scanner scanner = new Scanner(System.in);

    // Printing Functions

    /**
     * Prints a horizontal bar
     * 
     * @param character character used to print the bar
     * @param len       length of the bar to be printed
     */
    public static void printBar(char character, int len) {
        for (int i = 0; i < len; i++)
            System.out.print(character);
        System.out.println();
    }

    /**
     * Prints a horizontal bar with a certain length with default character '='
     * 
     * @param len length of the bar to be printed
     */
    public static void printBar(int len) {
        printBar('=', len);
    };

    /**
     * Prints a formatted prompt given a string
     * 
     * @param prompt string to be included in the prompt
     */
    private static void printPrompt(String prompt) {
        System.out.print(">>> " + prompt + ": ");
    }

    /*
     * User input functions follow
     */

    /**
     * Overloaded method of getString(String prompt)
     * Used if first instance of getString within method
     * 
     * @param prompt string to be included in the prompt
     * @return string user inputs in the current line
     */
    public static String getString(String prompt) {
        return getString(prompt, false);
    }

    /**
     * Prompts user for a praticular string input and returns the next line of input
     * from the user
     * 
     * @param prompt   string to be included in the prompt
     * @param isRepeat boolean of whether it has been used previously,
     *                 true if getString was used successively
     * @return string user inputs in the current line
     */
    public static String getString(String prompt, boolean isRepeat) {

        if (prompt.equals(">>> "))
            System.out.print(prompt);
        else
            printPrompt(prompt);

        // flush buffer
        if (scanner.hasNext() && !isRepeat) {
            scanner.nextLine();
        }

        String strUser = scanner.nextLine();

        return strUser;
    }

    /**
     * Prompts user for a particular integer input and
     * returns the next integer from the user
     * 
     * @param prompt string to be included in the prompt
     * @return first integer user inputs in the current line
     */
    public static int getInt(String prompt) {

        printPrompt(prompt);
        int userInt = -1;

        // flush buffer
        // thanks stack overflow
        // https://stackoverflow.com/a/18275695
        while (scanner.hasNext()) {
            if (scanner.hasNextInt()) {
                userInt = scanner.nextInt();
                break;
            } else {
                scanner.next();
            }
        }

        return userInt;
    }

    /**
     * Prompts user for an integer from a range of numbers and returns the next
     * valid integer from the user
     * 
     * @param prompt string to be included in the prompt
     * @param min    lower bound of the range
     * @param max    upper bound of the range
     * @return first integer user inputs in the current line
     */
    public static int getInt(String prompt, int min, int max) {
        printPrompt(prompt);
        int userInt = -1;

        while (userInt < min || max < userInt) {
            while (scanner.hasNext()) {
                if (scanner.hasNextInt()) {
                    userInt = scanner.nextInt();
                    break;
                } else {
                    scanner.next(); // Just discard this, not interested...
                }
            }
            if (userInt < min || max < userInt)
                System.out.print(">>> ");
        }

        return userInt;
    }

    /**
     * Prompts user for a particular integer input and
     * returns the next integer from the user
     * 
     * @param prompt string to be included in the prompt
     * @param min    any float greater then this number is
     *               a valid input
     * @return first integer user inputs in the current line
     */
    public static float getFloat(String prompt, float min) {

        printPrompt(prompt);
        float userFloat = -1.0f;

        while (scanner.hasNext()) {
            if (scanner.hasNextFloat()) {
                userFloat = scanner.nextFloat();
                if (userFloat < min)
                    System.out.print(">>> ");
                else
                    break;
            } else {
                scanner.next();
            }
        }

        return userFloat;
    }

    /**
     * Prompts the user for a boolean input and returns the boolean input
     * 'Y' is true
     * 'N' is false
     * 
     * @param prompt string to be included in the prompt
     * @return returns true if first character user inputs in the line is 'Y'
     *         returns false if first character user inputs in the line is 'N'
     */
    public static boolean getBoolean(String prompt) {
        char userChar;

        printPrompt("[Y/N] " + prompt);

        userChar = Character.toUpperCase(scanner.next().charAt(0));

        while (userChar != 'Y' && userChar != 'N') {
            System.out.print(">>> ");
            userChar = Character.toUpperCase(scanner.next().charAt(0));
        }

        return userChar == 'Y';
    }
}

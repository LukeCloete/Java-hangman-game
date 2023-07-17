import java.util.Arrays;
import java.util.Scanner;
import java.lang.String;

public class Hangman {

    public static String[] words = { "ant", "baboon", "badger", "bat", "bear", "beaver", "camel",
            "cat", "clam", "cobra", "cougar", "coyote", "crow", "deer",
            "dog", "donkey", "duck", "eagle", "ferret", "fox", "frog", "goat",
            "goose", "hawk", "lion", "lizard", "llama", "mole", "monkey", "moose",
            "mouse", "mule", "newt", "otter", "owl", "panda", "parrot", "pigeon",
            "python", "rabbit", "ram", "rat", "raven", "rhino", "salmon", "seal",
            "shark", "sheep", "skunk", "sloth", "snake", "spider", "stork", "swan",
            "tiger", "toad", "trout", "turkey", "turtle", "weasel", "whale", "wolf",
            "wombat", "zebra" };

    public static String[] gallows = { "+---+\n" +
            "|   |\n" +
            "    |\n" +
            "    |\n" +
            "    |\n" +
            "    |\n" +
            "=========\n",

            "+---+\n" +
                    "|   |\n" +
                    "O   |\n" +
                    "    |\n" +
                    "    |\n" +
                    "    |\n" +
                    "=========\n",

            "+---+\n" +
                    "|   |\n" +
                    "O   |\n" +
                    "|   |\n" +
                    "    |\n" +
                    "    |\n" +
                    "=========\n",

            " +---+\n" +
                    " |   |\n" +
                    " O   |\n" +
                    "/|   |\n" +
                    "     |\n" +
                    "     |\n" +
                    " =========\n",

            " +---+\n" +
                    " |   |\n" +
                    " O   |\n" +
                    "/|\\  |\n" +
                    "     |\n" +
                    "     |\n" +
                    " =========\n",

            " +---+\n" +
                    " |   |\n" +
                    " O   |\n" +
                    "/|\\  |\n" +
                    "/    |\n" +
                    "     |\n" +
                    " =========\n",

            " +---+\n" +
                    " |   |\n" +
                    " O   |\n" +
                    "/|\\  |\n" +
                    "/ \\  |\n" +
                    "     |\n" +
                    " =========\n" };

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        String wordToGuess = getRandomWord(words);

        char[] placeholders = new char[wordToGuess.length()];
        for (int i = 0; i < placeholders.length; i++) {
            placeholders[i] = '_';
        }
        int hitCounter = 0;
        int missCounter = 0;
        char[] missedGuesses = new char[6];

        while (missCounter < 6) {
            System.out.println(gallows[missCounter]);
            System.out.print("Word:\t");
            printPlaceholders(placeholders);
            System.out.print("\nMisses:   ");
            printMissedGuesses(missedGuesses);
            System.out.print("\n\n");
            System.out.print("Guess: ");
            char playerGuess = scan.next().charAt(0);

            if (isGuessCorrect(wordToGuess, playerGuess)) {
                hitCounter++;
                updatePlaceholders(placeholders, wordToGuess, playerGuess);
            } else {
                missedGuesses[missCounter] = playerGuess;
                missCounter++;
            }

            if (Arrays.equals(wordToGuess.toCharArray(), placeholders) && missCounter == 0) {
                System.out.print(gallows[missCounter]);
                System.out.print("\nWord:\t");
                printPlaceholders(placeholders);
                System.out.println("\nFLAWLESS WORK!");
                break;
            } else if (Arrays.equals(wordToGuess.toCharArray(), placeholders) && missCounter == 5) {
                System.out.print(gallows[missCounter]);
                System.out.print("\nWord:\t");
                printPlaceholders(placeholders);
                System.out.println("\nCLOSE CALL! GOOD WORK!");
                break;
            } else if (Arrays.equals(wordToGuess.toCharArray(), placeholders) && missCounter < 5) {
                System.out.print(gallows[missCounter]);
                System.out.print("\nWord:\t");
                printPlaceholders(placeholders);
                System.out.println("\nGOOD WORK!");
                break;
            }
        }

        if (missCounter == 6 && hitCounter == 0) {
            System.out.print(gallows[missCounter]);
            System.out.println("\nRIP! Not even one letter?");
            System.out.println("\nThe word was: '" + wordToGuess + "'");
        } else if (missCounter == 6) {
            System.out.print(gallows[missCounter]);
            System.out.println("\nRIP!");
            System.out.println("\nThe word was: '" + wordToGuess + "'");
        }

        scan.close();
    }

    public static String getRandomWord(String[] words) {
        int randomNumber = (int) (Math.floor(Math.random() * words.length));
        return words[randomNumber];
    }

    public static void printPlaceholders(char[] placeholders) {
        for (int i = 0; i < placeholders.length; i++) {
            System.out.print(" " + placeholders[i]);
        }
        System.out.print("\n");
    }

    public static void updatePlaceholders(char[] placeholders, String wordToGuess, char guessedLetter) {
        guessedLetter = Character.toLowerCase(guessedLetter);
        for (int i = 0; i < wordToGuess.length(); i++) {
            if (wordToGuess.charAt(i) == guessedLetter) {
                placeholders[i] = guessedLetter;
            }
        }
    }

    public static boolean isGuessCorrect(String wordToGuess, char guessedLetter) {
        guessedLetter = Character.toLowerCase(guessedLetter);
        for (int i = 0; i < wordToGuess.length(); i++) {
            if (guessedLetter == wordToGuess.charAt(i)) {
                return true;
            }
        }
        return false;
    }

    public static void printMissedGuesses(char[] missedLetters) {
        for (int i = 0; i < missedLetters.length; i++) {
            System.out.print(missedLetters[i]);
        }
    }

}

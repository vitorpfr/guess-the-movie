import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;
import java.util.Scanner;

public class Game {
    private final String movie;
    private String hiddenMovie;
    Set<Character> wrongGuesses = new TreeSet<Character>();

    // Constructor to start game
    Game(String movieTitle) {
        this.movie = movieTitle.toLowerCase();
        this.hiddenMovie = movie.replaceAll("[a-z]", "_");
        System.out.println("You are guessing: " + hiddenMovie);
    }

    // Checks if user input is valid
    private boolean isValidInput(String input) {
        boolean inputIsLetter = Character.isLetter(input.charAt(0));
        boolean inputIsOneDigit = input.length() == 1;
        boolean inputNotChosenYet = !wrongGuesses.contains(Character.toLowerCase(input.charAt(0))) && hiddenMovie.indexOf(Character.toLowerCase(input.charAt(0))) == -1;
        return inputIsLetter && inputIsOneDigit && inputNotChosenYet;
    }

    // Get input from user until receiving a valid one
    public char getLetterFromUser() {
        // Ask user for a letter
        System.out.println("Guess a letter: ");
        Scanner scanner = new Scanner(System.in);
        String guess = scanner.next();

        // If input is not valid, keep asking for an input until getting a valid one
        while (!isValidInput(guess)) {
            System.out.println("This is not a valid new letter! Try again:");
            guess = scanner.next();
        }

        // Return lower-case character after confirming that it is valid
        return Character.toLowerCase(guess.charAt(0));
    }

    // Guess the letter that user inputted
    public void guessLetter(char guess) {
        // Check if guessed letter is in the title
        if (movie.indexOf(guess) >= 0) {
            // If yes, replace all occurrences of letter, from _ to the letter itself
            char[] movieToChars = hiddenMovie.toCharArray();
            int index = movie.indexOf(guess);
            while (index >= 0) {
                movieToChars[index] = guess;
                index = movie.indexOf(guess, index + 1);
            }
            this.hiddenMovie = String.valueOf(movieToChars);
        } else {
            // If no, register the wrong letter guess
            wrongGuesses.add(guess);
        }
        // Returns result of guess to user
        System.out.println("You have guessed (" + wrongGuesses.size() + ") wrong letters: " +  Arrays.toString(wrongGuesses.toArray()));
        System.out.println(hiddenMovie);
    }

    // Booleans that determine if the player won/lost and if game is finished
    public boolean won() { return movie.equals(hiddenMovie); }
    public boolean lost() { return wrongGuesses.size() >= 10; }
    public boolean finished() { return this.won() || this.lost(); }

    // Prints end result of game
    public void endGame() {
        if (this.won()) {
            System.out.println("Congratulations! You guessed the whole word!");
        } else if (this.lost()) {
            System.out.println("You spent all your points guessing and lost! Try to play a new game.");
        }
    }
}

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class GuessTheMovie {
    private static String getRandomTitle(String filepath) throws Exception {
        // Reads movies file and creates scanner object
        File file = new File(filepath);
        Scanner scanner = new Scanner(file);

        // Store all movies in a movie array
        ArrayList<String> titles = new ArrayList<String>();
        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            titles.add(line);
        }

        // Select and return a random movie from movie array
        Random rand = new Random();
        int numberOfTitles = titles.size();
        int randomIndex = rand.nextInt(numberOfTitles);
        return titles.get(randomIndex);
    }

    public static void main(String[] args) throws Exception {
        // Get a random movie from movies file
        String title = getRandomTitle("movies.txt");

        // Start game with chosen movie
        Game game = new Game(title);

        // Keep game running while final state hasn't been reached
        while(!game.finished()) {
            // Get a letter from user
            char letter = game.getLetterFromUser();

            // Try to guess letter in title
            game.guessLetter(letter);
        }

        // End game when final state was reached
        game.endGame();
    }
}

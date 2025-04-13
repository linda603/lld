package snakeandladder;

import java.util.Arrays;
import java.util.List;

public class GameDemo {
    public static void main(String[] args) {
        GameManager gameManager = GameManager.getInstance();

        // Game 1
        List<String> playerNames1 = Arrays.asList("Alice", "Bob", "Joey");
        GameManager.startNewGame(playerNames1);

        // Game 2
        List<String> playerNames2 = Arrays.asList("Anna", "Carl", "Dan", "Elie");
        GameManager.startNewGame(playerNames2);
    }
}

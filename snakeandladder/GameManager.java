package snakeandladder;

import java.util.ArrayList;
import java.util.List;

public class GameManager {
    private static GameManager instance;
    private static List<SnakeAndLadderGame> games;

    private GameManager() {
        games = new ArrayList<>();
    }

    public static synchronized GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    public static void startNewGame(List<String> playerNames) {
        SnakeAndLadderGame game = new SnakeAndLadderGame(playerNames);
        games.add(game);
        new Thread(() -> game.playGame()).start();
    }
}

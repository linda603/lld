package snakeandladder;

import java.util.ArrayList;
import java.util.List;

public class SnakeAndLadderGame {
    private final Board board;
    private final List<Player> players;
    private final Dice dice;
    private int currPlayerIdx;

    public SnakeAndLadderGame(List<String> names) {
        this.board = new Board();
        this.players = new ArrayList<>();
        this.dice = new Dice();
        this.currPlayerIdx = 0;
        for (String name : names) {
            this.players.add(new Player(name));
        }
    }

    public boolean isGameOver() {
        for (Player player : players) {
            if (player.getPosition() == board.getBoardSize()) {
                return true;
            }
        }
        return false;
    }

    public void playGame() {
        while (!isGameOver()) {
            Player currPlayer = players.get(currPlayerIdx);
            int diceRoll = dice.roll();
            int newPosition = currPlayer.getPosition() + diceRoll;

            if (newPosition <= board.getBoardSize()) {
                currPlayer.setPosition(board.getNewPosition(newPosition));
                System.out.println(currPlayer.getName() + " moved to position " + currPlayer.getPosition());
            }

            if (currPlayer.getPosition() == board.getBoardSize()) {
                System.out.println(currPlayer.getName() + " won!");
                break;
            }
            currPlayerIdx = (currPlayerIdx + 1) % players.size();
        }
    }

}

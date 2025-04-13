package snakeandladder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
    private static final int BOARD_SIZE = 100;
    private final Map<Integer, Snake> snakes;
    private final Map<Integer, Ladder> ladders;

    public Board() {
        snakes = new HashMap<>();
        ladders = new HashMap<>();
        initilizeBoard();
    }

    public void initilizeBoard() {
        // Initialize snakes
        snakes.put(17, new Snake(17, 6));
        snakes.put(34, new Snake(34, 22));
        snakes.put(80, new Snake(80, 72));

        // Initialize ladders
        ladders.put(1, new Ladder(1, 30));
        ladders.put(10, new Ladder(10, 29));
        ladders.put(50, new Ladder(50, 100));
        ladders.put(28, new Ladder(28, 84));
        ladders.put(80, new Ladder(80, 99));
    }

    public int getBoardSize() {
        return BOARD_SIZE;
    }

    // Check if position is at any snake or ladder position
    public int getNewPosition(int position) {
        if (snakes.containsKey(position)) {
            return snakes.get(position).getEnd();
        }
        if (ladders.containsKey(position)) {
            return ladders.get(position).getEnd();
        }
        return position;
    }
}

package chessgame;

import java.util.Scanner;

public class ChessGame {
    private Board board;
    private Player[] players;
    private int currentPlayerIdx;
    private Status status;

    public ChessGame() {
        this.board = new Board();
        this.players = new Player[]{new Player(Color.WHITE), new Player(Color.WHITE)};
        this.currentPlayerIdx = 0;
        this.status = Status.ACTIVE;
    }

    public void startGame() {
        while (!isGameOver()) {
            Player player = players[currentPlayerIdx];
            currentPlayerIdx = (currentPlayerIdx + 1) % players.length;
            Move move = getPlayerMove(player);

            if (board.isCheckmate(move.getPiece(), move.getDestRow(), move.getDestCol())) {
                this.status = Status.COMPLETED;
                System.out.println("Player " + player.getColor() + " has won!");
                break;
            }

            try {
                player.makeMove(board, move);
            } catch(InvalidMoveException e) {
                System.out.println(e.getMessage());
                System.out.println("Please try again!");
                continue;
            }
        }
    }

    private boolean isGameOver() {
        return status != Status.ACTIVE;
    }

    private Move getPlayerMove(Player player) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please source row: ");
        int row = scanner.nextInt();
        System.out.println("Please source col: ");
        int col = scanner.nextInt();
        System.out.println("Please destination row: ");
        int destRow = scanner.nextInt();
        System.out.println("Please destination col: ");
        int destCol = scanner.nextInt();

        Piece piece = board.getPiece(row, col);
        if (piece == null || piece.getColor() != player.getColor()) {
            throw new InvalidMoveException("Invalid move!");
        }

        return new Move(piece, destRow, destCol);
    }
}

package chessgame;

public class Player {
    private Color color;

    public Player(Color color) {
        this.color = color;
    }

    public void makeMove(Board board, Move move) {
        Piece piece = move.getPiece();
        int destCol = move.getDestCol();
        int destRow = move.getDestRow();

        if (board.isValidMove(piece, destRow, destCol)) {
            board.setPiece(piece.getRow(), piece.getCol()null);
            board.setPiece(destRow, destCol, piece);
            piece.setRow(destRow);
            piece.setCol(destCol);
        } else {
            throw new InvalidMoveException("Invalid move...")
        }
    }

}

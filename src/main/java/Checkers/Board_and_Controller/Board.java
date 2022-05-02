package Checkers.Board_and_Controller;

import java.util.Set;

public interface Board {
    int getBoardValue();
    Piece getPiece(int xLocation, int yLocation);
    boolean deletePiece(Piece piece);
    boolean putPiece();
    Piece[][] getBoardCopy();
    void setBoard(Piece[][] setup);
    Set<Piece> getAllPieces();
}
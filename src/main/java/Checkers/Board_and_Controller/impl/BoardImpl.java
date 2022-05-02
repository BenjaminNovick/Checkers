package Checkers.Board_and_Controller.impl;

import Checkers.Board_and_Controller.Board;
import Checkers.Board_and_Controller.Piece;

import java.util.Set;

public class BoardImpl implements Board {
    @Override
    public int getBoardValue() {
        return 0;
    }

    @Override
    public Piece getPiece(int xLocation, int yLocation) {
        return null;
    }

    @Override
    public boolean deletePiece(Piece piece) {
        return false;
    }

    @Override
    public boolean putPiece() {
        return false;
    }

    @Override
    public Piece[][] getBoardCopy() {
        return new Piece[0][0];
    }

    @Override
    public void setBoard(Piece[][] setup) {

    }

    @Override
    public Set<Piece> getAllPieces() {
        return null;
    }
}

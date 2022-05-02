package Checkers.Board_and_Controller.impl;

import Checkers.Board_and_Controller.Piece;
import org.junit.Test;

import static org.junit.Assert.*;

public class PieceImplTest {

    @Test
    public void getLocation() {
        int[] location = {1,1};
        Piece p = new PieceImpl(Piece.Color.RED,location,false);
    }

    @Test
    public void movePiece() {
    }

    @Test
    public void isKing() {
    }



    @Test
    public void isRed() {
    }
}
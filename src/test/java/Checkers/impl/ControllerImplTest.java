package Checkers.impl;

import Checkers.Board_and_Controller.Piece;
import Checkers.Board_and_Controller.impl.BoardImpl;
import Checkers.Board_and_Controller.impl.PieceImpl;
import Checkers.Move;
import org.junit.Test;

import static org.junit.Assert.*;
import src.main.java.Checkers.Controller;

import java.util.List;

public class ControllerImplTest {
    BoardImpl b= new BoardImpl();
    Controller cont= new ControllerImpl(b);
 Piece[][]empty= new PieceImpl[7][7];
    @Test
    public void getAllRedMoves() {
        b.setBoard(empty);   // test when there are no avilible moves
        assertTrue(cont.GetAllRedMoves().isEmpty());
        empty[0][0]=new PieceImpl(Piece.Color.RED,new int[]{0,0},true);// put red piece in spot
        b.setBoard(empty);
        List<Move>t= cont.GetAllRedMoves();  // is list of all legal moves
        assertEquals(t.size(),1);// has one one avalible move
        assertTrue((t.get(0).getStartingLocation()[0]==0)&&(t.get(0).getStartingLocation()[1]==0)&&(t.get(0).getEndingLocation()[0]==1)&&(t.get(0).getEndingLocation()[1]==1));
        // checks if end ad begin locations are correct


        // add piece of other color in on jumpabe spot
        empty[1][1]= new PieceImpl(Piece.Color.BLACK,new int[]{1,1},true);
        t= cont.GetAllRedMoves();
        assertEquals(t.size(),1);
        // end and begining locations are correct for jump
        assertTrue((t.get(0).getStartingLocation()[0]==0)&&(t.get(0).getStartingLocation()[1]==0)&&(t.get(0).getEndingLocation()[0]==2)&&(t.get(0).getEndingLocation()[1]==2));
       // have piece blocking jump so jump is not posible
        empty[1][1]= new PieceImpl(Piece.Color.BLACK,new int[]{2,2},true);
        b.setBoard(empty);
        // jump is not posible there should be know moves available
        assertTrue(cont.GetAllRedMoves().isEmpty());

        // king moveing backwords test
        empty=  new PieceImpl[7][7];
        empty[7][7]=new PieceImpl(Piece.Color.RED,new int[]{7,7},true);
        b.setBoard(empty);
        t= cont.GetAllRedMoves();
        assertEquals(t.size(),1);
        // ending and beging locations are correct
        assertTrue((t.get(0).getStartingLocation()[0]==7)&&(t.get(0).getStartingLocation()[1]==7)&&(t.get(0).getEndingLocation()[0]==6)&&(t.get(0).getEndingLocation()[1]==6));
        // jump test
        empty[6][6]= new PieceImpl(Piece.Color.BLACK,new int[]{6,6},true);
        t= cont.GetAllRedMoves();
        assertEquals(t.size(),1);
        // ending and stating locations for jump are correct
        assertTrue((t.get(0).getStartingLocation()[0]==0)&&(t.get(0).getStartingLocation()[1]==0)&&(t.get(0).getEndingLocation()[0]==5)&&(t.get(0).getEndingLocation()[1]==5));
       // cant jump do to do to peice in way
        empty[1][1]= new PieceImpl(Piece.Color.BLACK,new int[]{5,5},true);
        b.setBoard(empty);
        assertTrue(cont.GetAllRedMoves().isEmpty());

        // test to make shure nonking cant go backwords
        empty=  new PieceImpl[7][7];
        empty[7][7]=new PieceImpl(Piece.Color.BLACK,new int[]{7,7},false);
        empty[7][7]=new PieceImpl(Piece.Color.BLACK,new int[]{5,7},false);
        empty[7][7]=new PieceImpl(Piece.Color.RED,new int[]{6,6},false);
        assertTrue(cont.GetAllRedMoves().isEmpty());
    }

    @Test
    public void getAllBlackMoves() {
        b.setBoard(empty);
        assertTrue(cont.GetAllRedMoves().isEmpty());
    }

    @Test
    public void getMovesOfPiece() {
    }

    @Test
    public void doBlackMove() {
    }

    @Test
    public void doRedMove() {
    }

    @Test
    public void checkRedMove() {
    }

    @Test
    public void checkBlackMove() {
    }
}
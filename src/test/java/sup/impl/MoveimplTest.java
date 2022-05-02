package Checkers.impl;

import Checkers.Move;
import org.junit.Test;

import static org.junit.Assert.*;

public class MoveimplTest {

    @Test
    public void getStartingLocationtest() {
        int [] start ={1,1};
        int[] end = {2,2};
        Move m = new Moveimpl(start,end,42);
        assertTrue(m.getStartingLocation()[0]==start[0]);
        assertTrue(m.getStartingLocation()[1]==start[1]);

    }

    @Test
    public void getEndingLocation() {
        int [] start ={1,1};
        int[] end = {2,2};
        Move m = new Moveimpl(start,end,42);
        assertTrue(m.getEndingLocation()[0]==end[0]);
        assertTrue(m.getEndingLocation()[1]==end[1]);
    }

    @Test
    public void setMoveScore() {
        int [] start ={1,1};
        int[] end = {2,2};
        Move m = new Moveimpl(start,end);
        assertTrue(m.getMoveScore()==0);
        m.setMoveScore(-42.42);
        assertTrue(m.getMoveScore()==-42.42);

    }

    @Test
    public void getMoveScore() {
        int [] start ={1,1};
        int[] end = {2,2};
        Move m = new Moveimpl(start,end);
       assertTrue(m.getMoveScore()==0);

        Move m2 = new Moveimpl(start,end,-1.2);
       assertTrue( m2.getMoveScore()==-1.2);

    }

    @Test
    public void compareTo() {
        int [] start ={1,1};
        int[] end = {2,2};
        Move m = new Moveimpl(start,end);
        Move m2 = new Moveimpl(start,end,-1.2);

        assertTrue(m.compareTo(m2)==1);
        assertTrue(m2.compareTo(m)==-1);
        assertTrue(m2.compareTo(m2)==0);


    }
}
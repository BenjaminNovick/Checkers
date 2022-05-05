package Checkers.impl;

import Checkers.AI;
import Checkers.Board_and_Controller.Board;
import Checkers.Board_and_Controller.impl.BoardImpl;
import Checkers.Controller;
import org.junit.Test;

import static org.junit.Assert.*;

public class AIimplTest {
Board b= new BoardImpl();
Controller c= new ControllerImpl(b);
AI ai= new AIimpl(b,c);
    @Test
    public void getBestRedMove() {
        ai.getBestRedMove();
    }

    @Test
    public void getBestBlackMove() {
        ai.getBestBlackMove();
    }
}
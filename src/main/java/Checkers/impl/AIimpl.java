package Checkers.impl;
import Checkers.Board_and_Controller.Board;
import Checkers.Controller;
import Checkers.Move;
import Checkers.AI;

import java.util.List;

public class AIimpl implements AI {
    Board board;
    Controller controller;
    int level =10;
    public AIimpl(Board board,Controller controller){
        this.board=board;
        this.controller=controller;
    }
    @Override
    public Move getBestRedMove() {
        List<Move> posiblemoves= controller.GetAllRedMoves();
        Move hightsmove =new Moveimpl(new int[]{1, 1}, new int[]{1, 1},-3);
        for(Move m :posiblemoves){
            m.setMoveScore(getMoveRedValue(m,level));
            if(m.getMoveScore() > hightsmove.getMoveScore()){
                hightsmove=m;
            }
        }

        return hightsmove;
    }

    @Override
    public Move getBestBlackMove() {


        List<Move> posiblemoves= controller.GetAllBlackMoves();
        Move hightsmove =new Moveimpl(new int[]{1, 1}, new int[]{1, 1},3);
        for(Move m :posiblemoves){
            m.setMoveScore(getMoveBlackValue(m,level));
            if(m.getMoveScore() < hightsmove.getMoveScore()){
                hightsmove=m;
            }
        }

        return hightsmove;
    }



    private double getMoveRedValue(Move m,int lev) {   // red move is eather defined by the bourd value or bord vale after black does the best move for black
        if (lev == 0) {
            return board.getBoardValue();
        }
        Move hightsmove = new Moveimpl(new int[]{1, 1}, new int[]{1, 1}, 3);
        controller.DoRedMove(m);
        List<Move> posiblemoves = controller.GetAllBlackMoves();
        if (posiblemoves.isEmpty()) {
            return -1;
        }
        for (Move mo : posiblemoves) {
            mo.setMoveScore(getMoveBlackValue(mo, level-1));
            if (mo.getMoveScore() < hightsmove.getMoveScore()) {
                hightsmove = mo;
            }


        }
        controller.Undo();
        return hightsmove.getMoveScore();
    }
    private double getMoveBlackValue(Move m,int lev){
        if (lev == 0) { // were not loking any furthere
            return board.getBoardValue();
        }
        Move hightsmove = new Moveimpl(new int[]{1, 1}, new int[]{1, 1}, -3);
        controller.DoBlackMove(m);
        List<Move> posiblemoves = controller.GetAllRedMoves();
        if (posiblemoves.isEmpty()) {// there are no moves
            return 1;
        }
        for (Move mo : posiblemoves) {
            mo.setMoveScore(getMoveBlackValue(mo, level-1));
            if (mo.getMoveScore() > hightsmove.getMoveScore()) {
                hightsmove = mo;
            }
        }
        controller.Undo();
        return hightsmove.getMoveScore();
    }
}


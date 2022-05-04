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
        Move hightsmove =new Moveimpl(new int[]{1, 1}, new int[]{1, 1},-3.0);
        for(Move m :posiblemoves){
            m.setMoveScore(getMoveRedValue(m,level,hightsmove.getMoveScore()));
            if(m.getMoveScore() > hightsmove.getMoveScore()){
                hightsmove=m;
            }
        }

        return hightsmove;
    }

    @Override
    public Move getBestBlackMove() {


        List<Move> posiblemoves= controller.GetAllBlackMoves();
        Move lowsmove =new Moveimpl(new int[]{1, 1}, new int[]{1, 1},3);
        for(Move m :posiblemoves){
            m.setMoveScore(getMoveBlackValue(m,level,lowsmove.getMoveScore()));
            if(m.getMoveScore() < lowsmove.getMoveScore()){
                lowsmove=m;
            }
        }

        return lowsmove;
    }



    private double getMoveRedValue(Move m,int lev,double lastHighst) {   // red move is eather defined by the bourd value or bord vale after black does the best move for black
        if (lev == 0) {
            return board.getBoardValue();
        }
        Move lowsmove = new Moveimpl(new int[]{1, 1}, new int[]{1, 1}, 3);
        controller.DoRedMove(m);
        List<Move> posiblemoves = controller.GetAllBlackMoves();
        if (posiblemoves.isEmpty()) {
            return -1;
        }
        for (Move mo : posiblemoves) {
            mo.setMoveScore(getMoveBlackValue(mo, level-1,lowsmove.getMoveScore()));
            if (mo.getMoveScore() < lowsmove.getMoveScore()) {
                lowsmove = mo;
            }
            if(lowsmove.getMoveScore()<lastHighst){
                break;
            }

        }
        controller.Undo();
        return lowsmove.getMoveScore();
    }
    private double getMoveBlackValue(Move m,int lev,double lastlowest){
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
            mo.setMoveScore(getMoveRedValue(mo, level-1,hightsmove.getMoveScore()));
            if (mo.getMoveScore() > hightsmove.getMoveScore()) {
                hightsmove = mo;
            }
            if(hightsmove.getMoveScore()>lastlowest){
                break;
            }
        }
        controller.Undo();
        return hightsmove.getMoveScore();
    }
}


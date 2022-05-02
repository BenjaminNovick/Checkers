package Checkers.impl;
import Checkers.Board_and_Controller.Board;
import Checkers.Board_and_Controller.Piece;
import Checkers.Controller;
import Checkers.Move;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class ControllerImpl implements Controller {
Stack<Piece[][]> PastBordStates= new Stack<Piece[][]>();
Board Board;
public  ControllerImpl(Board bord){
    this.Board=bord;
}
    @Override
    public List<Move> GetAllRedMoves() {
    List<Move> out=new LinkedList<Move>();
        for (Piece p :Board.getAllPieces()){
            if(p.isRed()) {
                out.addAll(GetMovesOfPiece(p.getLocation()));
            }
        }
        return out;
    }

    @Override
    public List<Move> GetAllBlackMoves() {
        return null;
    }

    @Override
    public List<Move> GetMovesOfPiece(int[] location) {
        return null;
    }

    @Override
    public boolean DoBlackMove(Move m) {
        return false;
    }

    @Override
    public boolean DoRedMove(Move m) {
        return false;
    }

    @Override
    public boolean CheckRedMove(Move m) {
        return false;
    }

    @Override
    public boolean CheckBlackMove(Move m) {
        return false;
    }

    @Override
    public boolean Undo() {
    Board.setBoard(PastBordStates.pop());
        return false;
    }
    private boolean putBoardInStack(){
        PastBordStates.push(Board.getBoardCopy());
        return true;
    }
}

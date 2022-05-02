package Checkers.impl;
import Checkers.Board_and_Controller.Board;
import Checkers.Board_and_Controller.Piece;
import Checkers.Controller;
import Checkers.Move;

import java.util.*;

public class ControllerImpl implements Controller {
Stack<Piece[][]> PastBordStates= new Stack<Piece[][]>();
Board Board;
public  ControllerImpl(Board bord){
    this.Board=bord;
}
    @Override
    public List<Move> GetAllRedMoves() {
    List<Move> out=new ArrayList<>();
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
        List<Move> moves = new ArrayList<>();
        moves.addAll(getNormalMoves(location);
        if(Board.getPiece(location).isKing())){
            moves.addAll(getKingJumps(location));
        }
        else if(Board.getPiece(location).isRed()){
            moves.addAll(getRedJumps(location, new ArrayList<Move>()));
        }
        else {
            moves.addAll(getBlackJumps(location));
        }
    }

    private List<Move> getRedJumps(int[] location, ArrayList<Move> jumps) {
        if(inBounds(upLeft(location)) && !Board.getPiece(upLeft(location)).isRed() && inBounds(upLeft(upLeft(location))) && Board.getPiece(upLeft(upLeft(location))) == null){
            getRedJumps(upLeft(upLeft(location)), jumps);
        }
        if(inBounds(upRight(location)) && !Board.getPiece(upRight(location)).isRed() && inBounds(upRight(upRight(location))) && Board.getPiece(upRight(upRight(location))) == null){
            getRedJumps(upRight(upRight(location)), jumps);
        }
        return jumps;
    }

    private int[] upRight(int[] location){
       int[] array = new int[2];
       array[0] = location[0] + 1;
       array[1] = location[1] + 1;
       return array;
    }
    private int[] upLeft(int[] location){
        int[] array = new int[2];
        array[0] = location[0] - 1;
        array[1] = location[1] + 1;
        return array;
    }
    private int[] downRight(int[] location){
        int[] array = new int[2];
        array[0] = location[0] + 1;
        array[1] = location[1] - 1;
        return array;
    }
    private int[] downLeft(int[] location){
        int[] array = new int[2];
        array[0] = location[0] - 1;
        array[1] = location[1] - 1;
        return array;
    }

    private boolean inBounds(int[] location){
        if(location[0] < 0 || location[0] > 7 || location[1] < 0 || location[1] > 7){
            return false;
        }
        return true;
    }

    private List<Move> getNormalMoves(int[] location) {
    }

    private List<Move> get

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

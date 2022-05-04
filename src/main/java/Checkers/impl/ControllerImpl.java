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
        moves.addAll(getNormalMoves(location));
        if(Board.getPiece(location).isKing()){
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

    private boolean canUpRightJump(int[] location, Piece piece){
        if(inBounds(upRight(location)) && Board.getPiece(upRight(location)).isRed() != piece.isRed() && inBounds(upRight(upRight(location))) && Board.getPiece(upRight(upRight(location))) == null){
            return true;
        }
        return false;
    }
    private boolean canUpLeftJump(int[] location, Piece piece){
        if(inBounds(upLeft(location)) && Board.getPiece(upLeft(location)).isRed() != piece.isRed() && inBounds(upLeft(upLeft(location))) && Board.getPiece(upLeft(upLeft(location))) == null){
            return true;
        }
        return false;
    }

    private boolean canDownLeftJump(int[] location, Piece piece){
        if(inBounds(downLeft(location)) && Board.getPiece(downLeft(location)).isRed() != piece.isRed() && inBounds(downLeft(downLeft(location))) && Board.getPiece(downLeft(downLeft(location))) == null){
            return true;
        }
        return false;
    }

    private boolean canDownRightJump(int[] location, Piece piece){
        if(inBounds(downRight(location)) && Board.getPiece(downRight(location)).isRed() != piece.isRed() && inBounds(downRight(downRight(location))) && Board.getPiece(downRight(downRight(location))) == null){
            return true;
        }
        return false;
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
    Piece piece= Board.getPiece(location);
    List<Move> out = new ArrayList<Move>();
    if(piece.isKing()){
        out.addAll(getRedMove(piece));
        out.addAll(getBlackMove(piece));
        return out;
    }
    if(piece.isRed()){
        out.addAll(getRedMove(piece));
    }else {
        out.addAll(getBlackMove(piece));
    }
return out;

    }

    private List<Move> getRedMove(Piece p){
    List<Move> list = new ArrayList<Move>();
        if((inBounds(upLeft(p.getLocation())))&&((Board.getPiece(upLeft(p.getLocation())))==null)){
            list.add(new Moveimpl(p.getLocation(),upLeft(p.getLocation())));
        }
        if((inBounds(upRight(p.getLocation())))&&((Board.getPiece(upRight(p.getLocation())))==null)){
            list.add(new Moveimpl(p.getLocation(),upRight(p.getLocation())));
        }
        return  list;

    }
    private List<Move> getBlackMove(Piece p){
        List<Move> list = new ArrayList<Move>();
        if((inBounds(downLeft(p.getLocation())))&&((Board.getPiece(downLeft(p.getLocation())))==null)){
            list.add(new Moveimpl(p.getLocation(),upLeft(p.getLocation())));
        }
        if((inBounds(downRight(p.getLocation())))&&((Board.getPiece(downRight(p.getLocation())))==null)){
            list.add(new Moveimpl(p.getLocation(),upRight(p.getLocation())));
        }
        return  list;

    }

    @Override
    public boolean DoBlackMove(Move m) {
        return false;
    }

    @Override
    public boolean DoRedMove(Move m) {
        Move mtodo=null;
        if (CheckRedMove(m)) {
            List<Move> realmoves = GetMovesOfPiece(m.getStartingLocation());
            int numberofj = 0;
            for (Move seemove : realmoves) {
                if ((seemove.getEndingLocation()[0] == m.getEndingLocation()[0]) && (m.getEndingLocation()[1] == seemove.getEndingLocation()[1])) {
                    if (seemove.getJumpLocations().size() >= numberofj) {// because the moves created from the ui do not contain thespots to jump it will find the move with the same and spot but the must jumps
                        mtodo = seemove;
                        numberofj = seemove.getJumpLocations().size();
                    }
                }
            }
            if (!(mtodo == null)) {
                Piece piece = Board.getPiece(mtodo.getStartingLocation());
                Board.deletePiece(piece);
                Board.putPiece(piece, mtodo.getEndingLocation());
                for (int[] js : m.getJumpLocations()) {
                    Board.deletePiece(Board.getPiece(js));
                }
                return true;
            }
        }
        return false;

    }

    @Override
    public boolean CheckRedMove(Move m) {
    Move mtodo=null;
    List<Move> realmoves;
    int numberofj=0;
    if(inBounds(m.getStartingLocation())) {
        if (!(Board.getPiece(m.getStartingLocation()) == null)) {

            if ((Board.getPiece(m.getStartingLocation()).isRed()) && (inBounds(m.getEndingLocation()))) {
                realmoves = GetMovesOfPiece(m.getStartingLocation());
                for (Move seemove : realmoves) {
                    if ((seemove.getEndingLocation()[0] == m.getEndingLocation()[0]) && (m.getEndingLocation()[1] == seemove.getEndingLocation()[1])) {
                        return true;
                    }
                }

            }
        }
    }
        return false;
    }

    @Override
    public boolean CheckBlackMove(Move m) {
        Move mtodo=null;
        List<Move> realmoves;
        int numberofj=0;
        if(inBounds(m.getStartingLocation())) { // if in bounds;
            if (!(Board.getPiece(m.getStartingLocation()) == null)) {// if there actuly is a pice there
                if ((!Board.getPiece(m.getStartingLocation()).isRed()) && (inBounds(m.getEndingLocation()))) {
                    realmoves = GetMovesOfPiece(m.getStartingLocation());
                    for (Move seemove : realmoves) {
                        if ((seemove.getEndingLocation()[0] == m.getEndingLocation()[0]) && (m.getEndingLocation()[1] == seemove.getEndingLocation()[1])) {
                            return true;
                        }
                    }

                }
            }
        }
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

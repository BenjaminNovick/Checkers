package Checkers;

import Checkers.Board_and_Controller.Piece;

public interface Move extends Comparable<Move> {
    int[] getStartingLocation();
    int[] getEndingLocation();
    //Piece getPiece(); i dont thing this is good for this case it will be contraler that will find the piece based on the info in this class;
    double getMoveScore();
    void setMoveScore(double score);  // i just added this some times we will need to change or put a score in later
}

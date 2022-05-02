package Checkers.impl;
import Checkers.Board_and_Controller.Piece;
import Checkers.Move;

import java.util.ArrayList;

public class Moveimpl implements Move  {
    private int[]StartLocation;
    private int[]EndLocation;
    private double score;
    private ArrayList<int[]> jumps;
    public Moveimpl(int[] StartLocation,int[] EndLocation){// format array[0]=x array[1]= y 
        score=0;
        for (int i=0; i<2;i++){
            this.StartLocation[i] =StartLocation[i];
            this.EndLocation[i]=EndLocation[i];
        }
    }
    public Moveimpl(int[] StartLocation,int[] EndLocation, ArrayList<int[]> jumps){// format array[0]=x array[1]= y
        score=0;
        for (int i=0; i<2;i++){
            this.StartLocation[i] =StartLocation[i];
            this.EndLocation[i]=EndLocation[i];
        }
        this.jumps = jumps;
    }
    public Moveimpl(int[] StartLocation,int[] EndLocation,double Score){
        this.score=Score;
        for (int i=0; i<2;i++){
            this.StartLocation[i] =StartLocation[i];
            this.EndLocation[i]=EndLocation[i];
        }
    }
    @Override
    public int[] getStartingLocation() {
        int[]temp=new int[2];
        for (int i=0; i<2;i++){
           temp[i]= this.StartLocation[i] ;
        }
        return temp;

    }

    @Override
    public int[] getEndingLocation() {
        int[]temp=new int[2];
        for (int i=0; i<2;i++){
            temp[i]= this.EndLocation[i] ;
        }
        return temp;
    }

    @Override
    public ArrayList<int[]> getJumpLocations() {
        return this.jumps;
    }

    @Override
    public void setMoveScore(double score){
        this.score = score;
    }

    @Override
    public double getMoveScore() {
        double temp = score;
        return temp;
    }

    @Override
    public int compareTo(Move o) {
        if (o.getMoveScore() > this.score) {
            return -1;
        }
        if (o.getMoveScore() < this.score) {
            return 1;

        } else {
            return 0;

        }
    }
}

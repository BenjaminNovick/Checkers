package Checkers.impl;


import Checkers.Board_and_Controller.Piece;
import Checkers.Move;
import Checkers.UI;
import java.util.Scanner;

public class UIImpl implements UI {
    Scanner scanner = new Scanner(System.in);


    @Override
    public void printBord(Piece[][] boardArray) {
        for(int j = 0; j < 8; j++){
            for(int i = 0; i < 8; i++){
                if(boardArray[i][j] == null){
                    System.out.print("[]");
                }
                else if(boardArray[i][j].isRed()){
                    if(boardArray[i][j].isKing()){
                        System.out.print("[R]");
                    }
                    else {
                        System.out.print("[r]");
                    }
                }
                else{
                    if(boardArray[i][j].isKing()){
                        System.out.print("[B]");
                    }
                    else {
                        System.out.print("[b]");
                    }
                }
                System.out.print("\t");
            }
            System.out.print("\n");
        }
    }

    @Override
    public void printWinner(Piece.Color C) {
        if(C.equals(Piece.Color.RED)){
            System.out.println("RED WINS!!!!!!!!!");
        }
        else {
            System.out.println("BLACK WINS!!!!!!!!!");
        }
    }

    @Override
    public Move askPlayerForMove() {
        System.out.println("Please input the next move");
        String input = scanner.nextLine();
        if(input.length() != 8){
            insultPlayer();
            return askPlayerForMove();
        }
        int startX = input.charAt(0) - 65;
        int startY = input.charAt(1) - 1;
        int endX = input.charAt(6) - 65;
        int endY = input.charAt(7) - 1;
        Move move = new Moveimpl(new int[]{startX,startY},new int[]{endX,endY});
        return move;
    }

    @Override
    public void TellPlayThatMoveIsIllegal() {
        System.out.println("Illegal move");
    }

    @Override
    public void insultPlayer() {
        System.out.println("That's not right you fool");
    }

    @Override
    public int numberOfPlayers() {
        return 0;
    }

    @Override
    public boolean doYouWantAnotherGame() {
        return false;
    }
}

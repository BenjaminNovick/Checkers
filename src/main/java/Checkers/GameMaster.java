package Checkers;
import Checkers.Board_and_Controller.Board;
import Checkers.Board_and_Controller.Piece;
import Checkers.Board_and_Controller.impl.BoardImpl;
import Checkers.impl.AIimpl;
import Checkers.impl.ControllerImpl;
import Checkers.impl.UIImpl;
import Checkers.UI;
import Checkers.AI;
import java.util.Random;
public class GameMaster {
    UI ui = new UIImpl();
    Random random= new Random();
    public void main(String[] args){
        runGame();
    }
    private void runGame(){
        int players;
        boolean letsplay=true;
       while (letsplay==true) {
           players = ui.numberOfPlayers();
           if (players == 0) {
               RunNoPlayer();
           }
           if (players == 1) {
               RunPlayerAndAI();
           }
           if (players == 2) {
               RunTwoPlayers();
           }
           if (ui.doYouWantAnotherGame()==true){
               letsplay=true;
           }else {
               letsplay=false;
           }
       }
    }
    private void RunTwoPlayers(){
        Board board= new BoardImpl();
        Controller controller=new ControllerImpl(board);
        Move playesmove;
        Piece.Color turn = Piece.Color.RED;
        for (int t=0; t<60 ;t++) {
            ui.printBord(board.getBoardCopy());
            if (board.getBoardValue() == 1) {
                ui.printWinner(Piece.Color.RED);
                return;
            }
            if (board.getBoardValue() == -1) {
                ui.printWinner(Piece.Color.BLACK);
                return;
            }


            if (turn == Piece.Color.RED) {
                if(random.nextBoolean()){
                    ui.insultPlayer();
                }
                playesmove = ui.askPlayerForMove();
                if (controller.CheckRedMove(playesmove)) {
                    controller.DoRedMove(playesmove);
                    turn = Piece.Color.BLACK;
                } else {
                    ui.TellPlayThatMoveIsIllegal();
                }
            } else {
                if(random.nextBoolean()){
                    ui.insultPlayer();
                }
                playesmove = ui.askPlayerForMove();
                if (controller.CheckBlackMove(playesmove)) {
                    controller.DoBlackMove(playesmove);
                    turn = Piece.Color.BLACK;
                } else {
                    ui.TellPlayThatMoveIsIllegal();
                }
            }
        }


    }
    private void RunPlayerAndAI(){
        Board board= new BoardImpl();
        Controller controller=new ControllerImpl(board);
        Piece.Color turn = Piece.Color.RED;
        AI ai= new AIimpl(board,controller);
        Move playesmove;
        for (int t=0; t<60 ;t++){
            ui.printBord(board.getBoardCopy());
            if(board.getBoardValue()==1){
                ui.printWinner(Piece.Color.RED);
                return;
            }
            if (board.getBoardValue()==-1){
                ui.printWinner(Piece.Color.BLACK);
                return;
            }


            if(turn== Piece.Color.RED){
               playesmove= ui.askPlayerForMove();
               if (controller.CheckRedMove(playesmove)){
                   controller.DoRedMove(playesmove);
                   turn= Piece.Color.BLACK;
               }else {
                 ui.TellPlayThatMoveIsIllegal();
               }
            }else {
               if(random.nextBoolean()){
                   ui.insultPlayer();
               }
               controller.DoBlackMove(ai.getBestBlackMove());
               turn= Piece.Color.RED;
            }
        }
    }


    private void  RunNoPlayer(){
        int turn =0;
        Board board= new BoardImpl();
        Controller controller=new ControllerImpl(board);
        AI ai= new AIimpl(board,controller);
        while (turn<500){
            ui.printBord(board.getBoardCopy());
            controller.DoRedMove(ai.getBestRedMove());
            controller.DoBlackMove(ai.getBestBlackMove());
            if (board.getBoardValue()==-1){
                ui.printWinner(Piece.Color.BLACK);
                break;
            }
            if (board.getBoardValue()==-1){
                ui.printWinner(Piece.Color.RED);
                break;
            }
        }
    }




}
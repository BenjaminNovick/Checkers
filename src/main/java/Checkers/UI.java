package src.main.java.Checkers;
import Checkers.Board_and_Controller.Piece;
import Checkers.Move;
public interface UI {
    void printBord(Piece[][] bordarray);
    void printWinner(Color C);
    Move askPlayerForMove();
    void TellPlayThatMoveIsIllegal();
    void insultPlayer();
    int numberOfPlayers();
    boolean doYouWantAntherGame();

}

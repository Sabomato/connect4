package game.logic.data.Player;

import game.logic.data.Grid;
import static game.logic.data.Grid.*;
import game.logic.data.minigame.MinigameAns;
import game.logic.data.minigame.Minigames;

import java.io.Serializable;

public class Player implements Serializable,IPlayer {

    private static final long serialVersionUID =1L;

    private String name;
    protected Character piece;
    private static int idPlayer = 0;



    public Player() {

        if(idPlayer++ % 2 == 0)
            this.piece = BLUE_PIECE;
        else
            this.piece = RED_PIECE;

    }
    @Override
    public boolean isHuman(){

        return false;
    }

    public int getnPlays() {
        return 0;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean PlaySpecialPiece(Grid g, int nColumn){

        return false;
    }

    @Override
    public int getUndoCredits() {
        return -1;
    }

    @Override
    public int getNSpecialPieces() {
        return -1;
    }

    public int PlayNormalPiece(Grid g, int nColumn){

        return -1;
    }
    public IPlayer tryUndo(int nCredits){
        return null;
    }


    public IPlayer getPlayerInfo() {
        return this;
    }


    public boolean isMinigameTurn(){
        return false;
    }

    public void incNplays(){

    }
    public MinigameAns InsertAnswerMinigame(Minigames minigames, String answer, int turn){
        return null;
    }

    @Override
    public boolean undo(int nCredits, int nSpecialPieces) {
        return false;
    }

    public String getName() {
        return name;
    }

    @Override
    public char getPiece() {
        return piece;
    }

    public void IncreaseSpecialPieces() {

    }
    public boolean DecreaseSpecialPieces() {

        return false;
    }

    @Override
    public String toString() {
        return String.format("Player %s piece:%c ",getName(),piece);
    }
}

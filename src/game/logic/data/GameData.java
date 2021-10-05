package game.logic.data;

import game.logic.data.Player.HumanPlayer;
import game.logic.data.Player.IPlayer;
import game.logic.data.Player.Player;
import game.logic.data.Player.VirtualPlayer;
import game.logic.data.minigame.MinigameAns;
import game.logic.data.minigame.Minigames;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Vector;


public class GameData implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final int HUMANS = 0, HUMAN_VIRTUAL = 1, VIRTUALS = 2;

    private Minigames minigames;
    private Grid grid;
    private Player p1,p2,current;
    private int turn;
    private boolean won;

    public GameData() {
        turn = 1;
        grid = new Grid();
        minigames = new Minigames();

    }

    public boolean initPlayers(int codePlayers){

        switch (codePlayers) {
            case HUMANS -> {
                p1 = new HumanPlayer();
                p2 = new HumanPlayer();
            }
            case HUMAN_VIRTUAL -> {
                p1 = new HumanPlayer();
                p2 = new VirtualPlayer();
            }
            case VIRTUALS -> {
                p1 = new VirtualPlayer();
                p2 = new VirtualPlayer();
            }
            default -> {
                return false;
            }
        }

        rafflePlayers();

        return true;

    }

    private void rafflePlayers(){

        if(Math.random()/Math.nextDown(1.0) < 0.50)
            current = p1;
        else
            current = p2;

    }

    public boolean setPlayersName(String nameP1,String nameP2){

        if(nameP1 == null || nameP2 == null)
            return false;
        if(nameP1.equals(nameP2))
            return false;

        p1.setName(nameP1);
        p2.setName(nameP2);

        return true;
    }
    public boolean  checkValidPlayersName(String nameP1,String nameP2){
        if(nameP1 == null || nameP2 == null)
            return false;
        if(nameP1.equals(nameP2))
            return false;
        return true;
    }


    public boolean isValidNormalPlay(int nColumn) {

        return grid.isValidNormalPlay(nColumn);
    }

    public boolean isValidSpecialPlay(int nColumn){

        return current.getNSpecialPieces() > 0 && grid.isValidSpecialPlay(nColumn);
    }

    public int PlayerNormalPiece(int nColumn){

        minigames.ClearLog();
        int ret = current.PlayNormalPiece(grid,nColumn);

        if(ret == Grid.WIN)
            won = true;


        return ret;

    }

    public boolean PlaySpecialPiece(int nColumn){

        minigames.ClearLog();
        if(current.PlaySpecialPiece(grid,nColumn)){
            EndTurn();
            return  true;
        }
            return false;

    }

    public String GetMinigameQuestion() {

        return minigames.GetQuestion();

    }

    public MinigameAns InsertAnswerMinigame(String answer){

        MinigameAns ret = current.InsertAnswerMinigame(minigames,answer,turn);
        if( ret == MinigameAns.TIME_OVER || ret == MinigameAns.GAVE_UP){
            EndTurn();
        }
        return ret;
    }

    public void incNPlays(){
        current.incNplays();
    }

    public void EndTurn(){

        if(current == p1)
            current = p2;
        else
            current = p1;

        ++turn;
        current.incNplays();
    }

    public boolean isMinigameTurn(){

        return current.isMinigameTurn();

    }

    public String getP1Name(){

        return p1.getName();
    }

    public String getP2Name(){

        return p2.getName();
    }

    public int getTurn(){

        return turn;
    }

    public String getMinigameInfo(){

        return minigames.getMinigameInfo();
    }

    public MinigameAns getMinigameAnswer(){

        return minigames.getAnswer();
    }

    public boolean undoByName(String name,int nCredits,int nSpecialPieces){

        if(p1.getName().equals(name))
           return p1.undo(nCredits,nSpecialPieces);

        return p2.undo(nCredits,nSpecialPieces);
    }

    public IPlayer tryUndo(int nCredits){

        return current.tryUndo(nCredits);
    }
    public IPlayer getCurrentPlayerInfo(){
        if(current != null)
            return current.getPlayerInfo();
        return null;

    }

    public String printSnapshot(){
        String ret = minigames.getLog();

        return ret != null ? ret:"" + this.toString();
    }
    public ArrayList<Vector<Character>> getWholeGridCopy(){

        return grid.getWholeGridCopy();
    }

    public boolean isCurrentPlayerHuman(){

        return current instanceof HumanPlayer;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        if(!won)
            sb.append(grid.toString()).append(String.format("Turn:%d\n %s",turn,current));
        else
            sb.append(grid.toString()).append(String.format("Turn:%d\n %s \n %s",turn,p1,p2));

        return sb.toString();
    }
}

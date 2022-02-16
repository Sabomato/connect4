package game.logic;

import game.logic.data.Player.HumanPlayer;
import game.logic.data.Player.IPlayer;
import game.logic.data.minigame.MinigameAns;
import game.logic.states.Situations;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;


public class FourInLineManager {

    private FourInLineOriginator originator;
    private FourInLineCareTaker careTaker;

    public FourInLineManager() {
        this.originator = new FourInLineOriginator();
        this.careTaker = new FourInLineCareTaker(originator);

    }

    public void Next(){

        //careTaker.saveMemento();
        originator.Next();

    }

    public boolean isValidNormalPlay(int nColumn){

        return originator.isValidNormalPlay(nColumn);
    }

    public boolean isValidSpecialPlay(int nColumn){
        return originator.isValidSpecialPlay(nColumn);
    }

    public void PlayNormalPiece(int column){

        careTaker.saveMemento();
        originator.PlayNormalPiece(column);


    }

    public void PlaySpecialPiece(int column){

        careTaker.saveMemento();
        originator.PlaySpecialPiece(column);

    }

    public void SetPlayersName(String P1Name, String P2Name){

        originator.SetPlayersName(P1Name,P2Name);
            //careTaker.saveMemento();
    }
    public boolean  checkValidPlayersName(String nameP1,String nameP2){
        return originator.checkValidPlayersName(nameP1,nameP2);
    }
    public boolean isMinigameTurn(){
        return originator.isMinigameTurn();
    }

    public void GameOver() {
        careTaker.saveMemento();
        SaveReplay();
        //originator.GameOver();
        InitiateOrigCare();
    }

    public void SetHumanORVirtual(int playerType){

        originator.SetHumanORVirtual(playerType);
    }

    public void insertMinigameAnswer(String answer){

        originator.insertMinigameAnswer(answer);

    }

    public String getMinigameQuestion() {

        return originator.getMinigameQuestion();
    }

    public String getMinigameInfo(){


        return originator.getMinigameInfo();
    }

    public void isGoingToPlayMinigame(boolean answer){

        //careTaker.saveMemento();
        originator.isGoingToPlayMinigame(answer);

    }

    public MinigameAns getMinigameAnswer(){
        //careTaker.saveMemento();
       return originator.getMinigameAnswer();

    }

    public Situations getCurrentSituation(){
       // careTaker.saveMemento();
        return originator.getCurrentSituation();
    }

    public boolean undo(int nCredits){

        IPlayer p = originator.tryUndo(nCredits);
        if(p == null)
            return false;
        if(!careTaker.undo(nCredits))
            return false;

        int nCredits2 = (HumanPlayer.MAX_UNDO_CREDITS-p.getUndoCredits())+nCredits;

        return originator.undoByName(p.getName(), nCredits2,p.getNSpecialPieces());

    }
    public ArrayList<Vector<Character>> getWholeGridCopy(){

        return originator.getWholeGridCopy();
    }
    public String printReplay(){

        if(!careTaker.replay()){
            InitiateOrigCare();
            return null;
        }
        return originator.getSnapshot();
    }

    public boolean playReplay(){
        if(!careTaker.replay()){
            InitiateOrigCare();
            return false;
        }
        return true;
    }

    public void SaveReplay(){

        Replay.SaveReplay(careTaker);

    }

    public boolean LoadReplay(File f){

        Object obj = Replay.LoadReplay(f);

        if(obj == null)
            return false;

        careTaker = (FourInLineCareTaker) obj;

        careTaker.setOriginator(originator);
        return true;
    }

    public boolean LoadReplay(int nReplay){

        Object obj = Replay.LoadReplay(nReplay);

        if(obj == null)
            return false;

        careTaker = (FourInLineCareTaker) obj;

        careTaker.setOriginator(originator);
        return true;
    }

    public void playReplays(){

        originator.playReplays();
    }
    public IPlayer getCurrentPlayerInfo(){
        return originator.getCurrentPlayerInfo();

    }
    public int getTurn(){

        return originator.getTurn();
    }

    private void InitiateOrigCare(){
        this.originator = new FourInLineOriginator();
        this.careTaker = new FourInLineCareTaker(originator);
    }

    public boolean SaveGame(String nameFile){

        careTaker.saveMemento();
        return Save.SaveFile(nameFile,careTaker);

    }

    public boolean SaveGame(File f){

        careTaker.saveMemento();
        return Save.SaveFile(f,careTaker);

    }

    public boolean Loadgame(String nameFile){

        Object obj = Save.LoadFile(nameFile);

        if(obj == null)
            return false;

        careTaker = (FourInLineCareTaker) obj;
        careTaker.setOriginator(originator);
        careTaker.undo(1);

        return true;
    }

    public boolean Loadgame(File f){

        Object obj = Save.LoadFile(f);

        if(obj == null)
            return false;

        careTaker = (FourInLineCareTaker) obj;
        careTaker.setOriginator(originator);
        careTaker.undo(1);

        return true;
    }

    @Override
    public String toString() {
        return originator.toString();
    }
}

package game.logic;


import game.logic.data.Player.IPlayer;
import game.logic.data.minigame.MinigameAns;
import game.logic.states.Situations;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.util.ArrayList;
import java.util.Vector;

public class FourInLineObservable {

    public static final String UPDATED_BOARD_PROPERTY = "Board Update";
    public static final String PLAYER_CHANGED_PROPERTY = "Player Changed";
    public static final String SITUATION_CHANGED_PROPERTY = "Situation Changed";
    public static final String MINIGAME_PLAY_PROPERTY = "New Minigame Answer";
    public static final String MINIGAME_QUESTION_PROPERTY = "New Minigame Question";


    private final PropertyChangeSupport propertyChangeSupport;
    private final FourInLineManager fourInLineManager;

    public FourInLineObservable() {

        this.fourInLineManager = new FourInLineManager();
        this.propertyChangeSupport = new PropertyChangeSupport(fourInLineManager);
    }

    public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {

        propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
    }

    public void removeListener(String propertyName,PropertyChangeListener listener) {

        propertyChangeSupport.removePropertyChangeListener(propertyName,listener);
    }

    public void Next(){

        //careTaker.saveMemento();
        fourInLineManager.Next();
        propertyChangeSupport.firePropertyChange(SITUATION_CHANGED_PROPERTY,null,null);
    }

    public boolean isValidNormalPlay(int nColumn){

        return fourInLineManager.isValidNormalPlay(nColumn);
    }

    public boolean isValidSpecialPlay(int nColumn){

        return fourInLineManager.isValidSpecialPlay(nColumn);
    }

    public void PlayNormalPiece(int column){

        fourInLineManager.PlayNormalPiece(column);
        propertyChangeSupport.firePropertyChange(UPDATED_BOARD_PROPERTY,null,null);
        propertyChangeSupport.firePropertyChange(SITUATION_CHANGED_PROPERTY,null,null);
        propertyChangeSupport.firePropertyChange(PLAYER_CHANGED_PROPERTY,null,null);

    }

    public void PlaySpecialPiece(int column){

        fourInLineManager.PlaySpecialPiece(column);

        propertyChangeSupport.firePropertyChange(UPDATED_BOARD_PROPERTY,null,null);
        propertyChangeSupport.firePropertyChange(PLAYER_CHANGED_PROPERTY,null,null);

    }

    public void SetPlayersName(String P1Name, String P2Name){

        fourInLineManager.SetPlayersName(P1Name,P2Name);

        propertyChangeSupport.firePropertyChange(SITUATION_CHANGED_PROPERTY,null,null);
        propertyChangeSupport.firePropertyChange(PLAYER_CHANGED_PROPERTY,null,null);

        //careTaker.saveMemento();
    }
    public boolean  checkValidPlayersName(String nameP1,String nameP2){

        return fourInLineManager.checkValidPlayersName(nameP1,nameP2);
    }


    public boolean isMinigameTurn(){
        return fourInLineManager.isMinigameTurn();
    }

    public void GameOver() {
       fourInLineManager.GameOver();
        fireAlmostAll();

    }
    public int getTurn(){

        return fourInLineManager.getTurn();
    }

    public void SetHumanORVirtual(int playerType){

        fourInLineManager.SetHumanORVirtual(playerType);
        propertyChangeSupport.firePropertyChange(SITUATION_CHANGED_PROPERTY,null,null);
    }

    public void insertMinigameAnswer(String answer){

        fourInLineManager.insertMinigameAnswer(answer);

        propertyChangeSupport.firePropertyChange(SITUATION_CHANGED_PROPERTY,null,null);
        propertyChangeSupport.firePropertyChange(MINIGAME_QUESTION_PROPERTY,null,null);

    }

    public String getMinigameQuestion() {

        String Question = fourInLineManager.getMinigameQuestion();
        //propertyChangeSupport.firePropertyChange(MINIGAME_ANSWER_PROPERTY,null,null);

        return Question;
    }

    public String getMinigameInfo(){

        return fourInLineManager.getMinigameInfo();
    }

    public void isGoingToPlayMinigame(boolean answer){


        fourInLineManager.isGoingToPlayMinigame(answer);
        if (!answer)
            propertyChangeSupport.firePropertyChange(SITUATION_CHANGED_PROPERTY, null, null);


    }

    public MinigameAns getMinigameAnswer(){

        return fourInLineManager.getMinigameAnswer();

    }
    public ArrayList<Vector<Character>> getWholeGridCopy(){

        return fourInLineManager.getWholeGridCopy();
    }
    public Situations getCurrentSituation(){

        return fourInLineManager.getCurrentSituation();
    }

    public boolean undo(int nCredits){

        if(!fourInLineManager.undo(nCredits))
            return false;

        fireAlmostAll();

        return true;

    }

    public IPlayer getCurrentPlayerInfo(){
        return fourInLineManager.getCurrentPlayerInfo();

    }

    public String printReplay(){

        return fourInLineManager.printReplay();
    }

    public boolean playReplay(){

        boolean ret = fourInLineManager.playReplay();
        fireAlmostAll();
        return ret;

    }

    public void SaveReplay(){

        fourInLineManager.SaveReplay();

    }

    public boolean LoadReplay(int nReplay){


        boolean ret = fourInLineManager.LoadReplay(nReplay);


        return ret;
    }

    public boolean LoadReplay(File f){


        boolean ret = fourInLineManager.LoadReplay(f);
        fireAlmostAll();

        return ret;
    }

    public void playReplays(){

        fourInLineManager.playReplays();
        fireAlmostAll();
    }

    public boolean SaveGame(String nameFile){

        return fourInLineManager.SaveGame(nameFile);

    }
    public boolean SaveGame(File f){

        return fourInLineManager.SaveGame(f);

    }
    public boolean Loadgame(String nameFile){

        boolean ret = fourInLineManager.Loadgame(nameFile);

        fireAlmostAll();

        return ret;
    }
    public boolean Loadgame(File f){

        boolean ret = fourInLineManager.Loadgame(f);

        fireAlmostAll();

        return ret;
    }

    public void fireAlmostAll(){

        propertyChangeSupport.firePropertyChange(UPDATED_BOARD_PROPERTY,null,null);
        propertyChangeSupport.firePropertyChange(SITUATION_CHANGED_PROPERTY,null,null);
        propertyChangeSupport.firePropertyChange(PLAYER_CHANGED_PROPERTY,null,null);

    }
    @Override
    public String toString() {
        return fourInLineManager.toString();
    }

}

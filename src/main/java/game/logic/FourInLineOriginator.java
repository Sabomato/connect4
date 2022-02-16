package game.logic;

import game.logic.data.GameData;

import game.logic.data.Player.IPlayer;
import game.logic.data.minigame.MinigameAns;
import game.logic.memento.IMemento;
import game.logic.memento.IOriginator;
import game.logic.memento.Memento;
import game.logic.memento.Snapshot;
import game.logic.states.IStates;
import game.logic.states.Situations;
import game.logic.states.WaitStart;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

public class FourInLineOriginator implements IOriginator {

    public static final long serialVersionUID = 1L;

    private GameData gameData;
    private IStates state;

    public FourInLineOriginator() {
        this.gameData = new GameData();
        this.state = new WaitStart(gameData);
    }

    public void Next(){
        this.state = state.Next();
    }

    public boolean isValidNormalPlay(int nColumn){
        return gameData.isValidNormalPlay(nColumn);
    }
    public boolean isValidSpecialPlay(int nColumn){
        return gameData.isValidSpecialPlay(nColumn);
    }

    public void PlayNormalPiece(int nColumn){

        this.state = state.PlayNormalPiece(nColumn);

    }

    public boolean  checkValidPlayersName(String nameP1,String nameP2){
        return gameData.checkValidPlayersName(nameP1,nameP2);
    }

    public void endTurn(){
        gameData.EndTurn();

    }

    public boolean PlaySpecialPiece(int column){

        return gameData.PlaySpecialPiece(column);

    }

    public void SetPlayersName(String P1Name, String P2Name){

        this.state = state.SetPlayersName(P2Name,P1Name);
    }

    public void GameOver()
    {
        this.state = state.GameOver();
    }

    public void SetHumanORVirtual(int playerType){

        this.state = state.SetHumanORVirtual(playerType);
    }

    public ArrayList<Vector<Character>> getWholeGridCopy(){

        return gameData.getWholeGridCopy();
    }
    public boolean isMinigameTurn(){
        return gameData.isMinigameTurn();
    }

    public void insertMinigameAnswer(String answer){

        this.state = state.insertMinigameAnswer(answer);
        return;

    }

    public void incNPlays(){
        gameData.incNPlays();
    }

    public IPlayer getCurrentPlayerInfo(){
        return gameData.getCurrentPlayerInfo();

    }

    public String getMinigameQuestion() {

        return gameData.GetMinigameQuestion();
    }
    public int getTurn(){

        return gameData.getTurn();
    }

    public String getMinigameInfo(){

        return gameData.getMinigameInfo();
    }

    public void isGoingToPlayMinigame(boolean answer){

        this.state = state.isGoingToPlayMinigame(answer);

    }

    public void playReplays(){
        this.state = state.PlayReplays();
    }

    public MinigameAns getMinigameAnswer(){
        return gameData.getMinigameAnswer();

    }

    public IPlayer tryUndo(int nCredits){

        return gameData.tryUndo(nCredits);
    }

    public boolean undoByName(String name,int nCredits,int nSpecialPieces){

        return gameData.undoByName(name,nCredits,nSpecialPieces);
    }

    public String getSnapshot(){

        return gameData.printSnapshot();

    }

    public Situations getCurrentSituation(){

        return state.getCurrentState();
    }

    @Override
    public IMemento createMemento() throws IOException {
        return new Memento(new Snapshot(gameData,state));

    }

    @Override
    public void restoreMemento(IMemento m) throws IOException, ClassNotFoundException {

        Memento memento = (Memento) m;
        Snapshot s = (Snapshot)memento.getSnapshot();
        gameData = s.getGameData();
        state = s.getState();

    }

    @Override
    public String toString() {
        return gameData.toString();
    }

}

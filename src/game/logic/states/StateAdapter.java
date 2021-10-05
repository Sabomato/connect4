package game.logic.states;

import game.logic.data.GameData;

import java.io.Serializable;

public class StateAdapter implements IStates, Serializable {
    public static final long serialVersionUID = 1L;

    protected GameData gameData;

    public StateAdapter(GameData gameData) {
        this.gameData = gameData;
    }

    @Override
    public IStates insertMinigameAnswer(String answer) {
        return this;
    }

    @Override
    public IStates PlayNormalPiece(int column) {
        return this;
    }



    @Override
    public IStates Next() {
        return this;
    }


    @Override
    public IStates isGoingToPlayMinigame(boolean answer) {
        return this;
    }

    @Override
    public IStates GameOver() {
        return new WaitStart(gameData);
    }



    @Override
    public IStates PlayReplays() {
        return this;
    }

    @Override
    public IStates SetPlayersName(String P1Name, String P2Name) {

        return this;
    }

    @Override
    public IStates SetHumanORVirtual(int playerType) {
        return this;
    }

    @Override
    public Situations getCurrentState() {

        return null;
    }
}

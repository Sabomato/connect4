package game.logic.memento;

import game.logic.data.GameData;
import game.logic.states.IStates;

import java.io.Serializable;

public class Snapshot implements Serializable {

    private GameData gameData;
    private IStates state;

    public Snapshot(GameData gameData, IStates state) {
        this.gameData = gameData;
        this.state = state;
    }

    public GameData getGameData() {
        return gameData;
    }

    public IStates getState() {
        return state;
    }
}

package game.logic.states;


import game.logic.data.GameData;

public class WaitPlayerNames extends StateAdapter {
    public WaitPlayerNames(GameData gameData) {
        super(gameData);
    }

    @Override
    public IStates SetPlayersName(String P1Name, String P2Name) {

        return gameData.setPlayersName(P1Name,P2Name)? new WaitPlay(gameData):this;
    }

    @Override
    public Situations getCurrentState() {
        return Situations.PLAYER_NAMES;
    }
}

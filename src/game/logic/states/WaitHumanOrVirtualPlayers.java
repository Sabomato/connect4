package game.logic.states;

import game.logic.data.GameData;

public class WaitHumanOrVirtualPlayers extends StateAdapter{

    public WaitHumanOrVirtualPlayers(GameData gameData) {
        super(gameData);
    }


    @Override
    public IStates SetHumanORVirtual(int PlayerType) {

        if(gameData.initPlayers(PlayerType))
            return new WaitPlayerNames(gameData);
        return this;
    }

    @Override
    public Situations getCurrentState() {
        return Situations.HUMAN_OR_VIRTUAL;
    }
}

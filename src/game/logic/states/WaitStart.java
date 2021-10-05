package game.logic.states;

import game.logic.data.GameData;

import java.util.Currency;

public class WaitStart extends StateAdapter{

    public WaitStart(GameData gameData) {
        super(gameData);
    }

    @Override
    public IStates Next() {
        return new WaitHumanOrVirtualPlayers(gameData);
    }

    @Override
    public IStates GameOver() {
        return this;
    }

    @Override
    public IStates PlayReplays() {
        return new WaitPlayReplays(gameData);
    }

    @Override
    public Situations getCurrentState() {
        return Situations.START;
    }
}

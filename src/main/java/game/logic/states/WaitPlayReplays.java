package game.logic.states;

import game.logic.data.GameData;

public class WaitPlayReplays extends StateAdapter{


    public WaitPlayReplays(GameData gameData) {
        super(gameData);
    }


    @Override
    public Situations getCurrentState() {
        return Situations.PLAY_REPLAY;
    }
}

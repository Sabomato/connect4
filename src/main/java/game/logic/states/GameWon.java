package game.logic.states;

import game.logic.data.GameData;

public class GameWon extends StateAdapter{


    public GameWon(GameData gameData) {
        super(gameData);
    }

    @Override
    public Situations getCurrentState() {
        return Situations.GAME_WON;
    }
}

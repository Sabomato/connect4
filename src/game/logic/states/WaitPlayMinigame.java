package game.logic.states;

import game.logic.data.GameData;
import game.logic.data.minigame.Minigame;
import game.logic.data.minigame.MinigameAns;

public class WaitPlayMinigame extends StateAdapter{


    public WaitPlayMinigame(GameData gameData) {
        super(gameData);
    }

    @Override
    public IStates insertMinigameAnswer(String answer) {

        MinigameAns ret = gameData.InsertAnswerMinigame(answer);

        if(ret.equals(MinigameAns.TIME_OVER) || ret.equals(MinigameAns.WON) || ret.equals(MinigameAns.GAVE_UP))
            return new WaitPlay(gameData);

        return this;

    }

    @Override
    public IStates isGoingToPlayMinigame(boolean answer) {

         return answer ? this: new WaitPlay(gameData);
    }

    @Override
    public Situations getCurrentState() {
        return Situations.PLAY_MINIGAME;
    }
}

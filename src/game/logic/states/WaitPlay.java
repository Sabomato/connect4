package game.logic.states;

import game.logic.data.GameData;
import game.logic.data.Grid;

public class WaitPlay extends StateAdapter{

    public WaitPlay(GameData gameData) {
        super(gameData);
    }

    @Override
    public IStates PlayNormalPiece(int nColumn) {

        int ret = gameData.PlayerNormalPiece(nColumn);

        if(ret == Grid.WIN)
            return new GameWon(gameData);

        if(ret == Grid.VALID_PLAY)
            gameData.EndTurn();

        if(gameData.isMinigameTurn())
            return new WaitPlayMinigame(gameData);

        return this;
    }


    @Override
    public Situations getCurrentState() {
        if(gameData.isCurrentPlayerHuman())
            return Situations.WAIT_PLAY_HUMAN;
        else
            return  Situations.WAIT_PLAY_VIRTUAL;
    }
}

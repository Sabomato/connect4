package game.logic.states;

public interface IStates {



        IStates insertMinigameAnswer(String answer);

        IStates SetHumanORVirtual(int playerType);

        IStates PlayNormalPiece(int column);

        IStates isGoingToPlayMinigame(boolean answer);

        IStates Next();

        IStates GameOver();

        IStates PlayReplays();

        IStates SetPlayersName(String P1Name, String P2Name);

        Situations getCurrentState();

}


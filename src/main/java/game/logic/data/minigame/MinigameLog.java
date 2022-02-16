package game.logic.data.minigame;

import java.io.Serializable;

public class MinigameLog implements Serializable {

    private static final long serialVersionUID =1L;
    private int turn;
    private String PlayerName,minigame;
    private boolean won;

    public MinigameLog(String playerName, String minigame, boolean won,int turn) {
        PlayerName = playerName;
        this.minigame = minigame;
        this.won = won;
        this.turn = turn;
    }

    @Override
    public String toString() {

        return new StringBuilder().append(String.format(("\nTurn: %d Player:%s Minigame:%s Result:%s\n")
                ,turn,PlayerName,minigame,won? "Won" : "Lost")).toString();
    }
}

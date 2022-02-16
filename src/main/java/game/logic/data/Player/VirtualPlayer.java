package game.logic.data.Player;

import game.logic.data.Grid;
import game.logic.data.minigame.Calculation;

public class VirtualPlayer extends Player{

    public static final long serialVersionUID = 1L;
    public VirtualPlayer() {
        super();

    }

    @Override
    public int PlayNormalPiece(Grid g, int nColumn) {

        int randColumn = Calculation.getRandNumber(1,Grid.NCOLUMNS);
        int ret = g.addPiece(randColumn,piece);

        return ret;

    }

    @Override
    public String toString() {
        return super.toString()+"Virtual";
    }
}

package game.logic.data.Player;

import java.io.Serializable;

public interface IPlayer extends Serializable {

    public boolean undo(int nCredits,int nSpecialPieces);

    public String getName();

    public int getUndoCredits();

    public int getNSpecialPieces();

    public boolean isHuman();

    public char getPiece();


}

package game.logic.data.Player;

import game.logic.data.Grid;
import game.logic.data.minigame.MinigameAns;
import game.logic.data.minigame.Minigames;

public class HumanPlayer extends Player{

    public static final long serialVersionUID = 1L;

    public static  final int MAX_UNDO_CREDITS = 5;
    private int nPlays, undoCredits,nSpecialPieces;
    private boolean MinigameFlag = true;

    public HumanPlayer() {
        super();
        nSpecialPieces = 0;
        this.undoCredits = 5;
        this.nPlays = 0;//1;

    }

    @Override
    public boolean isHuman() {
        return this instanceof HumanPlayer;
    }

    @Override
    public boolean isMinigameTurn() {


        if(MinigameFlag)
            return nPlays % 4 == 0 && nPlays > 0;

        return false;

    }
    public void incNplays(){
        ++nPlays;
    }
    @Override
    public MinigameAns InsertAnswerMinigame(Minigames minigames, String answer, int turn) {

        MinigameAns ret = minigames.InsertAnswer(answer,this.getName(),turn);
        if(ret.equals(MinigameAns.WON)){
            MinigameFlag = false;
            ++nSpecialPieces;
        }


        return  ret;
    }

    @Override
    public int getNSpecialPieces() {
        return nSpecialPieces;
    }

    @Override
    public boolean PlaySpecialPiece(Grid g, int nColumn){

        if(nSpecialPieces <= 0)
            return false;

        if(!g.clearColumn(nColumn))
            return false;


        --nSpecialPieces;
        //++nPlays;
        return true;

    }
    @Override

    public int PlayNormalPiece(Grid g,int nColumn){

        int ret = g.addPiece(nColumn,piece);
        //if(ret !=Grid.INVALID_PLAY);
            //++nPlays;
        MinigameFlag = true;
        return ret;

    }
    @Override
    public void IncreaseSpecialPieces() {

        ++this.nSpecialPieces;
    }

    public boolean DecreaseSpecialPieces() {

        if(this.nSpecialPieces <= 0 )
            return false;

        //--this.nSpecialPieces;
        return true;
    }

    @Override
    public IPlayer tryUndo(int nCredits) {
        if(undoCredits < nCredits)
            return null;

        return this;

    }

    @Override
    public int getUndoCredits() {
        return undoCredits;
    }

    @Override
    public boolean undo(int nCredits,int nSpecialPieces){
        if(undoCredits < nCredits)
            return false;
        else
            undoCredits -= nCredits;

        nPlays = 0;
        return true;
    }
    @Override
    public String toString() {

        return super.toString() + String.format(" UndoCredits:%d Special Pieces:%d Human",undoCredits,nSpecialPieces);
    }
}

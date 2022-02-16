package game.logic.data;

import java.io.Serializable;
import java.util.*;



public class Grid implements Serializable {

    private static final long serialVersionUID =1L;

    public static final int WIN = 1, VALID_PLAY = 0,INVALID_PLAY = -1;
    public static final char BLUE_PIECE = 'X', RED_PIECE ='O';


    public final static int NCOLUMNS = 7;
    public final static int NROWS = 6;

    private final ArrayList<Vector<Character>> game;

    public Grid() {
        game = new ArrayList<>(NCOLUMNS);

        for (int i = 0 ; i < NCOLUMNS; ++i) {
            game.add(new Vector<>(NROWS, 0));
            Vector<Character> v = game.get(i);

            for(int j = 0; j < v.capacity();++j)
                v.addElement(' ');
        }

    }
    public ArrayList<Vector<Character>> getWholeGridCopy(){

        ArrayList<Vector<Character>> grid = new ArrayList<>(NCOLUMNS);
        for (int i = 0 ; i < NCOLUMNS; ++i) {
            grid.add(new Vector<>(game.get(i).size()));
            grid.get(i).setSize(game.get(i).size());
            Collections.copy(grid.get(i),game.get(i));
        }
        return grid;
    }
    public boolean isValidNormalPlay(int nColumn){

        return nColumn <= game.size() && nColumn >= 1 && game.get(nColumn - 1).get(NROWS - 1).equals(' ');
    }

    public boolean isValidSpecialPlay(int nColumn){

        return nColumn <= game.size() && nColumn >= 1;
    }

    public int addPiece (int nColumn,Character piece){

        if(!isValidNormalPlay(nColumn))
            return INVALID_PLAY;

        Vector<Character> v = game.get(nColumn-1);

            for(int i = 0; i < v.size();++i)
                if (v.get(i).equals(' ')) {
                    v.set(i, piece);
                    break;
                }

            return checkWin(piece);

    }

    public boolean clearColumn(int nColumn){

        if(nColumn > game.size()+1 || nColumn < 1 )
            return false;
        Collections.fill(game.get(nColumn-1),' ');
        return true;

    }

    private int checkWin(Character piece) {

        int i,j;
        //horizontal check
        for ( i = game.size() - 1; i >= 3; --i)
            for ( j = game.get(i).size() - 1; j >= 0; --j)
                if (game.get(i).get(j).equals(piece) && game.get(i - 1).get(j).equals(piece) && game.get(i - 2).get(j).equals(piece) && game.get(i - 3).get(j).equals(piece))
                    return WIN;

        //vertical check
        for ( i = game.size() - 1; i >= 0; --i)
            for ( j = game.get(i).size() - 1; j >= 3; --j)
                if (game.get(i).get(j).equals(piece) && game.get(i).get(j - 1).equals(piece) && game.get(i).get(j - 2).equals(piece) && game.get(i).get(j - 3).equals(piece))
                    return WIN;

        //descending diagonal check
        for ( i = game.size() - 4; i >= 0; --i)
            for ( j = game.get(i).size() - 1; j >= 3; --j)
                if (game.get(i).get(j).equals(piece) && game.get(i + 1).get(j - 1).equals(piece) && game.get(i + 2).get(j - 2).equals(piece) && game.get(i + 3).get(j - 3).equals(piece))
                    return WIN;

        //ascending diagonal check
        for ( i = game.size() - 4; i >= 0; --i)
            for ( j = game.get(i).size() - 4; j >= 0; --j)
                if (game.get(i).get(j).equals(piece) && game.get(i + 1).get(j + 1).equals(piece) && game.get(i + 2).get(j + 2).equals(piece) && game.get(i + 3).get(j + 3).equals(piece))
                    return WIN;

        return VALID_PLAY;

    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder(game.size()*7);


        s.append('_').append("______".repeat(game.size())).append(System.lineSeparator());
        //s.append('|').append("   |".repeat(game.size())).append(System.lineSeparator());
        for (int i = NROWS-1; i >= 0; --i)
        {
            s.append("|  ");
            for (Vector<Character> characters : game) {
                s.append(characters.get(i)).append("  |  ");

            }

            s.append(System.lineSeparator()).append('|').append("_____|".repeat(game.size())).append(System.lineSeparator());


        }
        s.append(System.lineSeparator());
        return s.toString();
    }
}

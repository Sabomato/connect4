package game.ui.graphic.waitPlay;

import game.logic.FourInLineObservable;
import game.logic.states.Situations;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.util.ArrayList;
import java.util.Vector;
import game.ui.graphic.resources.ImageLoader;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import static game.logic.data.Grid.*;
import static game.logic.FourInLineObservable.*;
import static game.ui.graphic.ConstantsGUI.*;


public class PlayGUI extends BorderPane {

    private FourInLineObservable fourInLineObservable;
    GridPane gridGUI;
    PlayerInfoGUI playerInfoGUI;
    MenuBarPlay menuBarPlay;

    public PlayGUI(FourInLineObservable fourInLineObservable){

        this.fourInLineObservable = fourInLineObservable;


        playerInfoGUI = new PlayerInfoGUI(fourInLineObservable);
        setGrid();

        menuBarPlay = new MenuBarPlay(fourInLineObservable);



        setRight(playerInfoGUI);
        setTop(menuBarPlay);

        fourInLineObservable.addPropertyChangeListener(UPDATED_BOARD_PROPERTY, pc -> updateBoard());
        fourInLineObservable.addPropertyChangeListener(FourInLineObservable.SITUATION_CHANGED_PROPERTY, pc -> updateVisibility());


        updateVisibility();

    }

    private void setGrid(){
        gridGUI = new GridPane();

        gridGUI.setHgap(GRID_GAPS);
        gridGUI.setVgap(GRID_GAPS);


        gridGUI.setAlignment(Pos.CENTER);
        gridGUI.setBackground( new Background(new BackgroundFill(Color.GREY,new CornerRadii(2),Insets.EMPTY)));


        gridGUI.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
                null, new BorderWidths(5))));

        setLeft(gridGUI);

        updateBoard();
    }

    private void updateBoard(){

        gridGUI.getChildren().clear();
        ImageView piece;
        ArrayList<Vector<Character>> grid = fourInLineObservable.getWholeGridCopy();

        for(int i = 0 ; i < NCOLUMNS; ++i){
            Vector<Character> col = grid.get(i);
            int finalI = i + 1;

            for(int j = 0; j < NROWS; ++j){

                switch(col.get(j)) {

                    case BLUE_PIECE -> {

                        piece = new ImageView(ImageLoader.getImage(BLUE_PIECE_IMG));
                        piece.setPreserveRatio(true);
                        piece.setFitHeight(PIECE_RADIUS * 2);

                        gridGUI.add(piece, i, NROWS - j);
                        piece.setOnMouseClicked(eH->PlayPiece(finalI));
                    }
                    case RED_PIECE -> {

                        piece = new ImageView(ImageLoader.getImage(RED_PIECE_IMG));
                        piece.setPreserveRatio(true);
                        piece.setFitHeight(PIECE_RADIUS * 2);

                        gridGUI.add(piece, i, NROWS - j);
                        piece.setOnMouseClicked(eH->PlayPiece(finalI));

                    }
                    default -> {

                        Circle c = new Circle(PIECE_RADIUS, Color.WHITE);
                        gridGUI.add(c, i, NROWS - j);

                        c.setOnMouseClicked(eH->PlayPiece(finalI));
                    }


                }

            }
        }
    }

    public void PlayPiece(int nColumn){

        if (playerInfoGUI.getPieceToPlay() == PlayerInfoGUI.NORMAL_PIECE_OPT)
            fourInLineObservable.PlayNormalPiece(nColumn);
        else if (playerInfoGUI.getPieceToPlay() == PlayerInfoGUI.SPECIAL_PIECE_OPT)
            fourInLineObservable.PlaySpecialPiece(nColumn);

    }

    private void updateVisibility(){

        Situations situations = fourInLineObservable.getCurrentSituation();
        boolean visibility = situations == Situations.WAIT_PLAY_HUMAN || situations == Situations.WAIT_PLAY_VIRTUAL
                || situations == Situations.PLAY_REPLAY || situations == Situations.GAME_WON;
        setVisible(visibility);

        if(situations == Situations.WAIT_PLAY_VIRTUAL)
            fourInLineObservable.PlayNormalPiece(-1);





    }
}

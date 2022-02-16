package game.ui.graphic;

import game.logic.FourInLineObservable;
import game.logic.states.Situations;
import game.ui.graphic.waitPlay.PlayGUI;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;


import static game.logic.Save.SAVES_DIR;
import static game.ui.graphic.ConstantsGUI.*;

public class PlayReplayGUI extends BorderPane {

    FourInLineObservable fourInLineObservable;
    boolean bool;
    public PlayReplayGUI(FourInLineObservable fourInLineObservable) {

        this.fourInLineObservable = fourInLineObservable;

        setPrefSize(SCENE_WIDTH,SCENE_HEIGHT);



            setOnMouseClicked(eh->forwardReplay());
            setOnKeyPressed(eh->{

                if(eh.getCode() == KeyCode.ESCAPE)
                    fourInLineObservable.GameOver();
            });
        setOnKeyPressed(eh->{

            if(eh.getCode() == KeyCode.N){

                forwardReplay();

            }
        });

        fourInLineObservable.addPropertyChangeListener(FourInLineObservable.SITUATION_CHANGED_PROPERTY, pc -> updateVisibility());
        updateVisibility();
    }


    private void updateVisibility(){

        Situations situations = fourInLineObservable.getCurrentSituation();
        setVisible(situations == Situations.PLAY_REPLAY);

        if(situations == Situations.PLAY_REPLAY && bool) {
            bool = false;
            showAlert("How to Play replay", "After loading, press the N to move one turn");
        }
        if(situations != Situations.PLAY_REPLAY)
            bool = true;

    }

    private void forwardReplay(){
        fourInLineObservable.playReplay();
    }


    private void showAlert(String title, String msg) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle(title);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}

package game.ui.graphic;

import game.logic.FourInLineObservable;
import game.logic.states.Situations;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;

import static game.ui.graphic.ConstantsGUI.SCENE_HEIGHT;
import static game.ui.graphic.ConstantsGUI.SCENE_WIDTH;

public class GameOverGUI extends BorderPane {

    FourInLineObservable fourInLineObservable;
    public GameOverGUI(FourInLineObservable fourInLineObservable) {

        this.fourInLineObservable = fourInLineObservable;

        setPrefSize(SCENE_WIDTH,SCENE_HEIGHT);



        fourInLineObservable.addPropertyChangeListener(FourInLineObservable.SITUATION_CHANGED_PROPERTY, pc -> updateVisibility());
    }

    private void updateVisibility(){

        Situations situations = fourInLineObservable.getCurrentSituation();
        setVisible(situations == Situations.GAME_WON);
        if(situations == Situations.GAME_WON){
            showAlert("Winner", "We Have a winner!");
        }
    }
    private void showAlert(String title, String msg) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle(title);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}

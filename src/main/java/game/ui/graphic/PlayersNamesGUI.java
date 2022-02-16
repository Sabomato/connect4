package game.ui.graphic;

import game.logic.FourInLineObservable;
import game.logic.states.Situations;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import static game.ui.graphic.ConstantsGUI.SCENE_HEIGHT;
import static game.ui.graphic.ConstantsGUI.SCENE_WIDTH;

public class PlayersNamesGUI extends BorderPane {

    FourInLineObservable fourInLineObservable;
    TextField P1Name,P2Name;
    int ret;
    public PlayersNamesGUI(FourInLineObservable fourInLineObservable) {

        this.fourInLineObservable = fourInLineObservable;

        SetTextFields();

        fourInLineObservable.addPropertyChangeListener(FourInLineObservable.SITUATION_CHANGED_PROPERTY, pc -> updateVisibility());
        updateVisibility();
    }

    private void SetTextFields(){
        P1Name = new TextField();
        P2Name = new TextField();
        P1Name.setPromptText("Player one name");
        P2Name.setPromptText("Player two name");

        P1Name.setOnMousePressed(eH->P1Name.selectAll());
        P2Name.setOnMousePressed(eH->P2Name.selectAll());
        VBox box = new VBox(P1Name,P2Name);
        box.setSpacing(5);
        setMaxWidth(SCENE_WIDTH * 0.4);

        box.setAlignment(Pos.CENTER);
        box.setSpacing(SCENE_HEIGHT * 0.1);

        setCenter(box);
        setAlignment(box,Pos.CENTER);
        SetNextButton();

    }



    private void SetNextButton(){

        Button next = new Button("Next");
        next.setOnAction(new putPlayersNames());

        ;
        next.setPadding( new Insets(SCENE_HEIGHT * 0.02));
        next.setDefaultButton(true);


        HBox hBox = new HBox(next);
        hBox.setAlignment(Pos.TOP_CENTER);
        hBox.setPadding(new Insets(SCENE_HEIGHT * 0.1));
        setBottom(hBox);

    }

    private void updateVisibility(){

        Situations situations = fourInLineObservable.getCurrentSituation();
        setVisible(situations == Situations.PLAYER_NAMES);


    }

    private class putPlayersNames implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            Alert WrongNames;
            if (fourInLineObservable.checkValidPlayersName(P1Name.getText(), P2Name.getText()))
                fourInLineObservable.SetPlayersName(P1Name.getText(), P2Name.getText());
            else {
                WrongNames = new Alert(Alert.AlertType.INFORMATION, "Invalid Names!");
                WrongNames.show();
            }
            P1Name.setText("");
            P2Name.setText("");
        }

    }
}

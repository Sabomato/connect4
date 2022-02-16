package game.ui.graphic;

import game.logic.FourInLineObservable;
import game.logic.states.Situations;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;

import static game.logic.Save.SAVES_DIR;
import static game.ui.graphic.ConstantsGUI.*;


public class StartGUI extends BorderPane {

    Window ownerWindow;
    FourInLineObservable fourInLineObservable;

    public StartGUI(FourInLineObservable fourInLineObservable) {

        this.fourInLineObservable = fourInLineObservable;
        Button newGame = new Button("New Game");
        newGame.setOnAction(eH->this.fourInLineObservable.Next());
       // Button playReplays = new Button("Play replay");

        newGame.setMinWidth(SCENE_WIDTH * 0.5);
        newGame.setMaxHeight(SCENE_HEIGHT * 0.5);

        //playReplays.setOnAction(eH->fourInLineObservable.playReplays());
        VBox options = new VBox(newGame/*,playReplays*/);

        Label title = new Label("C o n n e c t  4");
        title.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC,40));

        VBox titleBox = new VBox(title);
        titleBox.setAlignment(Pos.CENTER);
        titleBox.setPadding( new Insets(SCENE_HEIGHT * 0.08));


        options.setSpacing(BUTTON_SPACING_START);
        options.setAlignment(Pos.CENTER);
        setCenter(options);
        setTop(titleBox);

        fourInLineObservable.addPropertyChangeListener(FourInLineObservable.SITUATION_CHANGED_PROPERTY, pc -> updateVisibility());



    }
    private void updateVisibility(){

        Situations situations = fourInLineObservable.getCurrentSituation();
        setVisible(situations == Situations.START);

    }


}

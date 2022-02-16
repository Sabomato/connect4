package game.ui.graphic;

import game.logic.FourInLineObservable;
import game.logic.states.Situations;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;


import static game.ui.graphic.ConstantsGUI.*;
import static game.logic.data.GameData.*;


public class SelectPlayerTypeGUI extends BorderPane {

    FourInLineObservable fourInLineObservable;
    int option = -1;

    public SelectPlayerTypeGUI(FourInLineObservable fourInLineObservable) {

        this.fourInLineObservable = fourInLineObservable;
        setMaxWidth(SELECT_PLAYER_TYPE_PANE_WIDTH);

        setSelections();

        SetNextButton();


        fourInLineObservable.addPropertyChangeListener(FourInLineObservable.SITUATION_CHANGED_PROPERTY, pc -> updateVisibility());
        updateVisibility();
    }

    private void onClick(PlayerSelectSelection selection, int opt){
        clearSelected();
        selection.Select();
        option = opt;
        //image.setStrokeWidth();
    }

    private void clearSelected(){

        VBox child = (VBox) getChildren().get(0);
        for( int i = 0; i < child.getChildren().size(); i++) {
            PlayerSelectSelection selection = (PlayerSelectSelection) child.getChildren().get(i);
            selection.UnSelect();


        }
    }

    private void SetNextButton(){

        Button next = new Button("Next");
        next.setOnAction(eH->fourInLineObservable.SetHumanORVirtual(option));
        next.setPadding( new Insets(SCENE_HEIGHT * 0.02));
        next.setDefaultButton(true);


        HBox hBox = new HBox(next);
        hBox.setAlignment(Pos.TOP_CENTER);
        hBox.setPadding(new Insets(SCENE_HEIGHT * 0.1));
        setBottom(hBox);

    }

    private void setSelections(){



        PlayerSelectSelection HumanPC = new PlayerSelectSelection(ONE_HUMAN_ONE_PC_IMG," Human Vs PC");
        HumanPC.setOnMouseClicked(ev->onClick(HumanPC,HUMAN_VIRTUAL));

        PlayerSelectSelection Humans = new PlayerSelectSelection(TWO_HUMAN_IMG," Human Vs Human");
        Humans.setOnMouseClicked(ev->onClick(Humans,HUMANS));

        //PlayerSelectSelection PCs = new PlayerSelectSelection(TWO_PC_IMG," PC Vs PC");
        //PCs.setOnMouseClicked(ev->onClick(PCs,VIRTUALS));

        VBox options = new VBox(/*PCs,*/HumanPC,Humans);
        //options.setAlignment(Pos.BOTTOM_CENTER);
        options.setAlignment(Pos.CENTER);
        options.setSpacing(SCENE_HEIGHT * 0.05);
        setCenter(options);


    }

    private void updateVisibility(){

        Situations situations = fourInLineObservable.getCurrentSituation();
        setVisible(situations == Situations.HUMAN_OR_VIRTUAL);

    }

}

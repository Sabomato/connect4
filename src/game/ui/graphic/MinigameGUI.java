package game.ui.graphic;

import game.logic.FourInLineObservable;
import game.logic.data.minigame.MinigameAns;
import game.logic.states.Situations;
import javafx.scene.control.*;

import java.util.Optional;

public class MinigameGUI {


    Alert wantsToPlay, info, wonAlert;
    TextInputDialog question;
    ButtonType yes,no;
    FourInLineObservable fourInLineObservable;
    Situations situations;
    boolean bool;
    public MinigameGUI(FourInLineObservable fourInLineObservable) {

        this.fourInLineObservable = fourInLineObservable;

        wonAlert = new Alert(Alert.AlertType.INFORMATION);
        wonAlert.setHeaderText("");

        SetWantsToPlay();
        setQuestion();
        setInfo();

        fourInLineObservable.addPropertyChangeListener(FourInLineObservable.SITUATION_CHANGED_PROPERTY, pc -> updateVisibility());
        fourInLineObservable.addPropertyChangeListener(FourInLineObservable.MINIGAME_QUESTION_PROPERTY, pc -> getQuestion());

    }


    private void setInfo(){
        info = new Alert(Alert.AlertType.INFORMATION);
        info.getButtonTypes().setAll(new ButtonType("Ok",ButtonBar.ButtonData.OK_DONE));
        info.setHeaderText("");
    }

    private void showInfo(){
        info.setContentText(fourInLineObservable.getMinigameInfo());
        info.showAndWait();
    }

    private void setQuestion() {
        question = new TextInputDialog();
        question.getEditor().setPromptText("Answer:");
        question.setTitle("Minigame");
        question.setHeaderText("Answer before the time is over!");
        question.setOnCloseRequest(ev->{});


    }

    private void getQuestion() {

        MinigameAns minigameAns = fourInLineObservable.getMinigameAnswer();

        if(minigameAns!= null)
        {
            if(minigameAns.equals(MinigameAns.WON)||
                minigameAns.equals(MinigameAns.TIME_OVER)||
                    minigameAns.equals(MinigameAns.GAVE_UP)) {
                wonAlert.setContentText(minigameAns.toString());
                wonAlert.show();
                return;
            }
        }



        question.setHeaderText(minigameAns == null?"": minigameAns.toString() );

        question.setContentText(fourInLineObservable.getMinigameQuestion());


        Optional<String> result = question.showAndWait();
        if (result.isPresent()){
            fourInLineObservable.insertMinigameAnswer(result.get());

        }else
        {
            fourInLineObservable.insertMinigameAnswer("End");
        }

        question.getEditor().clear();

    }

    private void fWantsToPlay(){
        if( situations != Situations.PLAY_MINIGAME )
            return;

        if(wantsToPlay.showAndWait().isPresent() && wantsToPlay.showAndWait().get() == yes ) {
            fourInLineObservable.isGoingToPlayMinigame(true);
            showInfo();
            getQuestion();
        }
        else
            fourInLineObservable.isGoingToPlayMinigame(false);

    }

    private void SetWantsToPlay(){

        wantsToPlay = new Alert(Alert.AlertType.INFORMATION,"Do you want to play the minigame?");
        wantsToPlay.setHeaderText("");
        yes = new ButtonType("Yes",ButtonBar.ButtonData.OK_DONE);
        no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        wantsToPlay.getButtonTypes().setAll(yes,no);
        wantsToPlay.setOnCloseRequest(ev->{});

    }

    private void updateVisibility(){

        situations = fourInLineObservable.getCurrentSituation();
        if(situations != Situations.PLAY_MINIGAME){
            bool = true;
        }
        if(situations == Situations.PLAY_MINIGAME && bool){
            bool = false;
            fWantsToPlay();

        }

    }

}

package game.ui.graphic;

import game.logic.FourInLineObservable;
import game.ui.graphic.waitPlay.PlayGUI;
import javafx.scene.layout.StackPane;


public class RootPane extends StackPane {

    FourInLineObservable fourInLineObservable;
    public RootPane(FourInLineObservable fourInLineObservable){

        this.fourInLineObservable = fourInLineObservable;

        StartGUI startGUI = new StartGUI(fourInLineObservable);
        SelectPlayerTypeGUI  selectPlayerTypeGUI= new SelectPlayerTypeGUI(fourInLineObservable);
        PlayersNamesGUI playersNamesGUI = new PlayersNamesGUI(fourInLineObservable);
        PlayGUI playGUI = new PlayGUI(fourInLineObservable);
        MinigameGUI minigameGUI = new MinigameGUI(fourInLineObservable);
       //PlayReplayGUI playReplayGUI = new PlayReplayGUI(fourInLineObservable);
        GameOverGUI gameOverGUI = new GameOverGUI(fourInLineObservable);
        getChildren().addAll(gameOverGUI,selectPlayerTypeGUI,/*playReplayGUI,*/playGUI,playersNamesGUI,startGUI);



    }
    void test(){
        fourInLineObservable.Next();
        this.fourInLineObservable.SetHumanORVirtual(0);
        this.fourInLineObservable.SetPlayersName("manuel","manuela");
  //      this.fourInLineObservable.PlayNormalPiece(1);
      //  this.fourInLineObservable.PlayNormalPiece(2);
   //     this.fourInLineObservable.PlayNormalPiece(3);

    }

}

package game.ui.graphic.waitPlay;


import game.logic.FourInLineObservable;
import game.logic.states.Situations;
import javafx.application.Platform;
import javafx.scene.control.*;
import javafx.scene.input.KeyCombination;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import static game.logic.Replay.REPLAY_DIR;
import static game.logic.Save.*;

import java.io.File;
import java.io.IOException;

public class MenuBarPlay extends MenuBar {

    Window ownerWindow;
    MenuItem  load, save, exit,loadReplay;
    FourInLineObservable fourInLineObservable;
    Menu options;

    public MenuBarPlay(FourInLineObservable fourInLineObservable) {
        this.fourInLineObservable = fourInLineObservable;


        options = new Menu("Options");


        load = new MenuItem("Load");
        save = new MenuItem("Save");
        exit = new MenuItem("Exit");
        loadReplay = new MenuItem("Load Replay");

        save.setOnAction(evt -> saveOption());
        load.setOnAction(evt -> loadOption());
        exit.setOnAction(evt -> fourInLineObservable.GameOver());
        loadReplay.setOnAction(evt->loadReplay());

        options.getItems().setAll( load, save,
                 new SeparatorMenuItem(),loadReplay,new SeparatorMenuItem(), exit);

        this.getMenus().add(options);


        load.setAccelerator(KeyCombination.keyCombination("SHORTCUT+L"));
        save.setAccelerator(KeyCombination.keyCombination("SHORTCUT+S"));
        exit.setAccelerator(KeyCombination.keyCombination("SHORTCUT+X"));
        loadReplay.setAccelerator(KeyCombination.keyCombination("SHORTCUT+R"));

        fourInLineObservable.addPropertyChangeListener(FourInLineObservable.SITUATION_CHANGED_PROPERTY, pc -> updateVisibility());

    }

    private void saveOption() {
        FileChooser fChooser = new FileChooser();
        fChooser.setInitialDirectory(new File (SAVES_DIR));
        File f = fChooser.showSaveDialog(ownerWindow);

        if(f==null)
            return;

        if(!fourInLineObservable.SaveGame(f))
            showAlert("Info","Error saving file!");

    }

    private void loadOption() {
        FileChooser fChooser = new FileChooser();
        fChooser.setInitialDirectory(new File (SAVES_DIR));
        File f = fChooser.showOpenDialog(ownerWindow);

        if(f==null)
            return;

        if(!fourInLineObservable.Loadgame(f))
            showAlert("Info","Error loading file!");

    }

    private void loadReplay() {

        FileChooser fChooser = new FileChooser();
        fChooser.setInitialDirectory(new File(REPLAY_DIR));
        File f = fChooser.showOpenDialog(ownerWindow);

        if(f==null)
            return;

        if(!fourInLineObservable.LoadReplay(f))
            showAlert("Info","Error loading replay!");

    }

    private void showAlert(String title, String msg) {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle(title);
        alert.setContentText(msg);
        alert.showAndWait();
    }
    private void updateVisibility(){

        Situations situations = fourInLineObservable.getCurrentSituation();
        boolean replay =  situations == Situations.PLAY_REPLAY;
        loadReplay.setDisable(true);
        if(replay){
            save.setDisable(true);
            load.setDisable(true);
            loadReplay.setDisable(false);
        }



    }
}

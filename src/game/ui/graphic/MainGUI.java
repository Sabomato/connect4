package game.ui.graphic;


import game.logic.FourInLineObservable;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import static game.ui.graphic.ConstantsGUI.*;
import static javafx.application.Application.launch;

public class MainGUI extends Application {

@Override
    public void start(Stage primaryStage)throws Exception {

        FourInLineObservable fourInLineObservable = new FourInLineObservable();
        RootPane root = new RootPane(fourInLineObservable);
        Scene scene = new Scene(root ,SCENE_WIDTH,SCENE_HEIGHT);

        primaryStage.setTitle("Connect4");
        primaryStage.setScene(scene);
        primaryStage.setOnCloseRequest(eh-> Platform.exit());
        primaryStage.setResizable(true);
        primaryStage.show();
    }



    public static void main(String[] args) {
        launch(args);
    }
}


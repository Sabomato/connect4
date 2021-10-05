package game.ui.graphic.waitPlay;

import game.logic.FourInLineObservable;
import game.logic.data.Player.IPlayer;
import game.ui.graphic.resources.ImageLoader;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import static game.logic.data.Grid.*;

import static game.ui.graphic.ConstantsGUI.*;

public class PlayerInfoGUI extends VBox {

    public final static int SPECIAL_PIECE_OPT = 1, NORMAL_PIECE_OPT = 0;

    private FourInLineObservable fourInLineObservable;
    private Label Name, specialPieceLabel, UndoTxt,turn;
    private ImageView playerType,UndosImg,specialPiece;
    private Rectangle selectSpecialPiece,selectPiece,selectUndo;
    private TextField undoField;
    private HBox Selects;
    private int pieceToPlay;

    public PlayerInfoGUI(FourInLineObservable fourInLineObservable) {

        setPrefWidth(PLAYER_INFO_WIDTH);

        setAlignment(Pos.CENTER);
        this.fourInLineObservable = fourInLineObservable;
        setSpacing(SCENE_HEIGHT * 0.05);
        setInfo();

        fourInLineObservable.addPropertyChangeListener(FourInLineObservable.PLAYER_CHANGED_PROPERTY, pc -> updateInfo());



    }

    private void setPlayerNameAndTypeInfo(){

        turn = new Label("Turn:" + fourInLineObservable.getTurn());

        playerType = new ImageView();

        Name = new Label("",playerType);

        getChildren().addAll(turn ,Name);
    }

    private void SetUndoInfo(){

        UndosImg = new ImageView(ImageLoader.getImage(UNDO_ICON_IMG));
        UndosImg.setPreserveRatio(true);
        UndosImg.setFitHeight(PLAYER_INFO_HBOX_HEIGHT * 0.5);

        UndoTxt = new Label("Undos:",UndosImg);

        getChildren().add(UndoTxt);
    }

    private void setSpecialPieceInfo(){


        specialPiece = new ImageView(ImageLoader.getImage(SPECIAL_PIECE_IMG));
        specialPiece.setPreserveRatio(true);
        specialPiece.setFitHeight(PLAYER_INFO_HBOX_HEIGHT * 0.50);

        specialPieceLabel = new Label("Special Pieces: ",specialPiece);

        getChildren().add(specialPieceLabel);
    }

    private void setSelectInfo(){

        selectPiece = new Rectangle(SELECT_PIECE_SIZE,SELECT_PIECE_SIZE);
        selectPiece.setOnMouseClicked(eH->Select(selectPiece,NORMAL_PIECE_OPT));


        selectPiece.setStroke(Color.BLACK);


        selectSpecialPiece = new Rectangle(SELECT_PIECE_SIZE,SELECT_PIECE_SIZE,new ImagePattern(ImageLoader.getImage(SPECIAL_PIECE_IMG)));
        selectSpecialPiece.setOnMouseClicked(eH->Select(selectSpecialPiece,SPECIAL_PIECE_OPT));
        selectSpecialPiece.setStroke(Color.BLACK);


        selectUndo  = new Rectangle(SELECT_PIECE_SIZE,SELECT_PIECE_SIZE,new ImagePattern(ImageLoader.getImage(UNDO_ICON_IMG)));
        selectUndo.setOnMouseClicked(new showUndoFiled());
        selectUndo.setStroke(Color.BLACK);

        undoField = new TextField();
        undoField.setPromptText("How many turns?");
        undoField.setOnKeyPressed(new Undo());
        undoField.setVisible(false);
        undoField.setPrefColumnCount(1);

        Selects = new HBox(selectPiece, selectSpecialPiece,new VBox(selectUndo,undoField));
        Selects.setSpacing(PLAYER_INFO_WIDTH * 0.15);
        Selects.setAlignment(Pos.TOP_CENTER);
        getChildren().add(Selects);

    }

    private void setInfo(){

        setPlayerNameAndTypeInfo();

        SetUndoInfo();

        setSpecialPieceInfo();

        setSelectInfo();
    }

    private void Select(Rectangle Image,int option){

        UnSelect(Image);
        Image.setStrokeWidth(PLAYER_INFO_WIDTH * 0.01);
        pieceToPlay = option;


    }

    private void UnSelect(Rectangle Image){

        selectPiece.setStrokeWidth(0);
        selectSpecialPiece.setStrokeWidth(0);
        selectUndo.setStrokeWidth(0);
        undoField.setVisible(false);

    }

    private void updateInfo(){

        IPlayer current = fourInLineObservable.getCurrentPlayerInfo();

        if(current == null)
            return;

        turn.setText("Turn:" + fourInLineObservable.getTurn()+"");

        playerType.setImage(ImageLoader.getImage(current.isHuman() ? HUMAN_IMG : PC));
        playerType.setPreserveRatio(true);
        playerType.setFitHeight(PLAYER_INFO_HBOX_HEIGHT * 0.5);

        Name.setText(current.getName());


        UndoTxt.setText("Undos:" + current.getUndoCredits());


        specialPieceLabel.setText("Special Pieces:" + current.getNSpecialPieces() );

        String imageName;
        char piece = current.getPiece();

        if(piece == BLUE_PIECE)
            imageName = BLUE_PIECE_IMG;
        else
            imageName = RED_PIECE_IMG;

        selectPiece.setFill(new ImagePattern(ImageLoader.getImage(imageName)));

    }

    private class showUndoFiled implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {

            if (event.getButton().equals(MouseButton.PRIMARY))
                if (event.getClickCount() == 1) {
                    UnSelect(selectUndo);
                    selectUndo.setStrokeWidth(PLAYER_INFO_WIDTH * 0.01);
                    undoField.setVisible(true);
                }
        }
    }

    private class Undo implements EventHandler<KeyEvent> {
        @Override
        public void handle(KeyEvent event) {
            Alert undoAlert =  new Alert(Alert.AlertType.INFORMATION);
            undoAlert.setTitle("");
            undoAlert.setHeaderText("");

            if(event.getCode() == KeyCode.ENTER)
            {
                String input = undoField.getText();

                try {
                    int undoCredits = Integer.valueOf(input);
                    if(!fourInLineObservable.undo(undoCredits))
                        undoAlert.setContentText("You don't have enought credits!");
                    else
                        undoAlert.setContentText("Turns were undone!");
                }
                catch(NumberFormatException e){
                    undoAlert.setContentText("Invalid input!");

                }
                finally {
                    undoAlert.show();
                    undoField.setVisible(false);
                }

            }
        }
    }

    public int getPieceToPlay(){
        return pieceToPlay;
    }



}

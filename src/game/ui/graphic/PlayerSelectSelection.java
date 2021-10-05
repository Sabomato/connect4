package game.ui.graphic;


import game.ui.graphic.resources.ImageLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;


import static game.ui.graphic.ConstantsGUI.PLAYER_TYPE_SELECT_SIZE;
import static game.ui.graphic.ConstantsGUI.*;

public class PlayerSelectSelection extends HBox {

    Rectangle image;
    Label textLabel;
    public PlayerSelectSelection(String ImageName, String text) {

        setMaxWidth(WIDTH_PLAYER_SELECTION_HBOX);
        image  = new Rectangle(PLAYER_TYPE_SELECT_SIZE,PLAYER_TYPE_SELECT_SIZE,new ImagePattern(ImageLoader.getImage(ImageName)));
        if(text != null)
            textLabel = new Label(text);
        getChildren().addAll(image,textLabel);

    }

    public void Select(){


        setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID,
                new CornerRadii(5), new BorderWidths(PLAYER_TYPE_SELECT_SIZE * 0.05))));

        //image.setStrokeWidth();
    }
    public void UnSelect(){
        setBorder(Border.EMPTY);

    }
}

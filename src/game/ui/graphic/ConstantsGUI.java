package game.ui.graphic;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

public class ConstantsGUI {

    public static final String BLUE_PIECE_IMG = "bluePiece.png", RED_PIECE_IMG ="redPiece.png",
    ONE_HUMAN_ONE_PC_IMG = "1Human1PC.png", TWO_HUMAN_IMG = "2Human.png",TWO_PC_IMG = "2PC.png",
    HUMAN_IMG = "human.png",PC = "PC.png", UNDO_ICON_IMG = "undo-icon.png",SPECIAL_PIECE_IMG = "specialPiece1.png";


    private static final Rectangle2D screenDim = Screen.getPrimary().getBounds();

    public final static double SCENE_HEIGHT = screenDim.getHeight() * 0.5;
    public final static double SCENE_WIDTH = screenDim.getWidth() * 0.5;
    public final static double GRID_HEIGHT = SCENE_HEIGHT * 0.80;
    public final static double GRID_WIDTH = SCENE_WIDTH * 0.7,
    GRID_GAPS = SCENE_WIDTH * 0.01;
    public final static double PIECE_RADIUS = GRID_WIDTH * 0.055;
    public final static double BUTTON_SPACING_START = SCENE_HEIGHT * 0.05;
    public final static double PLAYER_TYPE_SELECT_SIZE = SCENE_HEIGHT * 0.15,
    SELECT_PLAYER_TYPE_PANE_WIDTH = SCENE_WIDTH * 0.6,
    WIDTH_PLAYER_SELECTION_HBOX = SCENE_WIDTH * 0.4,
    PLAYER_INFO_WIDTH = SCENE_WIDTH * 0.39,
    SELECT_PIECE_SIZE = PLAYER_INFO_WIDTH * 0.2,
    PLAYER_INFO_HBOX_HEIGHT = SCENE_HEIGHT * 0.25;






}

package game.ui.text;

import game.logic.FourInLineManager;
import game.ui.text.TextUI;

public class Main {

    public static void main(String[] args) {


        FourInLineManager fourInLineManager = new FourInLineManager();
        TextUI textUI = new TextUI(fourInLineManager);
        textUI.run();

    }
}

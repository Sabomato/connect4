package game.ui.text;

import game.logic.FourInLineManager;
import game.logic.states.Situations;

import java.io.IOException;
import java.util.Scanner;

import static java.lang.Thread.sleep;

public class TextUI {

    private FourInLineManager fourInLineManager;
    private boolean exit = false;
    private Scanner sc = new Scanner(System.in);
    int option;
    boolean flag;
    private Situations situation;

    public TextUI(FourInLineManager fourInLineManager) {

        this.fourInLineManager = fourInLineManager;
    }

    void WaitStartUI() {


        System.out.println("-1 - Exit");
        System.out.println("0 - New Game");
        System.out.println("1 - Play Replay");
        System.out.println("2 - Load Game");
        System.out.print("> ");

        while (!sc.hasNextInt()) sc.next();

        option = sc.nextInt();

        switch (option){

            case -1 -> exit = true;

            case 0 -> fourInLineManager.Next();

            case 1-> fourInLineManager.playReplays();

            case 2->{
                System.out.println("File name:");
                String name = sc.next();

                if(fourInLineManager.Loadgame(name))
                    System.out.println("File loaded with sucessfully");
                else
                    System.err.println("Error loading file (probably doesn't exist)");
            }

        }

    }

    void WaitHumanOrVirtualPlayersUI(){


        System.out.println(situation);

        System.out.println("-1 - Exit");
        System.out.println("0 - Humans");
        System.out.println("1 - Human and Virtual");
        System.out.println("2 - Virtuals");
        System.out.print("> ");

        while (!sc.hasNextInt()) sc.next();
        option = sc.nextInt();


        if(option == -1)
            fourInLineManager.GameOver();
        else if (option >=0 && option <= 2)
            fourInLineManager.SetHumanORVirtual(option);




    }

    void SetPlayersNameUI() {

        System.out.println(situation);
        System.out.print("P1 Name: ");

        String nameP1 = sc.next();

        System.out.print("P2 Name: ");

        String nameP2 = sc.next();

        fourInLineManager.SetPlayersName(nameP1,nameP2);


    }

    public void WaitPlayHumanUI() {

        System.out.println();
        System.out.println(fourInLineManager);
        System.out.println();

        if(fourInLineManager.isMinigameTurn()){
            WaitMinigameUI();
            return;
        }

        System.out.println(situation);

        System.out.println("-1 - Exit");
        System.out.println("0 - Play Normal Piece");
        System.out.println("1 - Play Special Piece");
        System.out.println("2 - Undo");
        System.out.println("3 - Save Game");


        System.out.print("> ");

        while (!sc.hasNextInt())
            sc.next();
        option = sc.nextInt();

        switch(option) {

            case -1 -> fourInLineManager.GameOver();

            case 0 -> {
                System.out.println("Column number :");
                while (!sc.hasNextInt())
                    sc.next();
                option = sc.nextInt();

                if(fourInLineManager.isValidNormalPlay(option))
                    fourInLineManager.PlayNormalPiece(option);
                else
                    System.out.println("Invalid Play");

            }

            case 1 -> {
                System.out.println("Column number :");
                while (!sc.hasNextInt())
                    sc.next();
                option = sc.nextInt();
                if(fourInLineManager.isValidSpecialPlay(option))
                    fourInLineManager.PlaySpecialPiece(option);
                else
                    System.out.println("Invalid Play");
            }

            case 2 -> {

                    System.out.println("number of undos(>0) :");

                    while (!sc.hasNextInt())
                        sc.next();
                    option = sc.nextInt();
                if(option<1)
                    break;

                if(!fourInLineManager.undo(option))
                    System.out.println("You don't have enough UndoCredits or there are not enough previous turns.");
                else
                    System.out.println("Turns undone with sucess");
            }
            case 3->{

                System.out.println("File name:");
                String name = sc.next();

                if(fourInLineManager.SaveGame(name))
                    System.out.println("File Saved sucessfully");
                else
                    System.err.println("Error saving file");

            }

        }
        flag = true;
    }

    public void WaitPlayVirtualUI(){

        System.out.println(fourInLineManager);

        System.out.println(situation);

        fourInLineManager.PlayNormalPiece(0);

        flag = true;
    }

    public void WaitMinigameUI(){

        System.out.println("It's Minigame turn, do you wanna play?");
        System.out.println("-1 -Exit "+ System.lineSeparator() +"0 -Yes" + System.lineSeparator() + "1 -No");

        while (!sc.hasNextInt())
            sc.next();
       option = sc.nextInt();


       switch(option){

       case -1 ->exit = true;

       case 0 ->fourInLineManager.isGoingToPlayMinigame(true);

       case 1 ->fourInLineManager.isGoingToPlayMinigame(false);

       }

    }

    public void PlayMinigameUI() {


        if(flag){
            WaitMinigameUI();
            flag = false;
            return;
        }


        System.out.println(fourInLineManager.getMinigameInfo());

        System.out.println(fourInLineManager.getMinigameQuestion());


        String answer = sc.next();

        fourInLineManager.insertMinigameAnswer(answer);
        System.out.println(fourInLineManager.getMinigameAnswer());

    }

    public void PlayReplaysUI(){

        System.out.println(situation);
        System.out.println("-1 - Exit");
        System.out.println("1 - Replay_1");
        System.out.println("2 - Replay_2");
        System.out.println("3 - Replay_3");
        System.out.println("4 - Replay_4");
        System.out.println("5 - Replay_5");

        while (!sc.hasNextInt())
            sc.next();
        option = sc.nextInt();

        if(option == -1) {
            fourInLineManager.GameOver();
            return;
        }
        String ret;
        if(fourInLineManager.LoadReplay(option))
            System.out.println("Replay loaded sucessfully ");
        else
            System.err.println("Error loading replay");

        while((ret = fourInLineManager.printReplay()) != null){

            try {
             sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(ret);

        }

       // fourInLineManager.Next();
    }

    private void GameWonUI(){

        System.out.println();
        System.out.println(fourInLineManager);
        System.out.println();

        System.out.println(situation);
        System.out.println("-1 - Exit");

        while (!sc.hasNextInt())
            sc.next();
        option = sc.nextInt();

        if(option == -1)
            fourInLineManager.GameOver();


    }

    public void run(){

        while (!exit) {

            situation = fourInLineManager.getCurrentSituation();


            switch (situation) {

                case START -> WaitStartUI();

                case HUMAN_OR_VIRTUAL -> WaitHumanOrVirtualPlayersUI();

                case PLAYER_NAMES -> SetPlayersNameUI();

                case WAIT_PLAY_HUMAN -> WaitPlayHumanUI();

                case WAIT_PLAY_VIRTUAL -> WaitPlayVirtualUI();

                case PLAY_MINIGAME -> PlayMinigameUI();

                case PLAY_REPLAY -> PlayReplaysUI();

                case GAME_WON -> GameWonUI();
            }

        }

    }

}




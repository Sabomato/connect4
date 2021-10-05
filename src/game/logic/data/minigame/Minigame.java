package game.logic.data.minigame;

import java.io.IOException;
import java.io.Serializable;

public abstract class Minigame implements Serializable {

    private static final long serialVersionUID =1L;

    private long  StartWritingTime;
    protected int nAnswer;





    protected void StartWritingTime(){
        StartWritingTime = System.currentTimeMillis();
    }

    protected long WritingTime(){

        return (System.currentTimeMillis() - StartWritingTime)/1000;
    }

    abstract public String GetQuestion();

    abstract MinigameAns InsertAnswer(String answer);

    @Override
    abstract public String toString();

    abstract public String Info();

}

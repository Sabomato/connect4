package game.logic;


import game.logic.memento.*;


import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;


public class FourInLineCareTaker implements Serializable {

    public static final long serialVersionUID = 1L;

    private IOriginator originator;
    private ArrayDeque<IMemento> undoMementos;
    private ArrayDeque<IMemento> redoMementos;

    public FourInLineCareTaker(IOriginator originator) {

        this.originator = originator;
        undoMementos = new ArrayDeque<>();
        redoMementos = new ArrayDeque<>();

    }

    public void setOriginator(IOriginator originator) {
        this.originator = originator;
    }

    public void saveMemento() {
        redoMementos.clear();
        try{
            undoMementos.push(originator.createMemento());
        } catch(IOException ex) {
            System.out.println("SaveMemento(Caretaker): " + ex);
            undoMementos.clear();
            redoMementos.clear();
        }
    }
    public  boolean replay(){

        if (undoMementos
                .isEmpty()) {
            return false;
        }
        try {

            IMemento previous = undoMementos.removeLast();
            originator.restoreMemento(previous);

        } catch(IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
            undoMementos.clear();
            redoMementos.clear();
        }
        return true;

    }

    public boolean undo(int nCredits) {
        if (undoMementos.isEmpty())
            return false;


        try {
            //IMemento current = originator.createMemento();
           // redoMementos.push(current);
            IMemento previous = null;
            for(int i = 0; i< nCredits;++i){
                if (!undoMementos.isEmpty())
                    previous = undoMementos.pop();
            }


            originator.restoreMemento(previous);

        } catch(IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
            undoMementos.clear();
            //redoMementos.clear();
        }
        return true;
    }


    /*
    public void redo() {
        if (redoMementos.isEmpty()) {
            return;
        }

        try {
            IMemento mementoParaRepor = redoMementos.pop();
            originator.restoreMemento(mementoParaRepor);

            IMemento current = originator.createMemento();
            undoMementos.push(current);
        } catch(IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
            undoMementos.clear();
            redoMementos.clear();
        }
    }
*/

}

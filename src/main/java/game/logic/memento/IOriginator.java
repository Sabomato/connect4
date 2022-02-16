package game.logic.memento;

import java.io.IOException;
import java.io.Serializable;


public interface IOriginator  extends Serializable {

    public IMemento createMemento() throws IOException;
    public void restoreMemento(IMemento memento) throws IOException, ClassNotFoundException;
}


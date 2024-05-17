package Helpers.Undo;

import java.util.LinkedList;
import java.util.List;
public class Stack<T> {
    private List<LogCommand> dataCollection;
    Stack() {
        dataCollection = new LinkedList<>();
    }
    public void push(LogCommand reverse) {
        dataCollection.add(dataCollection.size(), reverse);
    }
    public LogCommand pop() {
        if(dataCollection.size() > 0) {
            return dataCollection.remove(dataCollection.size()-1);
        }
        else {
            return null;
        }
    }
    public int size()
    {
    	    return dataCollection.size();
    }
    public void clear() {
        dataCollection.clear();
    }
}


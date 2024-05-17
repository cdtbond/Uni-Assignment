package Helpers.Undo;


import Data.HashMaps;


public class CommandManager {
    private static CommandManager instance = null;
    private Stack<HashMaps> stackNormal;
    private Stack<HashMaps> stackReverse;
    //like singleton
    static CommandManager getInstance(){
        if(instance != null) {
            return instance;
        }
        return new CommandManager();
    }
    public CommandManager() {
        stackNormal = new Stack<>();
        stackReverse = new Stack<>();
    }
    public void execute(LogCommand reverse){

        stackNormal.push(reverse);
    }

    public LogCommand undo() {
        LogCommand a = null;
	    if (stackNormal.size() > 0)
	    {   a = stackNormal.pop();
        stackReverse.push(a);
    }
	    return a;
}
    public LogCommand redo() {
    	LogCommand a = stackReverse.pop();
        if ( a != null)
        	{   
            stackNormal.push(a);
        }
        return a;
    }
    void clearNormal() {
        stackNormal.clear();
    }
    void clearReverse() {
       stackReverse.clear();
    }

    

}

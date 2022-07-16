package Board;

import Shapes2D.Shapes;
import java.util.ArrayList;
import java.util.Stack;

//singleton
public class ActionManager {

    private Stack<ArrayList<Shapes>> undo;
    private Stack<ArrayList<Shapes>> redo;
    private static final ActionManager manager = new ActionManager();

    private ActionManager() {
        undo = new Stack<>();
        redo = new Stack<>();
    }

    public static ActionManager getInstance() {
        return manager;
    }

    public void pop() {
        redo.push(undo.pop());
    }

    public ArrayList<Shapes> popRedo() {
        return redo.pop();
    }

    public void push(ArrayList<Shapes> e) {
        ArrayList<Shapes> x = new ArrayList<>();
        x.addAll(e);
        undo.push(x);
    }

    public ArrayList<Shapes> top() {
        return undo.peek();
    }

    public boolean isEmpty() {
        return undo.isEmpty();
    }

    public boolean isEmptyRedo() {
        return redo.isEmpty();
    }

    public int size() {
        return undo.size();
    }

}

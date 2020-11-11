package seedu.data;

import seedu.exceptions.EmptyDataStackException;

/**
 * Model keeps track of the runtime data,
 * it maintain a taskMap that contains updated tasks,
 * a currentStack that contains copies of taskMap from start to end of a session,
 * and an undoStack that contains copies of taskMap removed from currentStack.
 */
public class Model {
    private TaskMap taskMap;
    private DataStack currentStack;
    private DataStack undoStack;

    /**
     * Creates a new data model with taskMap.
     * @param taskMap taskMap with existing tasks.
     */
    public Model(TaskMap taskMap) {
        this.taskMap = taskMap;
        init();
    }

    /**
     * Initialise currentStack by adding the first copy of taskMap to the stack.
     * Initialise undoStack.
     */
    private void init() {
        currentStack = new DataStack();
        undoStack = new DataStack();
        currentStack.push(taskMap);
    }

    /**
     * Get currentStack that contains taskMaps.
     * @return currentStack.
     */
    public DataStack getDataStack() {
        return currentStack;
    }

    /**
     * Get a copy of updated taskMap.
     * @return copy of latest taskMap.
     */
    public TaskMap getTaskMap() {
        // new copy of taskMap
        return new TaskMap(taskMap.getValues());
    }
    
    /**
     * Get undoStack that contains taskMaps removed from currentStack.
     * @return undoStack
     */
    public DataStack getUndoStack() {
        return undoStack;
    }

    /**
     * Set the updated taskMap.
     * @param taskMap updated taskMap.
     */
    public void setTaskMap(TaskMap taskMap) {
        this.taskMap = taskMap;
    }

    /**
     * Add latest taskMap to currentStack and sets updated taskMap.
     * @param taskMap updated taskMap.
     */
    public void pushCurrentStackAndUpdate(TaskMap taskMap) {
        currentStack.push(taskMap);
        setTaskMap(taskMap);
    }
    
    /**
     * Get latest taskMap and remove it from currentStack, then sets updated taskMap.
     * @throws EmptyDataStackException when dataStack is empty.
     */
    public void popCurrentStackAndUpdate() throws EmptyDataStackException {
        undoStack.push(currentStack.pop());
        setTaskMap(currentStack.peek());
    }
    
    /**
     * Get latest taskMap and remove it from undoStack.
     * Add that taskMap to currentStack and sets updated taskMap.
     */
    public void popUndoStackAndUpdate() throws EmptyDataStackException {
        currentStack.push(undoStack.pop());
        setTaskMap(currentStack.peek());
    }
}
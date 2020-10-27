package seedu.data;

import seedu.exceptions.EmptyDataStackException;

public class Model {
    private TaskMap taskMap;
    private DataStack dataStack;

    public Model(TaskMap taskMap) {
        this.taskMap = taskMap;
        init();
    }

    private void init() {
        dataStack = new DataStack();
        dataStack.push(taskMap);
    }

    public DataStack getDataStack() {
        return dataStack;
    }

    public TaskMap getTaskMap() {
        // new copy of taskMap
        return new TaskMap(taskMap.getValues());
    }


    public void setTaskMap(TaskMap taskMap) {
        this.taskMap = taskMap;
    }

    public void pushAndUpdate(TaskMap taskMap) {
        dataStack.push(taskMap);
        setTaskMap(taskMap);
    }

    public void popAndUpdate() throws EmptyDataStackException {
        dataStack.pop();
        setTaskMap(dataStack.peek());
    }
}

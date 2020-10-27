package seedu.data;

import seedu.exceptions.EmptyDataStackException;
import seedu.task.Task;

import java.util.ArrayDeque;
import java.util.Deque;

public class DataStack {
    private Deque<TaskMap> stack = new ArrayDeque<>();

    public void push(TaskMap taskMap) {
        stack.addFirst(taskMap);
    }

    public TaskMap pop() throws EmptyDataStackException {
        if (stack.size() == 0) {
            throw new EmptyDataStackException();
        }
        return stack.removeFirst();
    }

    public TaskMap peek() {
        return stack.getFirst();
    }

    public int size() {
        return stack.size();
    }
}

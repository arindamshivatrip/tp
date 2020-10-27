package seedu.task;

import java.util.Comparator;

public class PrioritySorter implements Comparator<Task> {

    @Override
    public int compare(Task task, Task t1) {
        // descending order
        return t1.getPriority().compareTo(task.getPriority());
    }
}

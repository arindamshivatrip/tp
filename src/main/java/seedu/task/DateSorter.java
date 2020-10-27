package seedu.task;

import java.util.Comparator;

public class DateSorter implements Comparator<Task> {

    @Override
    public int compare(Task task, Task t1) {
        return task.getDate().compareTo(t1.getDate());
    }
}

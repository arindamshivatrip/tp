package seedu.commands;

import seedu.data.DataStack;
import seedu.data.Model;
import seedu.data.TaskMap;
import seedu.exceptions.InvalidDatetimeException;
import seedu.exceptions.InvalidPriorityException;
import seedu.exceptions.MaxNumTaskException;
import seedu.task.Task;

import java.util.regex.Pattern;

import static seedu.messages.Messages.ADD_MESSAGE;

public class Add extends ModificationCommand {
    public static final String COMMAND_WORD = "add";
    // Default date: day that the task is created, default priority: 0 (low to high: 0 - 2)
    public static final Pattern COMMAND_PATTERN = Pattern.compile(
            "^add (?<description>(\\w+\\s*)+\\w*)"
                    + "( d/(?<date>\\d{2}-\\d{2}-\\d{4}))?"
                    + "( st/(?<st>\\d{4}))?"
                    + "( et/(?<et>\\d{4}))?"
                    + "( p/(?<priority>\\d))?$");
    private final String description;
    private final String date;
    private final String startTime;
    private final String endTime;
    private final String priority;


    public Add(String description, String date, String startTime, String endTime, String priority) {
        this.description = description;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.priority = priority;
    }

    public CommandResult execute(Model model)
        throws InvalidPriorityException, InvalidDatetimeException, MaxNumTaskException {
        TaskMap tasks = model.getTaskMap();
        assert description != null;
        // Handle collision by generating new taskID if the value is in use.
        Task task = new Task(description, date, startTime, endTime, priority);
        Integer taskID = task.getTaskID();
        if (tasks.size() == TaskMap.MAX_NUM_TASKS) {
            throw new MaxNumTaskException();
        }
        assert tasks.size() != TaskMap.MAX_NUM_TASKS;
        // Finding an unused key
        while (tasks.get(taskID) != null) {
            taskID = (taskID + 1) % TaskMap.MAX_NUM_TASKS;
        }
        task.setTaskID(taskID);
        tasks.addTask(task);
        // update stack
        model.pushAndUpdate(tasks);
        return new CommandResult(ADD_MESSAGE);
    }
}

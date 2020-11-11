package seedu.commands;

import seedu.data.Model;
import seedu.data.TaskMap;
import seedu.exceptions.InvalidCommandException;
import seedu.exceptions.InvalidDatetimeException;
import seedu.exceptions.InvalidPriorityException;
import seedu.exceptions.InvalidReminderException;
import seedu.exceptions.MaxNumTaskException;
import seedu.task.Task;

import java.util.regex.Pattern;

import static seedu.messages.Messages.ADD_MESSAGE;

public class AddCommand extends ModificationCommand {
    public static final String COMMAND_WORD = "add";
    // Default date: day that the task is created, default priority: 0 (low to high: 0 - 2)
    public static final Pattern COMMAND_PATTERN = Pattern.compile(
            "^(?<description>(\\w+\\s*)+\\w*)"
                    + "( d/(?<date>\\d{2}-\\d{2}-\\d{4}))?"
                    + "( st/(?<st>\\d{4}))?"
                    + "( et/(?<et>\\d{4}))?"
                    + "( p/(?<priority>\\d))?"
                    + "( r/(?<reminder>\\w+\\s*))?"
                    + "( t-(?<t>\\d{4}))?$");

    private final String description;
    private final String date;
    private final String startTime;
    private final String endTime;
    private final String priority;
    private final String reminder;
    private final String reminderTime;

    /**
     * Constructor.
     *
     * @param description  name/description of task
     * @param date         date of task being created
     * @param startTime    start time of task.
     * @param endTime      end time of task.
     * @param priority     1,2 or 3.(low/med/high)
     * @param reminder     remind on/off
     * @param reminderTime time set for reminder
     * @throws InvalidCommandException throws this when start time is at a later timing in the day than end time
     */
    public AddCommand(String description, String date, String startTime, String endTime, String priority,
                      String reminder, String reminderTime) throws InvalidCommandException {
        this.description = description;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.priority = priority;
        this.reminder = reminder;
        this.reminderTime = reminderTime;

        if (startTime != null && endTime != null) {
            if (Integer.parseInt(startTime) >= Integer.parseInt(endTime)) {
                throw new InvalidCommandException();
            }
        }
    }

    /**
     * Adds a task. Updates the model with new list of tasks.
     *
     * @param model Contains the TaskMap and stack(for the undo function)
     * @return the CommandResult object, which shows the task added message.
     * @throws InvalidPriorityException if priority given is not 1,2 or 3.
     * @throws InvalidDatetimeException if wrong date or time format
     * @throws MaxNumTaskException      if tasks size == 10000
     * @throws InvalidReminderException if wrong reminder format
     */
    public CommandResult execute(Model model)
            throws InvalidPriorityException, InvalidDatetimeException, MaxNumTaskException, InvalidReminderException {
        TaskMap tasks = model.getTaskMap();
        assert description != null;
        // Handle collision by generating new taskID if the value is in use.
        Task task = new Task(description, date, startTime, endTime, priority, reminder, reminderTime);
        Integer taskID = task.getTaskID();
        if (tasks.size() == TaskMap.MAX_NUM_TASKS) {
            throw new MaxNumTaskException();
        }
        assert tasks.size() != TaskMap.MAX_NUM_TASKS;
        // Finding an unused key
        while (tasks.get(taskID) != null) {
            taskID = (taskID + 1) % TaskMap.MAX_NUM_TASKS;
        }
        task.reminder.startReminder(task);
        task.setTaskID(taskID);
        tasks.addTask(task);
        // update stack
        model.pushCurrentStackAndUpdate(tasks);
        return new CommandResult(ADD_MESSAGE, task);
    }
}

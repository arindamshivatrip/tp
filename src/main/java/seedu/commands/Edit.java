package seedu.commands;

import seedu.data.Model;
import seedu.data.TaskMap;
import seedu.exceptions.InvalidCommandException;
import seedu.exceptions.InvalidDatetimeException;
import seedu.exceptions.InvalidPriorityException;
import seedu.exceptions.InvalidTaskNumberException;
import seedu.task.Task;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static seedu.messages.Messages.EDIT_MESSAGE;

public class Edit extends ModificationCommand {
    public static final String COMMAND_WORD = "edit";
    public static final Pattern COMMAND_PATTERN = Pattern.compile(
            "^edit (?<key>\\d+)"
                    + "( des/(?<description>(\\w+\\s*)+\\w*))?"
                    + "( d/(?<date>\\d{2}-\\d{2}-\\d{4}))?"
                    + "( st/(?<st>\\d{4}))?"
                    + "( et/(?<et>\\d{4}))?"
                    + "( p/(?<priority>\\d))?$");
    private final Integer key;
    private final String description;
    private final String date;
    private final String startTime;
    private final String endTime;
    private final String priority;

    public Edit(String keyString, String description, String date, String startTime, String endTime, String priority)
            throws InvalidTaskNumberException {
        try {
            key = Integer.parseInt(keyString);
        } catch (NumberFormatException e) {
            throw new InvalidTaskNumberException();
        }
        this.description = description;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.priority = priority;
    }

    public CommandResult execute(Model model)
        throws InvalidTaskNumberException, InvalidPriorityException, InvalidDatetimeException {
        TaskMap tasks = model.getTaskMap();
        Task task = tasks.get(key);
        if (task == null) {
            throw new InvalidTaskNumberException();
        }

        Task editedTask = new Task(key, task.getDescription(), task.getDate(), task.getStartTime(),
            task.getEndTime(), task.getPriority());

        // Set field
        if (description != null) {
            editedTask.setDescription(description);
        }
        if (date != null) {
            editedTask.setDate(date);
        }
        if (startTime != null) {
            editedTask.setStartTime(startTime);
        }
        if (endTime != null) {
            editedTask.setEndTime(endTime);
        }
        if (priority != null) {
            editedTask.setPriority(priority);
        }
        tasks.delete(key);
        tasks.addTask(editedTask);
        model.pushAndUpdate(tasks);
        return new CommandResult(EDIT_MESSAGE);
    }
}

package seedu.commands;

import seedu.data.Model;
import seedu.data.TaskMap;
import seedu.exceptions.InvalidCommandException;
import seedu.exceptions.InvalidDatetimeException;
import seedu.exceptions.InvalidPriorityException;
import seedu.exceptions.InvalidReminderException;
import seedu.exceptions.InvalidTaskNumberException;
import seedu.task.Task;

import java.util.regex.Pattern;

import static seedu.messages.Messages.EDIT_MESSAGE;

public class EditCommand extends ModificationCommand {
    public static final String COMMAND_WORD = "edit";
    public static final Pattern COMMAND_PATTERN = Pattern.compile(
            "^(?<key>\\d+)"
                    + "( des/(?<description>(\\w+\\s*)+\\w*))?"
                    + "( d/(?<date>\\d{2}-\\d{2}-\\d{4}))?"
                    + "( st/(?<st>\\d{4}))?"
                    + "( et/(?<et>\\d{4}))?"
                    + "( p/(?<priority>\\d))?"
                    + "( r/(?<reminder>\\w+\\s*))?"
                    + "( t-(?<t>\\d{4}))?$");
    private final Integer key;
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
     * @param keyString    the index of the task being edited.
     * @param description  the description of the task being edited.
     * @param date         the date of the task being edited.
     * @param startTime    the start time of the task being edited.
     * @param endTime      the end time of the task being edited.
     * @param priority     the priority of the task being edited. (1,2 or 3)
     * @param reminder     the reminder being set to on/off
     * @param reminderTime the reminder time being set
     * @throws InvalidTaskNumberException When index is not a integer.
     * @throws InvalidCommandException    When the start time is more than end time(invalid format).
     */
    public EditCommand(String keyString, String description, String date, String startTime, String endTime,
                       String priority, String reminder, String reminderTime)
            throws InvalidTaskNumberException, InvalidCommandException {
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
        this.reminder = reminder;
        this.reminderTime = reminderTime;

        if (startTime != null && endTime != null) {
            if (Integer.parseInt(startTime) >= Integer.parseInt(endTime)) {
                throw new InvalidCommandException();
            }
        }
    }

    /**
     * Changed the required field of the task.
     *
     * @param model Contains TaskMap and stack(for undo function)
     * @return CommandResult object with the task edited message.
     * @throws InvalidTaskNumberException If the task at the index is not found. (Task has not been created)
     * @throws InvalidPriorityException   Priority is not 1,2 or 3.
     * @throws InvalidDatetimeException   Date/time is not in desired format. (eg 2500 or 1236 or abcd).
     * @throws InvalidReminderException   If wrong reminder format.
     */
    public CommandResult execute(Model model)
            throws InvalidTaskNumberException, InvalidPriorityException,
            InvalidDatetimeException, InvalidReminderException {
        TaskMap tasks = model.getTaskMap();
        Task task = tasks.get(key);
        if (task == null) {
            throw new InvalidTaskNumberException();
        }

        Task editedTask = new Task(key, task.getDescription(), task.getDate(), task.getStartTime(),
                task.getEndTime(), task.getPriority(), task.newReminder());

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
        if (reminder != null) {
            editedTask.setReminder(reminder, reminderTime);
        }
        editedTask.reminder.startReminder(editedTask);
        tasks.delete(key);
        tasks.addTask(editedTask);
        model.pushCurrentStackAndUpdate(tasks);
        return new CommandResult(EDIT_MESSAGE, editedTask);
    }
}

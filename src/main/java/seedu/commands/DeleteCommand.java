package seedu.commands;

import seedu.data.Model;
import seedu.data.TaskMap;
import seedu.exceptions.InvalidCommandException;
import seedu.exceptions.InvalidTaskNumberException;
import seedu.task.Task;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static seedu.messages.Messages.DELETE_MESSAGE;

public class DeleteCommand extends ModificationCommand {
    public static final String COMMAND_WORD = "delete";
    private final Integer key;
    public static final Pattern COMMAND_PATTERN = Pattern.compile(
            "^(?<key>\\d+)$");

    /**
     * Constructor.
     * @param keyString the Index of the task being deleted.
     * @throws InvalidTaskNumberException When something other than an int is entered after the delete command.
     */
    public DeleteCommand(String keyString) throws InvalidTaskNumberException {
        try {
            key = Integer.parseInt(keyString);
        } catch (NumberFormatException e) {
            throw new InvalidTaskNumberException();
        }
    }

    /**
     * Deletes the task, updates the model with new list of tasks.
     * @param model Contains the TaskMap and stack(for the undo function)
     * @return the CommandResult object, which shows the task deleted message.
     * @throws InvalidTaskNumberException if the Index the user entered was invalid, AKA task does not exist.
     */
    public CommandResult execute(Model model) throws InvalidTaskNumberException {
        TaskMap tasks = model.getTaskMap();
        Task task = tasks.get(key);
        if (task == null) {
            throw new InvalidTaskNumberException();
        }
        Task taskDeleted = task;
        tasks.delete(key);
        model.pushCurrentStackAndUpdate(tasks);
        return new CommandResult(DELETE_MESSAGE,taskDeleted);
    }
}

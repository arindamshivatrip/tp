package seedu.commands;

import seedu.data.Model;
import seedu.data.TaskMap;
import seedu.exceptions.InvalidCommandException;
import seedu.exceptions.InvalidTaskNumberException;
import seedu.task.Task;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static seedu.messages.Messages.DELETE_MESSAGE;

public class Delete extends ModificationCommand {
    public static final String COMMAND_WORD = "delete";
    private final Integer key;
    public static final Pattern COMMAND_PATTERN = Pattern.compile(
            "^delete (?<key>\\d+)$");

    public Delete(String keyString) throws InvalidTaskNumberException {
        try {
            key = Integer.parseInt(keyString);
        } catch (NumberFormatException e) {
            throw new InvalidTaskNumberException();
        }
    }

    public CommandResult execute(Model model) throws InvalidTaskNumberException {
        TaskMap tasks = model.getTaskMap();
        Task task = tasks.get(key);
        if (task == null) {
            throw new InvalidTaskNumberException();
        }
        tasks.delete(key);
        model.pushAndUpdate(tasks);
        return new CommandResult(DELETE_MESSAGE);
    }
}

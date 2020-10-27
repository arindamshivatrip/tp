package seedu.commands;

import seedu.data.Model;
import seedu.data.TaskMap;
import seedu.exceptions.EmptyDataStackException;
import seedu.exceptions.InvalidDatetimeException;
import seedu.exceptions.InvalidPriorityException;
import seedu.exceptions.InvalidTaskNumberException;
import seedu.exceptions.MaxNumTaskException;

public class Command {
    public CommandResult execute() {
        return new CommandResult("");
    }

    public CommandResult execute(TaskMap taskMap) {
        return new CommandResult("");
    }

    public CommandResult execute(Model model) throws InvalidPriorityException, InvalidDatetimeException,
        MaxNumTaskException, InvalidTaskNumberException, EmptyDataStackException {
        return new CommandResult("");
    }
}

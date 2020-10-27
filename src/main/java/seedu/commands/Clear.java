package seedu.commands;

import seedu.data.Model;
import seedu.data.TaskMap;

import static seedu.messages.Messages.CLEAR_MESSAGE;

public class Clear extends ModificationCommand {
    public static final String COMMAND_WORD = "clear";

    public CommandResult execute(Model model) {
        TaskMap tasks = model.getTaskMap();
        tasks.clear();
        model.pushAndUpdate(tasks);
        return new CommandResult(CLEAR_MESSAGE);
    }
}

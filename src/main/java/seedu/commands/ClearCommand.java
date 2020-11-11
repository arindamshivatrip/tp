package seedu.commands;

import seedu.data.Model;
import seedu.data.TaskMap;
import seedu.data.Timers;


import static seedu.messages.Messages.CLEAR_MESSAGE;

public class ClearCommand extends ModificationCommand {
    public static final String COMMAND_WORD = "clear";

    /**
     * Clears the current list of all tasks and updates the stack.
     * @param model contains TaskMap and stack.
     * @return CommandResult object with the clear message.
     */
    public CommandResult execute(Model model) {
        TaskMap tasks = model.getTaskMap();
        tasks.clear();
        Timers.cancel();
        model.pushCurrentStackAndUpdate(tasks);
        return new CommandResult(CLEAR_MESSAGE);
    }
}

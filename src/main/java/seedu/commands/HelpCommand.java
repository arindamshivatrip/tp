package seedu.commands;

import seedu.data.Model;
import seedu.data.TaskMap;

import static seedu.messages.Messages.HELP_MESSAGE;

public class HelpCommand extends GeneralCommand {
    public static final String COMMAND_WORD = "help";

    /**
     * Prints list of commands for user.
     *
     * @return CommandResult object with the help message.
     */
    public CommandResult execute() {
        return new CommandResult(HELP_MESSAGE);
    }
}

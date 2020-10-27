package seedu.commands;

import seedu.data.Model;
import seedu.data.TaskMap;

import static seedu.messages.Messages.HELP_MESSAGE;

public class Help extends GeneralCommand {
    public static final String COMMAND_WORD = "help";

    public CommandResult execute() {
        return new CommandResult(HELP_MESSAGE);
    }
}

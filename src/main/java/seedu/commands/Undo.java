package seedu.commands;

import seedu.data.DataStack;
import seedu.data.Model;
import seedu.exceptions.EmptyDataStackException;
import seedu.messages.Messages;
import seedu.ui.Ui;


public class Undo extends ModificationCommand {
    // Currently unable to redo undo
    public static final String COMMAND_WORD = "undo";


    public CommandResult execute(Model model) throws EmptyDataStackException {
        DataStack dataStack = model.getDataStack();
        if (dataStack.size() == 1) {
            return new CommandResult(Messages.NO_PREV_MODIFICATION);
        } else {
            model.popAndUpdate();
            return new CommandResult(Messages.UNDO_MESSAGE);
        }
    }
}

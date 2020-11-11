package seedu.commands;

import seedu.data.DataStack;
import seedu.data.Model;
import seedu.exceptions.EmptyDataStackException;
import seedu.messages.Messages;
import seedu.ui.Ui;


public class UndoCommand extends ModificationCommand {
    public static final String COMMAND_WORD = "undo";

    /**
     * Pops the latest version of the TaskMap from the stack
     * Updates the current list of tasks to the previous version.
     *
     * @param model Contains TaskMap and stack.
     * @return A CommandResult, depending on whether there was an undo, with the relevant message.
     * @throws EmptyDataStackException if stack is empty.
     */
    public CommandResult execute(Model model) throws EmptyDataStackException {
        DataStack dataStack = model.getDataStack();
        if (dataStack.size() == 1) {
            return new CommandResult(Messages.NO_PREV_MODIFICATION);
        } else {
            model.popCurrentStackAndUpdate();
            return new CommandResult(Messages.UNDO_MESSAGE);
        }
    }
}

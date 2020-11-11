package seedu.commands;

import seedu.data.DataStack;
import seedu.data.Model;
import seedu.exceptions.EmptyDataStackException;
import seedu.messages.Messages;
import seedu.ui.Ui;


public class RedoCommand extends ModificationCommand {
    public static final String COMMAND_WORD = "redo";

    /**
     * Pops the latest version of the TaskMap from the undo stack
     * and push it into the data stack.
     * Updates the current list of tasks to the previous version.
     *
     * @param model Contains TaskMap and stack.
     * @return A CommandResult, depending on whether there was an undo.
     * @throws EmptyDataStackException if stack is empty.
     */
    public CommandResult execute(Model model) throws EmptyDataStackException {
        DataStack dataStack = model.getUndoStack();
        if (dataStack.size() == 0) {
            return new CommandResult(Messages.NO_PREV_MODIFICATION_REDO);
        } else {
            model.popUndoStackAndUpdate();
            return new CommandResult(Messages.REDO_MESSAGE);
        }
    }
}


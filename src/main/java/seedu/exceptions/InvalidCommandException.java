package seedu.exceptions;

import seedu.messages.Messages;

public class InvalidCommandException extends Exception {
    @Override
    public String toString() {
        return Messages.INVALID_COMMAND;
    }
}

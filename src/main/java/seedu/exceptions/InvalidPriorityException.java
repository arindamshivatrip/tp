package seedu.exceptions;

import seedu.messages.Messages;

public class InvalidPriorityException extends Exception {
    @Override
    public String toString() {
        return Messages.INVALID_PRIORITY;
    }
}

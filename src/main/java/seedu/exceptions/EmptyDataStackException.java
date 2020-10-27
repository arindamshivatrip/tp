package seedu.exceptions;

import seedu.messages.Messages;

public class EmptyDataStackException extends Exception {
    @Override
    public String toString() {
        return Messages.EMPTY_DATA_STACK;
    }
}

package seedu.exceptions;

import seedu.messages.Messages;

public class InvalidDatetimeException extends Exception {
    @Override
    public String toString() {
        return Messages.INVALID_DATETIME;
    }
}

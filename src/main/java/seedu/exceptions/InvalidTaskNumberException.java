package seedu.exceptions;

import seedu.messages.Messages;

public class InvalidTaskNumberException extends Exception {
    @Override
    public String toString() {
        return Messages.INVALID_TASK_NUMBER;
    }
}

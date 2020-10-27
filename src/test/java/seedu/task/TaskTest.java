package seedu.task;

import org.junit.jupiter.api.Test;
import seedu.exceptions.InvalidDatetimeException;
import seedu.exceptions.InvalidPriorityException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.messages.Messages.INVALID_DATETIME;
import static seedu.messages.Messages.INVALID_PRIORITY;

class TaskTest {

    @Test
    void initialiseTask_differentPriority_success() throws InvalidDatetimeException {

        try {
            new Task("p1", null,
                    null, null, "1");
            new Task("p2", null,
                null, null, "2");
            new Task("p3", null,
                null, null, "3");
        } catch (InvalidPriorityException e) {
            fail();
        }
    }

    @Test
    void initialise_task_invalidPriorityExceptionThrown() throws InvalidDatetimeException {

        try {
            new Task("p0",
                null, null, null, "0");
        } catch (InvalidPriorityException e) {
            assertEquals(INVALID_PRIORITY, e.toString());
        }

        try {
            new Task("p4",
                null, null, null, "4");
        } catch (InvalidPriorityException e) {
            assertEquals(INVALID_PRIORITY, e.toString());
        }
    }

    @Test
    void initialiseTask__datetime_success() throws InvalidPriorityException {

        try {
            new Task("dt1", "20-10-2020",
                null, null, null);
            new Task("dt2", null,
                "0000", null, null);
            new Task("dt3", null,
                null, "2359", null);
        } catch (InvalidDatetimeException e) {
            fail();
        }
    }

    @Test
    void initialise_task_invalidDatetimeExceptionThrown() throws InvalidPriorityException {

        try {
            new Task("date_fail",
                "20-20-2020", null, null, null);
        } catch (InvalidDatetimeException e) {
            assertEquals(INVALID_DATETIME, e.toString());
        }

        try {
            new Task("time_fail",
                null, "3000", null, null);
        } catch (InvalidDatetimeException e) {
            assertEquals(INVALID_DATETIME, e.toString());
        }
    }


}
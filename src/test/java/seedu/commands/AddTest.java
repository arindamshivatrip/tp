package seedu.commands;

import org.junit.jupiter.api.Test;
import seedu.data.Model;
import seedu.data.TaskMap;
import seedu.exceptions.InvalidCommandException;
import seedu.exceptions.InvalidDatetimeException;
import seedu.exceptions.InvalidPriorityException;
import seedu.exceptions.MaxNumTaskException;
import seedu.exceptions.InvalidTaskNumberException;
import seedu.exceptions.UnknowCommandException;
import seedu.exceptions.EmptyDataStackException;
import seedu.parser.Parser;
import seedu.task.Task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.messages.Messages.ADD_MESSAGE;

class AddTest {

    Parser parser = new Parser();

    @Test
    public void constructor_noRawInput_throws_InvalidCommandException() {
        assertThrows(InvalidCommandException.class, () -> parser.processRaw("add" + ""));
    }

    @Test
    public void addCommand_wrongStartTimeFormat() {
        final String[] invalidInputs = {"", " ", "1231242345", "&*^%*&^", "abc"};
        for (int i = 0; i < invalidInputs.length; i++) {
            int finalI = i;
            assertThrows(InvalidCommandException.class, () ->
                    parser.processRaw("add task1 st/" + invalidInputs[finalI]));
        }
    }

    @Test
    public void addCommand_wrongEndTimeFormat() {
        final String[] invalidInputs = {"", " ", "1231242345", "&*^%*&^", "abc"};
        for (int i = 0; i < invalidInputs.length; i++) {
            int finalI = i;
            assertThrows(InvalidCommandException.class, () ->
                    parser.processRaw("add task1 et/" + invalidInputs[finalI]));
        }
    }

    @Test
    public void addCommand_wrongDateFormat() {
        final String[] invalidInputs = {"", " ", "1231242345", "&*^%*&^", "abc"};
        for (int i = 0; i < invalidInputs.length; i++) {
            int finalI = i;
            assertThrows(InvalidCommandException.class, () ->
                    parser.processRaw("add task1 d/" + invalidInputs[finalI]));
        }
    }

    @Test
    public void addCommand_wrongPriorityFormat() {
        final String[] invalidInputs = {"", " ", "1231242345", "&*^%*&^", "abc"};
        for (int i = 0; i < invalidInputs.length; i++) {
            int finalI = i;
            assertThrows(InvalidCommandException.class, () ->
                    parser.processRaw("add task1 p/" + invalidInputs[finalI]));
        }
    }

    @Test
    public void addCommand_executeSuccess() throws InvalidCommandException, InvalidDatetimeException,
            InvalidPriorityException, MaxNumTaskException, InvalidTaskNumberException,
            UnknowCommandException,EmptyDataStackException {
        TaskMap taskMap = new TaskMap();
        Model model = new Model(taskMap);
        Command add = parser.processRaw("add newTask");
        CommandResult result = add.execute(model);
        assertEquals(1, model.getTaskMap().size());
    }

}
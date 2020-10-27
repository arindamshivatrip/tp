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
import seedu.task.Task;
import seedu.parser.Parser;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DeleteTest {

    private Task t1;
    private Task t2;
    private Task t3;
    private Task t4;
    private Task t5;
    Parser parser = new Parser();

    void setup() {
        try {
            t1 = new Task("meeting", "20-10-2020", null, null, null);
            t2 = new Task("meeting", "20-10-2020", null, null, "2");
            t3 = new Task("reading", "17-10-2020", null, null, "2");
            t4 = new Task("reading", "24-10-2020", "2200", "2300", null);
            t5 = new Task("random", "15-10-2020", null, null, "3");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void constructor_noRawInput_throws_InvalidCommandException() {
        assertThrows(InvalidCommandException.class, () -> parser.processRaw("delete" + ""));
    }

    @Test
    public void deleteCommand_invalidData() {
        final String[] invalidInputs = {"", " ", "&*^%*&^", "abc"};
        for (int i = 0; i < invalidInputs.length; i++) {
            int finalI = i;
            assertThrows(InvalidCommandException.class, () -> parser.processRaw("delete " + invalidInputs[finalI]));
        }
    }

    @Test
    public void deleteCommand_executeTest() throws InvalidTaskNumberException, InvalidCommandException,
            MaxNumTaskException, InvalidDatetimeException, InvalidPriorityException, EmptyDataStackException,
            UnknowCommandException {
        setup();
        TaskMap taskMap = new TaskMap();
        taskMap.addTask(t1);
        taskMap.addTask(t2);
        taskMap.addTask(t3);
        taskMap.addTask(t4);
        taskMap.addTask(t5);
        Model model = new Model(taskMap);
        int id1 = t1.getTaskID();

        Command delete = parser.processRaw("delete " + id1);
        CommandResult result = delete.execute(model);
        assertEquals(4, model.getTaskMap().size());

    }

    @Test
    public void deleteCommand_invalidTaskNumber() throws
            InvalidTaskNumberException, InvalidCommandException, UnknowCommandException {
        setup();
        TaskMap taskMap = new TaskMap();
        taskMap.addTask(t3);

        Random random = new Random();
        int wrongId3 = 3217;
        while (taskMap.get(wrongId3) != null) {
            wrongId3 = random.nextInt(10000);
        }
        Model model = new Model(taskMap);

        Command delete = parser.processRaw("delete " + wrongId3);
        assertThrows(InvalidTaskNumberException.class, () -> delete.execute(model));

    }


}
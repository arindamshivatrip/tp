package seedu.commands;

import seedu.data.TaskMap;
import seedu.ui.DisplayMode;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;
import java.util.zip.CheckedOutputStream;

import static seedu.messages.Messages.LIST_MESSAGE;

public class List extends ReadOnlyCommand {
    public static final String COMMAND_WORD = "list";

    public static final Pattern COMMAND_PATTERN = Pattern.compile(
        "^list(?<dateFlag> -d)?"
                + "(?<priorityFlag> -p)?"
                + "(?<displayByWeek> -w)?"
                + "(?<displayByMonth> -m)?"
                + "( d/(?<date>\\d{2}-\\d{2}-\\d{4}))?$");

    private final boolean dateFlag;
    private final boolean priorityFlag;
    private final boolean displayByWeek;
    private final boolean displayByMonth;
    private final String date;


    public List(boolean dateFlag, boolean priorityFlag, boolean displayByWeek, boolean displayByMonth, String date) {
        this.dateFlag = dateFlag;
        this.priorityFlag = priorityFlag;
        this.displayByWeek = displayByWeek;
        this.displayByMonth = displayByMonth;
        this.date = date;
    }

    public CommandResult execute(TaskMap tasks) {
        assert !(dateFlag && priorityFlag);

        // TODO Check flag condition

        if (dateFlag) {
            return new CommandResult(LIST_MESSAGE, tasks.sortListByDate());
        } else if (priorityFlag) {
            return new CommandResult(LIST_MESSAGE, tasks.sortListByPriority());
        }

        CommandResult result = new CommandResult(LIST_MESSAGE, tasks);

        if (date != null) {
            LocalDate tempDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            result.setDate(tempDate);
            result.setDisplayMode(DisplayMode.DAY);
        } else if (displayByWeek) {
            result.setDisplayMode(DisplayMode.WEEK);
        } else if (displayByMonth) {
            result.setDisplayMode(DisplayMode.MONTH);
        }

        return result;
    }
}

package seedu.ui;

import seedu.commons.Util;
import seedu.data.TaskMap;
import seedu.task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

import static seedu.messages.Messages.LS;

public class DayStructure extends DisplayDateStructure {

    public DayStructure(LocalDate date) {
        super(date);
    }

    @Override
    protected void generateContent(TaskMap tasks) {
        StringBuilder stringBuilder = new StringBuilder();

        String headerFormat = "  | %-10s | %-20s | %-15s | %-10s | %-10s | %-11s |" + LS;
        String contentFormat = "  | %-10s | %-20s | %-15s | %-10s | %-10s | %-20s |" + LS;

        stringBuilder.append(LS)
                .append(currentDate.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy")))
                .append(" ")
                .append(currentDate.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.ENGLISH))
                .append(":")
                .append(LS)
                .append("   ")
                .append(Util.generatePadStringWithCharAndLength('_', 93))
                .append(LS)
                .append(String.format(headerFormat, "Index", "Description", "Date", "Start", "End", "Priority"))
                .append("   ").append(Util.generatePadStringWithCharAndLength('-', 93))
                .append(LS);

        TaskMap filteredTasks = tasks.searchByDate(currentDate);
        if (filteredTasks.size() == 0) {
            stringBuilder.append("  |")
                    .append(Util.generatePadStringWithCharAndLength(' ', 93))
                    .append("|")
                    .append(LS);
        } else {
            for (Task task : filteredTasks.getValues()) {
                stringBuilder.append(String.format(contentFormat,
                    "#" + task.getTaskID(),
                    Util.limitStringWithDots(task.getDescription(), 20),
                    task.getDate(),
                    task.getStartTime() == null ? "" : task.getStartTime(),
                    task.getEndTime() == null ? "" : task.getEndTime(),
                    task.getPriority()));
            }
        }
        stringBuilder.append("   ")
                .append(Util.generatePadStringWithCharAndLength('-', 93))
                .append(LS);

        content = stringBuilder.toString();
    }

    public void increment() {
        setCurrentDate(currentDate.plusDays(1));
    }

    public void decrement() {
        setCurrentDate(currentDate.minusDays(1));
    }
}

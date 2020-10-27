package seedu.commands;

import seedu.data.TaskMap;
import seedu.ui.DisplayMode;

import java.time.LocalDate;

public class CommandResult {
    private final String message;

    private TaskMap tasks = null;
    private DisplayMode displayMode = DisplayMode.ALL;
    private boolean isExit = false;
    private LocalDate date;

    public CommandResult(String message) {
        this.message = message;
    }

    public CommandResult(String message, TaskMap tasks) {
        this.message = message;
        this.tasks = tasks;
    }

    public String getMessage() {
        return message;
    }

    public TaskMap getTasks() {
        return tasks;
    }

    public DisplayMode getDisplayMode() {
        return displayMode;
    }

    public boolean isExit() {
        return isExit;
    }

    public void setExit(boolean exit) {
        isExit = exit;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setTasks(TaskMap tasks) {
        this.tasks = tasks;
    }

    public void setDisplayMode(DisplayMode displayMode) {
        this.displayMode = displayMode;
    }
}

package seedu.ui;

import seedu.commands.CommandResult;
import seedu.commons.Util;
import seedu.data.TaskMap;
import seedu.task.Task;

import java.io.InputStream;
import java.io.PrintStream;

import java.time.LocalDate;
import java.util.Scanner;

import static seedu.messages.Messages.LS;
import static seedu.messages.Messages.WELCOME_MESSAGE;

public class Ui {
    private final Scanner in;
    private final PrintStream out;
    private DisplayDateStructure displayDateStructure;

    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public String getUserInput() {
        return in.nextLine();
    }

    private void displayAll(TaskMap tasks) {
        // Basic adding sequence
        assert tasks != null : "null tasks";
        displayTasks(tasks);
    }

    private void displayByDateStructure(TaskMap tasks) {
        displayDateStructure.generateContent(tasks);
        out.println(displayDateStructure.getContent());
        out.println("q:quit, w:previous, e:next");
        while (in.hasNext()) {
            String input = in.next();
            if (input.length() != 1 && !"qwe".contains(input.toLowerCase())) {
                out.println("Invalid input.");
            }
            char charIn = input.toLowerCase().charAt(0);
            if (charIn == 'q') {
                out.println("You've exited the display mode.");
                in.nextLine();
                break;
            } else if (charIn == 'w') {
                displayDateStructure.decrement();
            } else if (charIn == 'e') {
                displayDateStructure.increment();
            } else {
                assert false;
            }
            displayDateStructure.generateContent(tasks);
            out.println(displayDateStructure.getContent());
            out.println("q:Quit, w:previous, e:next");
        }
    }

    public void displayTasks(TaskMap tasks) {
        // Header
        String headerFormat = "  | %-10s | %-20s | %-15s | %-10s | %-10s | %-11s |" + LS;
        String contentFormat = "  | %-10s | %-20s | %-15s | %-10s | %-10s | %-20s |" + LS;
        out.println("   " + Util.generatePadStringWithCharAndLength('_', 93));
        out.format(headerFormat, "Index", "Description", "Date", "Start", "End", "Priority");
        out.println("   " + Util.generatePadStringWithCharAndLength('-', 93));

        if (tasks.size() == 0) {
            out.println("  |" + Util.generatePadStringWithCharAndLength(' ', 93) + "|");
        } else {
            for (Task task : tasks.getValues()) {
                out.format(contentFormat,
                    "#" + task.getTaskID(),
                    Util.limitStringWithDots(task.getDescription(), 20),
                    task.getDate(),
                    task.getStartTime() == null ? "" : task.getStartTime(),
                    task.getEndTime() == null ? "" : task.getEndTime(),
                    task.getPriority());
            }
        }

        out.println("   " + Util.generatePadStringWithCharAndLength('-', 93));
        out.println();
    }

    public void showWelcomeMessage(TaskMap tasks) {
        showMessage(WELCOME_MESSAGE);
        if (tasks != null) {
            showReminders(tasks);
        }
    }

    public void showReminders(TaskMap tasks) {
        assert tasks != null : "null tasks";
        TaskMap tasksDueToday = tasks.searchByDate(LocalDate.now());
        String messageFormat = "%-15s%-30s%15s" + LS;
        String taskFormat = "%-15s%-6s%-18s%-6s%15s" + LS;
        out.println("||" + Util.generatePadStringWithCharAndLength(' ', 56) + "||");
        out.format(messageFormat, "||", "You have " + tasksDueToday.size() + " task(s) due today.", "||");
        for (Task task : tasksDueToday.getValues()) {
            out.format(taskFormat,
                "||",
                "#" + task.getTaskID() + " ",
                Util.limitStringWithDots(task.getDescription(), 17),
                (task.getStartTime() == null ? "" : task.getStartTime()),
                "||");
        }
        out.println("||" + Util.generatePadStringWithCharAndLength(' ', 56) + "||");

        TaskMap tasksDueTomorrow = tasks.searchByDate(LocalDate.now().plusDays(1));
        out.format(messageFormat, "||", "Upcoming tasks tomorrow:", "||");
        for (Task task : tasksDueTomorrow.getValues()) {
            out.format(taskFormat,
                "||",
                "#" + task.getTaskID() + " ",
                Util.limitStringWithDots(task.getDescription(), 17),
                (task.getStartTime() == null ? "" : task.getStartTime()),
                "||");
        }
        out.println("||" + Util.generatePadStringWithCharAndLength(' ',56) + "||" + LS
                + " " + Util.generatePadStringWithCharAndLength('=', 58) + " " + LS);
    }

    public void showMessage(String message) {
        out.println(message);
    }

    public void showException(Exception e) {
        out.println(e);
    }

    public boolean interpretCommandResult(CommandResult result) {
        assert result.getMessage() != null : "null message";
        showCommandResult(result);
        return result.isExit();
    }

    public void displayTasksForTesting(TaskMap taskMap) {
        // To be deleted later
        displayAll(taskMap);
    }

    private void showCommandResult(CommandResult result) {
        showMessage(result.getMessage());
        if (result.getTasks() != null) {
            if (result.getDisplayMode() == DisplayMode.ALL) {
                displayAll(result.getTasks());
            } else {
                if (result.getDisplayMode() == DisplayMode.DAY) {
                    displayDateStructure = new DayStructure(result.getDate());
                } else if (result.getDisplayMode() == DisplayMode.WEEK) {
                    // Weekly view
                    displayDateStructure = new WeekStructure();
                } else if (result.getDisplayMode() == DisplayMode.MONTH) {
                    // Monthly view
                    displayDateStructure = new MonthStructure();
                }
                displayByDateStructure(result.getTasks());
            }
        }
    }
}

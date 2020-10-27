package seedu.task;

import seedu.exceptions.InvalidDatetimeException;
import seedu.exceptions.InvalidPriorityException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static java.lang.Math.pow;


public class Task {
    // MAX_NUM_TASKS = 10000
    private static final int HASH_VALUE_DIGITS = 4;
    private Integer taskID;
    private String description;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private Priority priority;

    public Task(String description, String dateString, String startTime, String endTime, String priorityString)
        throws InvalidPriorityException, InvalidDatetimeException {
        this.description = description;
        date = dateStringToDate(dateString);
        this.startTime = timeStringToTime(startTime);
        this.endTime = timeStringToTime(endTime);
        priority = priorityStringToPriority(priorityString);
        taskID = generateHashValue();
    }

    public Task(Integer taskID, String description, LocalDate date,
                LocalTime startTime, LocalTime endTime, Priority priority) {
        this.description = description;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.priority = priority;
        this.taskID = taskID;
    }

    public Task(String taskID, String description, String dateString,
                String startTime, String endTime, String priorityString)
        throws InvalidPriorityException, InvalidDatetimeException {
        this.description = description;
        date = dateStringToDate(dateString);
        this.startTime = timeStringToTime(startTime);
        this.endTime = timeStringToTime(endTime);
        priority = priorityStringToPriority(priorityString);
        this.taskID = Integer.parseInt(taskID);
    }

    private int generateHashValue() {
        return hashCode() % (int) pow(10, HASH_VALUE_DIGITS);
    }

    public Integer getTaskID() {
        return taskID;
    }

    public void setTaskID(Integer taskID) {
        this.taskID = taskID;
    }

    private LocalDate dateStringToDate(String dateString) throws InvalidDatetimeException {
        if (dateString == null) {
            return LocalDate.now();
        }
        String[] dateParts = dateString.split("-");
        int day = Integer.parseInt(dateParts[0]);
        int month = Integer.parseInt(dateParts[1]);
        int year = Integer.parseInt(dateParts[2]);

        try {
            return LocalDate.of(year, month, day);
        } catch (DateTimeException e) {
            throw new InvalidDatetimeException();
        }

    }

    private LocalTime timeStringToTime(String timeString) throws InvalidDatetimeException {
        if (timeString == null) {
            return null;
        }
        int time = Integer.parseInt(timeString);
        int hour = time / 100;
        int minute = time % 100;

        try {
            return LocalTime.of(hour, minute);
        } catch (DateTimeException e) {
            throw new InvalidDatetimeException();
        }
    }

    private Priority priorityStringToPriority(String priorityString) throws InvalidPriorityException {
        if (priorityString == null) {
            return Priority.LOW;
        }
        Priority priority;
        switch (priorityString) {
        case "1":
            priority = Priority.LOW;
            break;
        case "2":
            priority = Priority.MEDIUM;
            break;
        case "3":
            priority = Priority.HIGH;
            break;
        default:
            throw new InvalidPriorityException();
        }
        return priority;
    }

    private String dateToString(LocalDate date) {
        if (date == null) {
            return "";
        } else {
            return " " + date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        }
    }

    private String timeToString(LocalTime time) {
        if (time == null) {
            return "";
        } else {
            return " " + time.format(DateTimeFormatter.ofPattern("HHmm"));
        }
    }

    private String priorityToString(Priority priority) {
        return " " + priority.toString();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(String dateString) throws InvalidDatetimeException {
        date = dateStringToDate(dateString);
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) throws InvalidDatetimeException {
        this.startTime = timeStringToTime(startTime);
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public void setEndTime(String endTime) throws InvalidDatetimeException {
        this.endTime = timeStringToTime(endTime);
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(String priorityString) throws InvalidPriorityException {
        priority = priorityStringToPriority(priorityString);
    }

    public String toString() {
        return taskID.toString() + " " + description + dateToString(date) + timeToString(startTime)
                + timeToString(endTime) + priorityToString(priority);
    }
}

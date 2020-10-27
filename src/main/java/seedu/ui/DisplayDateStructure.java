package seedu.ui;

import seedu.data.TaskMap;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;

public class DisplayDateStructure {
    protected static final TextStyle MONTH_TEXT_STYLE = TextStyle.FULL;
    protected static final TextStyle WEEKDAY_TEXT_STYLE = TextStyle.SHORT;
    protected static final Locale LOCALE = Locale.ENGLISH;
    protected static final int DAYS_PER_WEEK = 7;
    protected static final int HASHCODE_STRING_LENGTH = 6;
    protected static final int DISPLAY_LENGTH = 141;
    protected static final int DAY_COLUMN_WIDTH = 20;
    protected static final int WEEK_ROW_WIDTH = 10;

    protected LocalDate currentDate = LocalDate.now();
    protected Month currentMonth = currentDate.getMonth();
    protected int currentDateDayOfMonth = currentDate.getDayOfMonth();
    protected DayOfWeek currentDateDayOfWeek = currentDate.getDayOfWeek();

    protected char[][] screen;
    protected String content = null;


    public DisplayDateStructure() {
        this(LocalDate.now());
    }

    public DisplayDateStructure(LocalDate date) {
        init(date);
    }

    private void init(LocalDate date) {
        currentDate = date;
        currentMonth = currentDate.getMonth();
        currentDateDayOfMonth = currentDate.getDayOfMonth();
        currentDateDayOfWeek = currentDate.getDayOfWeek();
    }

    public String getContent() {
        if (screen != null) {
            StringBuilder stringBuilder = new StringBuilder();
            for (char[] arr : screen) {
                stringBuilder.append(arr);
                stringBuilder.append(System.lineSeparator());
            }
            content = stringBuilder.toString();
        }
        return content;
    }

    protected void generateContent(TaskMap tasks) {
    }

    public void setCurrentDate(LocalDate currentDate) {
        this.currentDate = currentDate;
        updateFields();
    }

    public void setCurrentMonth(Month currentMonth) {
        this.currentMonth = currentMonth;
    }

    public void increment() {
    }

    public void decrement() {
    }

    public void updateFields() {
        currentMonth = currentDate.getMonth();
        currentDateDayOfMonth = currentDate.getDayOfMonth();
        currentDateDayOfWeek = currentDate.getDayOfWeek();
    }
}

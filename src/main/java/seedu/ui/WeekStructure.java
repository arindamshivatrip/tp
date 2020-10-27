package seedu.ui;

import seedu.commons.Util;
import seedu.data.TaskMap;
import seedu.task.Task;

import java.time.LocalDate;

public class WeekStructure extends DisplayDateStructure {
    private static final int DISPLAY_HEIGHT = 11;

    @Override
    protected void generateContent(TaskMap tasks) {
        currentMonth = currentDate.getMonth();
        String monthString = currentMonth.getDisplayName(MONTH_TEXT_STYLE, LOCALE);

        // Set screen with border
        screen = new char[DISPLAY_HEIGHT][DISPLAY_LENGTH];
        generateScreenWithBorder();

        int currentCol = 1;
        int currentRow = 1;

        // Display month name
        Util.putsIntoArrayWithCentralise(monthString, screen[currentRow++], 0, DISPLAY_LENGTH);

        // Display weekdays
        LocalDate startDate = currentDate.minusDays(currentDateDayOfWeek.getValue() - 1);
        LocalDate endDate = startDate.plusDays(DAYS_PER_WEEK);

        for (LocalDate date = startDate; date.isBefore(endDate); date = date.plusDays(1)) {

            // for each day
            String dayOfWeek = date.getDayOfWeek().getDisplayName(WEEKDAY_TEXT_STYLE, LOCALE);
            Util.putsIntoArrayWithCentralise(dayOfWeek,
                screen[currentRow++], currentCol, currentCol + DAY_COLUMN_WIDTH);

            String dayOfMonth = String.valueOf(date.getDayOfMonth());
            Util.putsIntoArrayWithCentralise(dayOfMonth,
                screen[currentRow++], currentCol, currentCol + DAY_COLUMN_WIDTH);

            for (Task task : tasks.searchByDate(date).getValues()) {
                String temp;
                if (currentRow == DISPLAY_HEIGHT - 2) {
                    temp = "...";
                    Util.putsIntoArray(temp, screen[currentRow], currentCol);
                    break;
                }
                temp = "#" + task.getTaskID();
                // # + 4 digits + " " = 6
                temp += Util.generatePadStringWithCharAndLength(' ', HASHCODE_STRING_LENGTH - temp.length());
                temp += task.getDescription();
                Util.limitStringWithDots(temp, 20);
                Util.putsIntoArray(temp, screen[currentRow++], currentCol);
            }

            currentCol += 20;
            currentRow = 2;    // Row0: Month str
        }
    }

    private void generateScreenWithBorder() {
        for (int i = 0; i < DISPLAY_HEIGHT; i++) {
            for (int j = 0; j < DISPLAY_LENGTH; j++) {
                screen[i][j] = (i % WEEK_ROW_WIDTH == 0 || j % DAY_COLUMN_WIDTH == 0) ? '*' : ' ';
            }
        }
    }

    public void increment() {
        setCurrentDate(currentDate.plusDays(DAYS_PER_WEEK));
    }

    public void decrement() {
        setCurrentDate(currentDate.minusDays(DAYS_PER_WEEK));
    }

}

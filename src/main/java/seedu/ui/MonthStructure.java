package seedu.ui;

import seedu.commons.Util;
import seedu.data.TaskMap;
import seedu.task.Task;

import java.time.DayOfWeek;
import java.time.LocalDate;

import static seedu.commons.Util.putsIntoArray;
import static seedu.commons.Util.putsIntoArrayWithCentralise;

public class MonthStructure extends DisplayDateStructure {
    private static int displayHeight = 53;
    private static final int ROW_NUM_OFFSET = 2;    // Month name and Day of week

    @Override
    protected void generateContent(TaskMap tasks) {
        setHeight();
        String monthString = currentMonth.getDisplayName(MONTH_TEXT_STYLE, LOCALE);

        int currentCol = 1;
        int currentRow = 1;

        // Set empty screen
        screen = new char[displayHeight][DISPLAY_LENGTH];
        generateScreenWithBorder();

        // Display month name
        putsIntoArrayWithCentralise(monthString, screen[currentRow++], 0, DISPLAY_LENGTH);

        // Display day name
        for (int i = 1; i <= DAYS_PER_WEEK; i++) {
            String dayOfWeek = DayOfWeek.of(i).getDisplayName(WEEKDAY_TEXT_STYLE, LOCALE);
            putsIntoArrayWithCentralise(dayOfWeek,
                screen[currentRow], currentCol, currentCol + DAY_COLUMN_WIDTH);
            currentCol += DAY_COLUMN_WIDTH;
        }
        currentRow++;


        // Display days
        LocalDate startDate = currentDate.minusDays(currentDateDayOfMonth - 1);
        int lengthOfCurrentMonth = currentDate.lengthOfMonth();
        LocalDate endDate = startDate.plusDays(lengthOfCurrentMonth);

        currentCol = (startDate.getDayOfWeek().getValue() - 1) * DAY_COLUMN_WIDTH + 1;

        for (LocalDate date = startDate; date.isBefore(endDate); date = date.plusDays(1)) {
            int tempRowNum = currentRow;

            // for each day
            String dayOfMonth = String.valueOf(date.getDayOfMonth());
            putsIntoArrayWithCentralise(dayOfMonth,
                screen[tempRowNum++], currentCol, currentCol + DAY_COLUMN_WIDTH);

            for (Task task : tasks.searchByDate(date).getValues()) {
                String temp;
                if (tempRowNum == currentRow + WEEK_ROW_WIDTH - ROW_NUM_OFFSET) {
                    temp = "...";
                    putsIntoArray(temp, screen[tempRowNum], currentCol);
                    break;
                }
                temp = "#" + task.getTaskID();
                // # + 4 digits + " " = 6
                temp += Util.generatePadStringWithCharAndLength(' ', HASHCODE_STRING_LENGTH - temp.length());
                temp += task.getDescription();
                Util.limitStringWithDots(temp, DAY_COLUMN_WIDTH);
                putsIntoArray(temp, screen[tempRowNum++], currentCol);
            }

            if (date.getDayOfWeek() == DayOfWeek.SUNDAY) {
                currentCol = 1;
                currentRow += WEEK_ROW_WIDTH;
            } else {
                currentCol += DAY_COLUMN_WIDTH;
            }
        }
    }

    private void generateScreenWithBorder() {
        for (int i = 0; i < displayHeight; i++) {
            for (int j = 0; j < DISPLAY_LENGTH; j++) {
                screen[i][j] = (i == 0 || i == (displayHeight - 1)
                    || ((i - ROW_NUM_OFFSET) % WEEK_ROW_WIDTH == 0 && i != ROW_NUM_OFFSET)
                    || j % DAY_COLUMN_WIDTH == 0) ? '*' : ' ';
            }
        }
    }

    public void increment() {
        setCurrentMonth(currentMonth.plus(1));
        if (currentMonth.getValue() == 1) {
            setCurrentDate(LocalDate.of(currentDate.getYear() + 1, currentMonth, 1));
        } else {
            setCurrentDate(LocalDate.of(currentDate.getYear(), currentMonth, 1));
        }
    }

    public void decrement() {
        setCurrentMonth(currentMonth.minus(1));
        if (currentMonth.getValue() == 12) {
            setCurrentDate(LocalDate.of(currentDate.getYear() - 1, currentMonth, 1));
        } else {
            setCurrentDate(LocalDate.of(currentDate.getYear(), currentMonth, 1));
        }
    }

    private void setHeight() {
        LocalDate firstDay = LocalDate.of(currentDate.getYear(), currentMonth, 1);
        int count = 0;
        int lengthOfCurrentMonth = currentDate.lengthOfMonth();
        LocalDate tempDay = firstDay;
        while (tempDay.isBefore(firstDay.plusDays(lengthOfCurrentMonth + firstDay.getDayOfWeek().getValue() - 1))) {
            tempDay = tempDay.plusDays(DAYS_PER_WEEK);
            count++;
        }
        displayHeight = 10 * count + 3;
    }

}

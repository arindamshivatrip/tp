package seedu.messages;

import seedu.commons.Util;

import static seedu.font.Colors.ANSI_BRIGHT_GREEN;
import static seedu.font.Colors.ANSI_CYAN;
import static seedu.font.Colors.ANSI_RESET;
import static seedu.font.Colors.ANSI_YELLOW;


public class Messages {
    public static final String LS = System.lineSeparator();
    public static final String WELCOME_BORDER = " " + padString('=', 58) + " ";
    public static final String WELCOME_BORDER_SPACE = "||" + padString(' ', 56) + "||";
    public static final String WELCOME_PREFIX = "||         ";
    public static final String HELP_BORDER = " " + padString('=', 120) + " ";
    public static final String HELP_BORDER_SPACE = "||" + padString(' ', 118) + "||";
    public static final String HELP_PREFIX = "||       ";
    public static final String INVALID_PRIORITY = "Invalid priority!" + LS;
    public static final String INVALID_COMMAND = "Invalid command!" + LS;
    public static final String UNKNOWN_COMMAND = "Unknown command!" + LS;
    public static final String INVALID_TASK_NUMBER = "Invalid task number!" + LS;
    public static final String INVALID_DATETIME = "Invalid datetime!" + LS;
    public static final String MAX_NUM_TASK = "Max number of tasks reached!" + LS;
    public static final String EMPTY_DATA_STACK = "Empty data stack!" + LS;
    public static final String NO_SUCH_FILE = "This ics file does not exist" + LS;
    public static final String INVALID_REMINDER = "Invalid reminder command!" + LS;
    public static final String IO_EXCEPTION = "Check data file!" + LS;
    public static final String INVALID_FORMAT = "Format entered is not valid! Type 'help' for more info." + LS;


    public static final String WELCOME_MESSAGE = WELCOME_BORDER + LS
            + WELCOME_BORDER_SPACE + LS
            + WELCOME_PREFIX + ANSI_YELLOW + "Welcome to" + ANSI_RESET + padString(' ', 37) + "||" + LS
            + WELCOME_PREFIX + ANSI_CYAN + "    ____  __      _   ____  _______" + ANSI_RESET + "            ||" + LS
            + WELCOME_PREFIX + ANSI_CYAN + "   / __ \\/ /___ _/ | / / / / / ___/" + ANSI_RESET + "            ||" + LS
            + WELCOME_PREFIX + ANSI_CYAN + "  / /_/ / / __ `/  |/ / / / /\\__ \\ " + ANSI_RESET + "            ||" + LS
            + WELCOME_PREFIX + ANSI_CYAN + " / ____/ / /_/ / /|  / /_/ /___/ / " + ANSI_RESET + "            ||" + LS
            + WELCOME_PREFIX + ANSI_CYAN + "/_/   /_/\\__,_/_/ |_/\\____//____/  " + ANSI_RESET
            + ANSI_CYAN + "v2.1" + ANSI_RESET + "        ||" + LS
            + WELCOME_BORDER_SPACE;

    public static final String HELP_MESSAGE = HELP_BORDER + LS
            + HELP_BORDER_SPACE + LS
            + HELP_PREFIX + "List of available commands:" + padString(' ', 84) + "||" + LS
            + HELP_PREFIX + "Note: Square bracket input is optional." + padString(' ', 72) + "||" + LS
            + HELP_PREFIX + "- help: show list of available commands" + padString(' ', 72) + "||" + LS
            + HELP_PREFIX + "  format: " + ANSI_BRIGHT_GREEN + "help" + ANSI_RESET + padString(' ', 97)
            + "||" + LS + HELP_PREFIX + "- add: add a task" + padString(' ', 94) + "||" + LS
            + HELP_PREFIX + "  format: " + ANSI_BRIGHT_GREEN
            + "add DESCRIPTION [d/dd-MM-yyyy] [st/HHmm] [et/HHmm] [p/NUMBER] [r/(ON/OFF)] [t-HHmm]"
            + ANSI_RESET + "                  ||" + LS
            + HELP_PREFIX + "- edit: edit a task" + padString(' ', 92) + "||" + LS
            + HELP_PREFIX + "  format: " + ANSI_BRIGHT_GREEN
            + "edit INDEX [des/DESCRIPTION] [d/dd-MM-yyyy] [st/HHmm] [et/HHmm] [p/NUMBER] [r/(ON/OFF)] [t-HHmm]"
            + ANSI_RESET + "     ||" + LS
            + HELP_PREFIX + "- list: show list of tasks" + padString(' ', 85) + "||" + LS
            + HELP_PREFIX + "  format: " + ANSI_BRIGHT_GREEN + "list [-d|-p|-w|-m|d/DATE]" + ANSI_RESET
            + padString(' ', 76) + "||" + LS
            + HELP_PREFIX + "- search: find task(s) with keyword in description"
            + padString(' ', 61) + "||" + LS
            + HELP_PREFIX + "  format: " + ANSI_BRIGHT_GREEN + "search KEYWORD(S)" + ANSI_RESET
            + padString(' ', 84) + "||" + LS
            + HELP_PREFIX + "- delete remove particular task by index" + padString(' ', 71) + "||" + LS
            + HELP_PREFIX + "  format: " + ANSI_BRIGHT_GREEN + "delete INDEX" + ANSI_RESET
            + padString(' ', 89) + "||" + LS
            + HELP_PREFIX + "- clear: remove all tasks" + padString(' ', 86) + "||" + LS
            + HELP_PREFIX + "  format: " + ANSI_BRIGHT_GREEN + "clear" + ANSI_RESET + padString(' ', 96)
            + "||" + LS
            + HELP_PREFIX + "- undo: revert modifications commands like add, edit, delete"
            + padString(' ', 51) + "||" + LS
            + HELP_PREFIX + "  format: " + ANSI_BRIGHT_GREEN + "undo" + ANSI_RESET + padString(' ', 97)
            + "||" + LS
            + HELP_PREFIX + "- redo: restore undone actions" + padString(' ', 81) + "||" + LS
            + HELP_PREFIX + "  format: " + ANSI_BRIGHT_GREEN + "redo" + ANSI_RESET
            + padString(' ', 97) + "||" + LS
            + HELP_PREFIX + "- bye: exit the program" + padString(' ', 88) + "||" + LS
            + HELP_PREFIX + "  format: " + ANSI_BRIGHT_GREEN + "bye" + ANSI_RESET + padString(' ', 98)
            + "||" + LS + HELP_BORDER_SPACE + LS
            + HELP_BORDER + LS;

    public static final String BYE_MESSAGE = "Bye! See you again!";
    public static final String LIST_MESSAGE = "Here is your list of tasks:";
    public static final String ADD_MESSAGE = "Task added.";
    public static final String IMPORT_MESSAGE = "Imported Calender.";
    public static final String EDIT_MESSAGE = "Task edited.";
    public static final String CLEAR_MESSAGE = "All tasks cleared." + LS;
    public static final String DELETE_MESSAGE = "Task deleted." + LS;
    public static final String SEARCH_NOT_FOUND_MESSAGE = "No matched item found." + LS;
    public static final String SEARCH_FOUND_MESSAGE = "Here is your list of tasks"
            + " which contain the word/letters:";
    public static final String NO_PREV_MODIFICATION = "There's nothing to undo.";
    public static final String UNDO_MESSAGE = "Previous modification has been undone.";
    public static final String REMINDER_MESSAGE = "Reminder, you have an upcoming task: ";
    public static final String NO_PREV_MODIFICATION_REDO = "There's nothing to redo.";
    public static final String REDO_MESSAGE = "Previous modification has been restored.";

    private static String padString(char letter, int length) {
        return Util.generatePadStringWithCharAndLength(letter, length);
    }
}

package seedu.commands;

import seedu.data.TaskMap;
import seedu.exceptions.InvalidCommandException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static seedu.messages.Messages.SEARCH_FOUND_MESSAGE;
import static seedu.messages.Messages.SEARCH_NOT_FOUND_MESSAGE;

public class SearchCommand extends ReadOnlyCommand {
    public static final String COMMAND_WORD = "search";
    // Search by description, can extend to other attributes
    public static final Pattern COMMAND_PATTERN = Pattern.compile(
            "^(?<toSearch>(\\w+\\s*)+\\w*)$");

    private final String toSearch;

    /**
     * Constructor.
     *
     * @param keyWord String to be searched for in the task list.
     */
    public SearchCommand(String keyWord) {
        toSearch = keyWord;
    }

    /**
     * Searches TaskMap for the keyword, and displays task(s) if found.
     *
     * @param tasks TaskMap of all the tasks in the list.
     * @return A CommandResult object, depending on whether keyword is found, with the relevant message.
     */
    public CommandResult execute(TaskMap tasks) {
        TaskMap found = tasks.searchByDescription(toSearch);
        if (found.size() > 0) {
            return new CommandResult(SEARCH_FOUND_MESSAGE, found);
        } else {
            return new CommandResult(SEARCH_NOT_FOUND_MESSAGE);
        }
    }
}

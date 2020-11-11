package seedu.parser;


import seedu.commands.AddCommand;
import seedu.commands.ByeCommand;
import seedu.commands.ClearCommand;
import seedu.commands.Command;
import seedu.commands.DeleteCommand;
import seedu.commands.EditCommand;
import seedu.commands.HelpCommand;
import seedu.commands.ListCommand;
import seedu.commands.RedoCommand;
import seedu.commands.SearchCommand;
import seedu.commands.UndoCommand;
import seedu.exceptions.InvalidCommandException;
import seedu.exceptions.InvalidFormatException;
import seedu.exceptions.InvalidTaskNumberException;
import seedu.exceptions.UnknownCommandException;

import java.util.regex.Matcher;

public class Parser {

    /**
     * Parses the raw user input to make sense of what was inputed.
     *
     * @param rawInput the raw user input
     * @return the command to be executed
     * @throws UnknownCommandException    the the method can't make sense of the input, default case
     * @throws InvalidCommandException    if input format is wrong
     * @throws InvalidTaskNumberException for edit/delete, if task index is wrong
     */
    public Command processRaw(String rawInput) throws
            UnknownCommandException, InvalidCommandException, InvalidTaskNumberException, InvalidFormatException {
        Matcher matcher;
        String commandWord = getCommandWord(rawInput);
        String commandArgs = getCommandArgs(rawInput);
        switch (commandWord) {
        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();
        case ListCommand.COMMAND_WORD:
            matcher = ListCommand.COMMAND_PATTERN.matcher(commandArgs);
            if (matcher.find()) {
                return new ListCommand("-d".equals(matcher.group("dateFlag")),
                        "-p".equals(matcher.group("priorityFlag")),
                        "-w".equals(matcher.group("displayByWeek")),
                        "-m".equals(matcher.group("displayByMonth")),
                        matcher.group("date"));
            } else {
                throw new InvalidCommandException();
            }
        case ByeCommand.COMMAND_WORD:
            return new ByeCommand();
        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();
        case AddCommand.COMMAND_WORD:
            matcher = AddCommand.COMMAND_PATTERN.matcher(commandArgs);
            if (matcher.find()) {
                return new AddCommand(matcher.group("description"), matcher.group("date"), matcher.group("st"),
                        matcher.group("et"), matcher.group("priority"), matcher.group("reminder"),
                        matcher.group("t"));
            } else {
                throw new InvalidFormatException();
            }
        case EditCommand.COMMAND_WORD:
            matcher = EditCommand.COMMAND_PATTERN.matcher(commandArgs);
            if (matcher.find()) {
                return new EditCommand(matcher.group("key"), matcher.group("description"),
                        matcher.group("date"), matcher.group("st"), matcher.group("et"),
                        matcher.group("priority"), matcher.group("reminder"), matcher.group("t"));
            } else {
                throw new InvalidFormatException();
            }
        case SearchCommand.COMMAND_WORD:
            matcher = SearchCommand.COMMAND_PATTERN.matcher(commandArgs);
            if (matcher.find()) {
                return new SearchCommand(matcher.group("toSearch"));
            } else {
                throw new InvalidCommandException();
            }
        case DeleteCommand.COMMAND_WORD:
            matcher = DeleteCommand.COMMAND_PATTERN.matcher(commandArgs);
            if (matcher.find()) {
                return new DeleteCommand(matcher.group("key"));
            } else {
                throw new InvalidCommandException();
            }
        case UndoCommand.COMMAND_WORD:
            return new UndoCommand();
        case RedoCommand.COMMAND_WORD:
            return new RedoCommand();
        default:
            throw new UnknownCommandException();

        }
    }

    /**
     * Gets the command word from the raw input.
     *
     * @param rawInput the user input
     * @return the command word. e.g add/edit/delete
     */
    public String getCommandWord(String rawInput) {
        String commandWord = rawInput;
        if (rawInput.contains(" ")) {
            String[] split = rawInput.split(" ", 2);
            commandWord = split[0];
        }
        return commandWord.trim();
    }

    /**
     * Gets everything after the command word.
     *
     * @param rawInput the user input
     * @return the string after the command word
     */
    public String getCommandArgs(String rawInput) {
        String commandArgs = "";
        if (rawInput.contains(" ")) {
            String[] split = rawInput.split(" ", 2);
            commandArgs = split[1];
        }
        return commandArgs.trim();
    }
}

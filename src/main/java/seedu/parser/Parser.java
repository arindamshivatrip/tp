package seedu.parser;

import seedu.commands.Add;
import seedu.commands.Bye;
import seedu.commands.Clear;
import seedu.commands.Command;
import seedu.commands.Delete;
import seedu.commands.Edit;
import seedu.commands.Help;
import seedu.commands.List;
import seedu.commands.Search;
import seedu.commands.Undo;
import seedu.exceptions.InvalidCommandException;
import seedu.exceptions.InvalidTaskNumberException;
import seedu.exceptions.UnknowCommandException;
import java.util.regex.Matcher;


public class Parser {


    public Command processRaw(String rawInput) throws
            UnknowCommandException, InvalidCommandException, InvalidTaskNumberException {
        Matcher matcher;
        if (rawInput.startsWith(Help.COMMAND_WORD)) {
            return new Help();
        } else if (rawInput.startsWith(List.COMMAND_WORD)) {
            matcher = List.COMMAND_PATTERN.matcher(rawInput);
            if (matcher.find()) {
                return new List(" -d".equals(matcher.group("dateFlag")),
                        " -p".equals(matcher.group("priorityFlag")),
                        " -w".equals(matcher.group("displayByWeek")),
                        " -m".equals(matcher.group("displayByMonth")),
                        matcher.group("date"));
            } else {
                throw new InvalidCommandException();
            }
        } else if (rawInput.startsWith(Bye.COMMAND_WORD)) {
            return new Bye();
        } else if (rawInput.startsWith(Clear.COMMAND_WORD)) {
            return new Clear();
        } else if (rawInput.startsWith(Add.COMMAND_WORD)) {
            matcher = Add.COMMAND_PATTERN.matcher(rawInput);
            if (matcher.find()) {
                return new Add(matcher.group("description"), matcher.group("date"), matcher.group("st"),
                        matcher.group("et"), matcher.group("priority"));
            } else {
                throw new InvalidCommandException();
            }
        } else if (rawInput.startsWith(Edit.COMMAND_WORD)) {
            matcher = Edit.COMMAND_PATTERN.matcher(rawInput);
            if (matcher.find()) {
                return new Edit(matcher.group("key"), matcher.group("description"),
                        matcher.group("date"), matcher.group("st"), matcher.group("et"),
                        matcher.group("priority"));
            } else {
                throw new InvalidCommandException();
            }
        } else if (rawInput.startsWith(Search.COMMAND_WORD)) {
            matcher = Search.COMMAND_PATTERN.matcher(rawInput);
            if (matcher.find()) {
                return new Search(matcher.group("toSearch"));
            } else {
                throw new InvalidCommandException();
            }
        } else if (rawInput.startsWith(Delete.COMMAND_WORD)) {
            matcher = Delete.COMMAND_PATTERN.matcher(rawInput);
            if (matcher.find()) {
                return new Delete(matcher.group("key"));
            } else {
                throw new InvalidCommandException();
            }
        } else if (rawInput.startsWith(Undo.COMMAND_WORD)) {
            return new Undo();
        } else {
            throw new UnknowCommandException();
        }
    }
}

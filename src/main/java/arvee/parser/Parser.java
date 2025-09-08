package arvee.parser;

import arvee.logic.CommandResult;
import arvee.util.Constants;
import arvee.util.DateTimeUtil;
import arvee.model.ToDoTask;
import arvee.model.Deadlines;
import arvee.model.Event;

public class Parser {
    /**
     * parses the corresponding input for the command word, and creates a corresponding CommandResult object
     * @param input the command
     * @return
     */
    public static CommandResult parse(String input) {
        assert input != null : "User input should not be null";
        input = input.trim();
      assert !input.isEmpty() : "User input should not be empty";
        if (input.equals(Constants.CMD_BYE)) return CommandResult.bye();
        if (input.equals(Constants.CMD_LIST)) return CommandResult.list();
        if (input.equals(Constants.CMD_SORT)) {
            return CommandResult.sort("asc");
        }

        if (input.startsWith(Constants.CMD_SORT)) {
            String arg = input.substring(5).trim().toLowerCase();
            return CommandResult.sort(arg);
        }


        if (input.startsWith(Constants.CMD_MARK + " ")) {
            int idx = Integer.parseInt(input.substring(5));
            return CommandResult.mark(idx, true);
        }
        if (input.startsWith(Constants.CMD_UNMARK + " ")) {
            int idx = Integer.parseInt(input.substring(7));
            return CommandResult.mark(idx, false);
        }
        if (input.startsWith(Constants.CMD_TODO)) {
            String desc = input.substring(5).trim();
            return CommandResult.add(new ToDoTask(desc));
        }
        if (input.startsWith(Constants.CMD_DEADLINE)) {
            String rest = input.substring(9).trim();
            String[] parts = rest.split("/by", 2);
            if (parts.length < 2) return CommandResult.error("Deadline format: deadline <desc> /by <date>");
            var by = DateTimeUtil.parseFlexible(parts[1].trim());
            return CommandResult.add(new Deadlines(parts[0].trim(), by));
        }
        if (input.startsWith(Constants.CMD_EVENT)) {
            String rest = input.substring(6).trim();
            String[] p1 = rest.split("/from", 2);
            if (p1.length < 2) return CommandResult.error("arvee.model.Event format: event <desc> /from <start> /to <end>");
            String[] p2 = p1[1].trim().split("/to", 2);
            if (p2.length < 2) return CommandResult.error("arvee.model.Event format: event <desc> /from <start> /to <end>");
            var start = DateTimeUtil.parseFlexible(p2[0].trim());
            var end   = DateTimeUtil.parseFlexible(p2[1].trim());
            return CommandResult.add(new Event(p1[0].trim(), start, end));
        }
        if (input.startsWith(Constants.CMD_DELETE)) {
            String[] parts = input.trim().split("\\s", 2);
            if (parts.length < 2) {
                return CommandResult.error("Usage: delete INDEX");
            }
            try {
                int index = Integer.parseInt(parts[1]);
                return CommandResult.delete(index);
            } catch (NumberFormatException e) {
                return CommandResult.error("Index must be a number");
            }
        }
        if (input.startsWith(Constants.CMD_FIND)) {
            String[] parts = input.trim().split("\\s", 2);
            if (parts.length < 2) {
                return CommandResult.error("Usage: find KEYWORD");
            }
            String keyword = parts[1].trim();
            return CommandResult.find(keyword);
        }

        return CommandResult.error("Sorry, I didn't understand that command.");
    }
}

package arvee.parser;

import arvee.logic.CommandResult;
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
        input = input.trim();
        if (input.equals("bye")) return CommandResult.bye();
        if (input.equals("list")) return CommandResult.list();

        if (input.startsWith("mark ")) {
            int idx = Integer.parseInt(input.substring(5));
            return CommandResult.mark(idx, true);
        }
        if (input.startsWith("unmark ")) {
            int idx = Integer.parseInt(input.substring(7));
            return CommandResult.mark(idx, false);
        }
        if (input.startsWith("todo")) {
            String desc = input.substring(5).trim();
            return CommandResult.add(new ToDoTask(desc));
        }
        if (input.startsWith("deadline")) {
            String rest = input.substring(9).trim();
            String[] parts = rest.split("/by", 2);
            if (parts.length < 2) return CommandResult.error("Deadline format: deadline <desc> /by <date>");
            var by = DateTimeUtil.parseFlexible(parts[1].trim());
            return CommandResult.add(new Deadlines(parts[0].trim(), by));
        }
        if (input.startsWith("event")) {
            String rest = input.substring(6).trim();
            String[] p1 = rest.split("/from", 2);
            if (p1.length < 2) return CommandResult.error("arvee.model.Event format: event <desc> /from <start> /to <end>");
            String[] p2 = p1[1].trim().split("/to", 2);
            if (p2.length < 2) return CommandResult.error("arvee.model.Event format: event <desc> /from <start> /to <end>");
            var start = DateTimeUtil.parseFlexible(p2[0].trim());
            var end   = DateTimeUtil.parseFlexible(p2[1].trim());
            return CommandResult.add(new Event(p1[0].trim(), start, end));
        }
        if (input.startsWith("delete")) {
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

        return CommandResult.error("Sorry, I didn't understand that command.");
    }
}

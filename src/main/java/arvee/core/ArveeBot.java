package arvee.core;

import arvee.logic.CommandResult;
import arvee.logic.TaskList;
import arvee.model.Task;
import arvee.parser.Parser;
import arvee.storage.Storage;

public class ArveeBot {
    private final TaskList tasks;

    public ArveeBot() {
        this.tasks = new TaskList(Storage.load());
    }

    public String getGreeting() {
        return "Hello! I'm ARVEE\nWhat can I do for you?";
    }

    /** Return true if the input would exit the app. */
    public boolean shouldExit(String input) {
        try {
            CommandResult r = Parser.parse(input.trim());
            return r.type == CommandResult.Type.BYE;
        } catch (Exception e) {
            return false;
        }
    }

    /** Process one line of user input and return the message to display. */
    public String getResponse(String input) {
        CommandResult r;
        try {
            r = Parser.parse(input.trim());
        } catch (Exception ex) {
            return "Error: " + ex.getMessage();
        }

        switch (r.type) {
            case FIND: {
                // In your code, FIND’s keyword is carried in r.error
                String keyword = r.error;
                TaskList results = new TaskList(tasks.find(keyword));
                return renderFound(results);
            }
            case DELETE: {
                Task deleted = tasks.get(r.index);
                tasks.remove(r.index);
                Storage.save(tasks.asList());
                return renderDeleted(deleted, tasks.size());
            }
            case BYE:
                return "Bye. Hope to see you again soon!";

            case LIST:
                return renderList(tasks);

            case MARK: {
                Task t = tasks.mark(r.index, r.markDone);
                Storage.save(tasks.asList());
                return renderMarked(t, r.markDone);
            }

            case ADD:
                assert r.task != null : "Added task must not be null";
                tasks.add(r.task);
                Storage.save(tasks.asList());
                return renderAdded(r.task, tasks.size());

            case ERROR:
            default:
                return r.error != null ? r.error : "Unknown command.";
        }
    }

    // ---------- render helpers (same text as your Ui previously printed) ----------

    private String renderList(TaskList tasks) {
        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1).append(". ").append(tasks.get(i + 1)).append("\n");
        }
        return sb.toString().trim();
    }

    private String renderMarked(Task t, boolean done) {
        return done
                ? "Nice! I've marked this task as done:\n " + t
                : "Ok, I've marked this task as not done yet:\n " + t;
    }

    private String renderAdded(Task t, int count) {
        return String.format("Got it. I've added this task:\n %s\nNow you have %d tasks in the list", t, count);
    }

    private String renderDeleted(Task t, int remaining) {
        return String.format("Noted. I've removed this task:\n %s\nNow you have %d tasks in the list.", t, remaining);
    }

    private String renderFound(TaskList results) {
        if (results.size() == 0) return "No matching tasks found.";
        StringBuilder sb = new StringBuilder("Here are the matching tasks in your list:\n");
        for (int i = 0; i < results.size(); i++) {
            sb.append(i + 1).append(". ").append(results.get(i)).append("\n");
        }
        return sb.toString().trim();
    }
}

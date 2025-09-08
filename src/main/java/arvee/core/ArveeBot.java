package arvee.core;

import arvee.logic.CommandResult;
import arvee.logic.TaskList;
import arvee.model.Task;
import arvee.parser.Parser;
import arvee.storage.Storage;

import static arvee.logic.CommandResult.Type.ERROR;

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

            case SORT:
                if ("desc".equals(r.error)) {
                    tasks.sortByDateDescending();
                } else {
                    tasks.sortByDateAscending();
                }
                Storage.save(tasks.asList());
                return renderSortedList(tasks, r.error);

            case ERROR:
            default:
                return r.error != null ? r.error : "Unknown command.";
        }
    }

    // ---------- render helpers (same text as your Ui previously printed) ----------

    /**
     * Returns out the string representation of list of tasks
     * @param tasks list to be printed
     * @return String representation of output
     */
    private String renderList(TaskList tasks) {
        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1).append(". ").append(tasks.get(i + 1)).append("\n");
        }
        return sb.toString().trim();
    }

    /**
     * Returns string representation of message confirming task is marked
     * @param t task to be marked
     * @param done status
     * @return message as String
     */
    private String renderMarked(Task t, boolean done) {
        return done
                ? "Nice! I've marked this task as done:\n " + t
                : "Ok, I've marked this task as not done yet:\n " + t;
    }

    /**
     * Returns string representation of message confirming task is added
     * @param t task to be added
     * @param count total number of tasks in the list
     * @return message as String
     */
    private String renderAdded(Task t, int count) {
        return String.format("Got it. I've added this task:\n %s\nNow you have %d tasks in the list", t, count);
    }

    /**
     * Returns string representation of message confirming task is removed
     * @param t task to be removed
     * @param remaining remaining number of tasks in list
     * @return message as String
     */
    private String renderDeleted(Task t, int remaining) {
        return String.format("Noted. I've removed this task:\n %s\nNow you have %d tasks in the list.", t, remaining);
    }

    /**
     * Returns string representation of the list of tasks with description matching keyword
     * @param results tasklist of matching tasks
     * @return message as String
     */
    private String renderFound(TaskList results) {
        if (results.size() == 0) return "No matching tasks found.";
        StringBuilder sb = new StringBuilder("Here are the matching tasks in your list:\n");
        for (int i = 0; i < results.size(); i++) {
            sb.append(i + 1).append(". ").append(results.get(i)).append("\n");
        }
        return sb.toString().trim();
    }

    /**
     * Returns string representation of list of tasks in sorted order
     * @param tasks task list in sorted order
     * @param order sorted in either ascending or descending order
     * @return message as String
     */
    private String renderSortedList(TaskList tasks, String order) {
        StringBuilder sb = new StringBuilder("Sorted by date/time (" + order + ").\n");
        sb.append("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1).append(". ").append(tasks.get(i + 1)).append("\n"); // note 1-based get()
        }
        return sb.toString().trim();
    }
}

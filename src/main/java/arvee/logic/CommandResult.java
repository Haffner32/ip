package arvee.logic;

import arvee.model.Task;

public class CommandResult {
    public enum Type { BYE, LIST, MARK, ADD, DELETE, ERROR, FIND }
    public final Type type;
    public final Task task;           // for ADD
    public final Integer index;       // for MARK (1-based)
    public final Boolean markDone;    // for MARK
    public final String error;        // for ERROR

    /**
     * Initialises a command result object that is subsequently acted upon by the Arvee class
     * @param t the type of command
     * @param task the task object that contains the type of task
     * @param index the index of the task to be acted
     * @param markDone whether the task is to be marked done or not done
     * @param error whether there are errors that arise from the command
     */
    private CommandResult(Type t, Task task, Integer index, Boolean markDone, String error) {
        this.type = t; this.task = task; this.index = index; this.markDone = markDone; this.error = error;
    }

    /**
     * a bye commandResult that terminates the program
     * @return commandResult
     */
    public static CommandResult bye() {
        return new CommandResult(Type.BYE, null, null, null, null);
    }

    /**
     * makes the program print out the items in the list.
     * @return commandResult
     */
    public static CommandResult list() {
        return new CommandResult(Type.LIST, null, null, null, null);
    }

    /**
     * makes the program mark the corresponding task in the list
     * @param oneBasedIndex the index of the task
     * @param done the status of the task
     * @return commandResult
     */
    public static CommandResult mark(int oneBasedIndex, boolean done) {
        return new CommandResult(Type.MARK, null, oneBasedIndex, done, null);
    }

    /**
     * adds the task to the list
     * @param t the task to be added
     * @return commandResult
     */
    public static CommandResult add(Task t) {
        return new CommandResult(Type.ADD, t, null, null, null);
    }

    /**
     * deletes a task from the list
     * @param oneBasedIndex the index of the task
     * @return commandResult
     */
    public static CommandResult delete(int oneBasedIndex) {
        return new CommandResult(Type.DELETE, null, oneBasedIndex, null, null);
    }

    /**
     * corresponds to an error thrown by the program
     * @param msg error message
     * @return commandResult
     */
    public static CommandResult error(String msg) {
        return new CommandResult(Type.ERROR, null, null, null, msg);
    }

    /**
     * finds tasks with a description that contains the keyword
     * @param keyword to be searched
     * @return commandResult
     */
    public static CommandResult find(String keyword) {
        return new CommandResult(Type.FIND, null, null, null, keyword);
    }
}


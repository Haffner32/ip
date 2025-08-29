public class CommandResult {
    enum Type { BYE, LIST, MARK, ADD, ERROR }
    public final Type type;
    public final Task task;           // for ADD
    public final Integer index;       // for MARK (1-based)
    public final Boolean markDone;    // for MARK
    public final String error;        // for ERROR

    private CommandResult(Type t, Task task, Integer index, Boolean markDone, String error) {
        this.type = t; this.task = task; this.index = index; this.markDone = markDone; this.error = error;
    }
    public static CommandResult bye() { return new CommandResult(Type.BYE, null, null, null, null); }
    public static CommandResult list() { return new CommandResult(Type.LIST, null, null, null, null); }
    public static CommandResult mark(int oneBasedIndex, boolean done) {
        return new CommandResult(Type.MARK, null, oneBasedIndex, done, null);
    }
    public static CommandResult add(Task t) { return new CommandResult(Type.ADD, t, null, null, null); }
    public static CommandResult error(String msg) { return new CommandResult(Type.ERROR, null, null, null, msg); }
}


package arvee.model;

public class Task {
    private String desc;
    private boolean done;

    /**
     * Constructor for task object
     * @param desc description
     */
    public Task(String desc) {
        this.desc = desc;
        this.done = false;
    }

    /**
     * marks task as done or not done
     * @param status status of the task
     */
    public void setDone(boolean status) {
        this.done = status;
    }

    /**
     * getter for description of task
     * @return description of task
     */
    public String getDesc() {
        return this.desc;
    }

    /**
     * getter for the status of the task
     * @return status of task
     */
    public boolean isDone() {
        return this.done;
    }

    @Override
    public String toString() {
        String status = this.done ? "X" : " ";
        return String.format("[%s] %s", status, this.desc);
    }
}

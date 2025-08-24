public class Task {
    private String desc;
    private boolean done;

    public Task(String desc) {
        this.desc = desc;
        this.done = false;
    }

    public void setDone(boolean status) {
        this.done = status;
    }

    public String getDesc() {
        return this.desc;
    }

    @Override
    public String toString() {
        String status = this.done ? "X" : " ";
        return String.format("[%s] %s", status, this.desc);
    }
}

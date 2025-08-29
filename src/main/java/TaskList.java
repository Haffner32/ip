import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> items;

    public TaskList() {
        this.items = new ArrayList<>();
    }

    public TaskList(List<Task> initial) {
        this.items = initial;
    }

    public int size() {
        return items.size();
    }

    public Task get(int index) {
        return items.get(index);
    }

    public void add(Task t) {
        items.add(t);
    }

    public void remove(int index) {
        items.remove(index);
    }

    public Task mark(int index, boolean done) {
        int i = index - 1;
        Task t = items.get(i);
        t.setDone(done);
        items.set(i, t);
        return t;
    }

    public List<Task> asList() {
        return items;
    }
}

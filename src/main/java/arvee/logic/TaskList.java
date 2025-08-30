package arvee.logic;

import arvee.model.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> items;

    /**
     * Creates a task list object that encapsulates an arraylist of Tasks
     * if there are no saved items upon initial startup
     */
    public TaskList() {
        this.items = new ArrayList<>();
    }

    /**
     * Creates a tasklist object based on the pre-existing arraylist of tasks
     * @param initial the saved arraylist of tasks
     */
    public TaskList(List<Task> initial) {
        this.items = initial;
    }

    /**
     * Returns the number of items in the list
     * @return item tally
     */
    public int size() {
        return items.size();
    }

    /**
     * gets the corresponding task according to its index
     * @param oneBasedIndex the index of the task
     * @return the corresponding task
     */
    public Task get(int oneBasedIndex) {
        int index = oneBasedIndex - 1;
        return items.get(index);
    }

    /**
     * adds a task to the arraylist
     * @param t the task to be added
     */
    public void add(Task t) {
        items.add(t);
    }

    /**
     * removes a task from the arraylist
     * @param oneIndex the index of the task to be removed
     */
    public void remove(int oneIndex) {
        int index = oneIndex - 1;
        items.remove(index);
    }

    /**
     * marks a task in the list as done or not done
     * @param index the index of the task to be changed
     * @param done the status of the task
     * @return the mutated task
     */
    public Task mark(int index, boolean done) {
        int i = index - 1;
        Task t = items.get(i);
        t.setDone(done);
        items.set(i, t);
        return t;
    }

    /**
     * Returns the list of tasks as an arraylist
     * @return arraylist
     */
    public List<Task> asList() {
        return items;
    }
}

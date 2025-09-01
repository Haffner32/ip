package arvee.logic;

import arvee.model.Task;
import arvee.model.ToDoTask;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {

    /**
     * Test to check whether the marking logic of the tasks works.
     */
    @Test
    @DisplayName("Mark and unmark by 1-based index changes task done state")
    void markAndUnmark() {
        TaskList list = new TaskList();
        Task t = new ToDoTask("read book");
        list.add(t);

        Task marked = list.mark(1, true);
        assertTrue(marked.isDone(), "Task should be marked done");
        assertTrue(list.get(0).isDone(), "Stored task should reflect change");

        Task unmarked = list.mark(1, false);
        assertFalse(unmarked.isDone(), "Task should be unmarked");
        assertFalse(list.get(0).isDone(), "Stored task should reflect change");
    }

    /**
     * Test to check whether the index marking will throw error for out of bounds index
     */
    @SuppressWarnings("checkstyle:SingleSpaceSeparator")
    @Test
    @DisplayName("Marking with out-of-range index throws IndexOutOfBoundsException")
    void markOutOfRange() {
        TaskList list = new TaskList();
        list.add(new ToDoTask("one"));
        assertThrows(IndexOutOfBoundsException.class, () -> list.mark(0, true));  // 0 not allowed (1-based)
        assertThrows(IndexOutOfBoundsException.class, () -> list.mark(2, true));  // too large
    }
}

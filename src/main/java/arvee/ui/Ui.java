package arvee.ui;

import java.util.Scanner;
import arvee.logic.TaskList;
import arvee.model.Task;

/**
 * Represents the user interface of the ARVEE chatbot application
 * provides the appropriate response for each command
 */
public class Ui {
    private final Scanner sc = new Scanner(System.in);

    /**
     * Outputs the welcome message
     */
    public void showWelcome() {
        System.out.println("Hello! I'm ARVEE" +
                "\n" + "What can I do for you?");
    }

    /**
     * Outputs an error message when an error is cought
     * @param s the error message
     */
    public void showError(String s) {
        System.out.println(s);
    }

    /**
     * Reads the next line in the input
     * @return outputs the line as a string
     */
    public String readCommand() {
        return sc.nextLine().trim();
    }

    /**
     * prints the list of tasks as a string output
     * @param tasks the input list of tasks
     */
    public void showList(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        int size = tasks.size();
        for (int i = 1; i < size + 1; i++) {
            String out = String.format("%s. %s", i, tasks.get(i));
            System.out.println(out);
        }
    }

    /**
     * prints the confirmation message after adding a task to the list
     * @param t task that was added
     * @param count the current tally of tasks in the list
     */
    public void showAdded(Task t, int count) {
        System.out.println(String.format("Got it. I've added this task:\n" +
                " %s\n" +
                "Now you have %s tasks in the list.", t, count));
    }

    /**
     * prints the confirmation message upon marking or unmarking a task in the list
     * @param t the task that was mutated
     * @param done whether the task is done or not
     */
    public void showMarked(Task t, boolean done) {
        if (done) {
            System.out.println(String.format("Nice! I've marked this task as done:\n %s", t));
        } else {
            System.out.println(String.format("Ok, I've marked this task as not done yet:\n %s", t));
        }
    }

    /**
     * prints the confirmation message after deleting a task from the list
     * @param t the task that was deleted
     * @param remaining the tally of remaining tasks in the list
     */
    public void showDeleted(Task t, int remaining) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + t);
        System.out.println("Now you have " + remaining + " tasks in the list.");
    }

    /**
     * prints a message as the program terminates
     */
    public void showBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}

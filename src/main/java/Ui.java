import java.util.Scanner;

public class Ui {
    private final Scanner sc = new Scanner(System.in);

    public void showWelcome() {
        System.out.println("Hello! I'm ARVEE" +
                "\n" + "What can I do for you?");
    }

    public void showLine(String s) {
        System.out.println(s);
    }

    public void showError(String s) {
        System.out.println(s);
    }

    public String readCommand() {
        return sc.nextLine().trim();
    }

    public void showList(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        int size = tasks.size();
        for (int i = 0; i < size; i++) {
            String out = String.format("%s. %s", i + 1, tasks.get(i));
            System.out.println(out);
        }
    }

    public void showAdded(Task t, int count) {
        System.out.println(String.format("Got it. I've added this task:\n" +
                " %s\n" +
                "Now you have %s tasks in the list.", t, count));
    }

    public void showMarked(Task t, boolean done) {
        if (done) {
            System.out.println(String.format("Nice! I've marked this task as done:\n %s", t));
        } else {
            System.out.println(String.format("Ok, I've marked this task as not done yet:\n %s", t));
        }
    }

    public void showBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}

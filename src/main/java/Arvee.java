import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.ArrayList;

public class Arvee {
    public static void main(String[] args) {
        System.out.println("Hello! I'm ARVEE" +
                "\n" + "What can I do for you?");
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> items = new ArrayList<>();
        while (true) {
            String input = sc.nextLine().trim();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (input.equals("list")) {
                int size = items.size();
                for (int i = 0; i < size; i++) {
                    String out = String.format("%s. %s", i + 1, items.get(i));
                    System.out.println(out);
                }
            } else if (input.startsWith("mark ")) {
                int index = Integer.valueOf(input.substring(5)) - 1;
                Task change = items.get(index);
                change.setDone(true);
                items.set(index, change);
                System.out.println(String.format("Nice! I've marked this task as done:\n %s", change));
            } else if (input.startsWith("unmark ")) {
                int index = Integer.valueOf(input.substring(7)) - 1;
                Task change = items.get(index);
                change.setDone(false);
                items.set(index, change);
                System.out.println(String.format("Ok, I've marked this task as not done yet:\n %s", change));
            }
            else {
                if (input.startsWith("todo")) {
                    String task = input.substring(5);
                    Task next = new ToDoTask(task);
                } else if (input.startsWith("deadline")) {

                }
                items.add(next);
                System.out.println("added: " + input);
            }
        }
    }

}

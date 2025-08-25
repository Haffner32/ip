import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.ArrayList;

public class Arvee {
    public static void main(String[] args) {
        System.out.println("Hello! I'm ARVEE" +
                "\n" + "What can I do for you?");
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> items = new ArrayList<>();
        while (sc.hasNextLine()) {
            String input = sc.nextLine().trim();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }

            try {

                if (input.startsWith("delete")) {
                    int index = parseIndexArg(input, "delete");
                    if (index > items.size() - 1) throw new InvalidIndexException(
                            "That task number does not exist. You have " + items.size() + " task(s).");
                    Task toRemove = items.get(index);
                    items.remove(index);
                    System.out.println("Noted. I've removed this task:\n" +
                            toRemove.toString() +
                            "\nNow you have " + items.size() + " tasks in the list.");
                    continue;
                }
                if (input.equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    int size = items.size();
                    for (int i = 0; i < size; i++) {
                        String out = String.format("%s. %s", i + 1, items.get(i));
                        System.out.println(out);
                    }
                    continue;
                }

                if (input.startsWith("mark ")) {
                    int index = Integer.valueOf(input.substring(5)) - 1;
                    Task change = items.get(index);
                    change.setDone(true);
                    items.set(index, change);
                    System.out.println(String.format("Nice! I've marked this task as done:\n %s", change));
                    continue;
                }

                if (input.startsWith("unmark ")) {
                    int index = Integer.valueOf(input.substring(7)) - 1;
                    Task change = items.get(index);
                    change.setDone(false);
                    items.set(index, change);
                    System.out.println(String.format("Ok, I've marked this task as not done yet:\n %s", change));
                    continue;
                }
                if (input.startsWith("todo")) {
                    String task = requireNonBlank(input.substring(4).trim());
                    Task next = new ToDoTask(task);
                    items.add(next);
                    System.out.println(String.format("Got it. I've added this task:\n" +
                            " %s\n" +
                            "Now you have %s tasks in the list.", next, items.size()));
                    continue;
                }
                if (input.startsWith("deadline")) {
                    String rest = requireNonBlank(input.substring(8).trim());
                    String[] parts = rest.split("/by", 2);
                    String task = parts[0];
                    String deadline = parts[1];
                    Task next = new Deadlines(task, deadline);
                    items.add(next);
                    System.out.println(String.format("Got it. I've added this task:\n" +
                            " %s\n" +
                            "Now you have %s tasks in the list.", next, items.size()));
                    continue;
                }
                if (input.startsWith("event")) {
                    String rest = requireNonBlank(input.substring(5).trim());
                    String[] parts = rest.split("/from", 2);
                    String task = parts[0].trim();
                    String[] timeParts = parts[1].split("/to",2);
                    String start = timeParts[0];
                    String end = timeParts[1];
                    Task next = new Event(task, start, end);
                    items.add(next);
                    System.out.println(String.format("Got it. I've added this task:\n" +
                            " %s\n" +
                            "Now you have %s tasks in the list.", next, items.size()));
                    continue;
                }
                throw new UnknownCommandException();
            } catch (EmptyDescriptionException | InvalidArgumentException | InvalidIndexException e) {
                System.out.println(e.getMessage());
            } catch (UnknownCommandException e) {
                System.out.println("Sorry, I don't recognize that command");
            }
        }
    }

    public static String requireNonBlank(String desc) throws EmptyDescriptionException {
        if (desc == null || desc.isBlank()) throw new EmptyDescriptionException(
                "OOPS!!! The description of a task cannot be empty.");
        return desc;
    }

    public static int parseIndexArg(String input, String cmd)
            throws InvalidArgumentException {
        String[] args = input.split(cmd, 2);
        String argIndex = args[1];
        if (argIndex.isBlank()) {
            throw new InvalidArgumentException("Please provide a task number.");
        }
        int oneBased;
        try {
            oneBased = Integer.parseInt(argIndex.trim());
        } catch (NumberFormatException e) {
            throw new InvalidArgumentException("That doesn't look like an argument. Please try again.");
        }
        return oneBased - 1;
    }
}

import java.util.Scanner;
import java.util.ArrayList;

public class Arvee {
    public static void main(String[] args) {
        System.out.println("Hello! I'm ARVEE" +
                "\n" + "What can I do for you?");
        Scanner sc = new Scanner(System.in);
        ArrayList<String> items = new ArrayList<>();
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
            } else {
                items.add(input);
                System.out.println("added: " + input);
            }
        }
    }
}

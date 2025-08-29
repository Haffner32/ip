import java.sql.SQLOutput;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Scanner;
import java.util.ArrayList;

public class Arvee {
    public static void main(String[] args) {
        Ui ui = new Ui();
        TaskList tasks = new TaskList(Storage.load());

        ui.showWelcome();

        while (true) {
            String input = ui.readCommand();
            CommandResult r;
            try {
                r = Parser.parse(input);
            } catch (Exception ex) {
                ui.showError("Error: " + ex.getMessage());
                continue;
            }

            switch (r.type) {
                case BYE:
                    ui.showBye();
                    return;

                case LIST:
                    ui.showList(tasks);
                    break;

                case MARK: {
                    Task t = tasks.mark(r.index, r.markDone);
                    ui.showMarked(t, r.markDone);
                    Storage.save(tasks.asList());
                    break;
                }

                case ADD:
                    tasks.add(r.task);
                    ui.showAdded(r.task, tasks.size());
                    Storage.save(tasks.asList());
                    break;

                case ERROR:
                    ui.showError(r.error);
                    break;
            }
        }
    }
}

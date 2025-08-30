package arvee;

import arvee.logic.CommandResult;
import arvee.logic.TaskList;
import arvee.model.Task;
import arvee.parser.Parser;
import arvee.storage.Storage;
import arvee.ui.Ui;

/**
 * Represents the main entry point of the Arvee chatbot application.
 * Handles the program loop by coordinating user input, parsing commands,
 * updating the task list, and saving/loading from storage.
 */
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
                case FIND: {
                    String keyword = r.error;
                    TaskList results = new TaskList(tasks.find(keyword));
                    ui.showFound(results);
                    break;
                }

                case DELETE: {
                    Task deleted = tasks.get(r.index);
                    tasks.remove(r.index);
                    ui.showDeleted(deleted, tasks.size());
                    Storage.save(tasks.asList());
                    break;
                }
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

package arvee.storage;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import arvee.util.DateTimeUtil;
import arvee.model.Task;
import arvee.model.ToDoTask;
import arvee.model.Deadlines;
import arvee.model.Event;

public class Storage {
    private static final Path SAVE_PATH = Paths.get("data", "duke.txt");

    /**
     * loads the saved list of tasks from the txt file into the program
     * @return the corresponding arraylist of tasks
     */
    public static List<Task> load() {
        List<Task> items = new ArrayList<>();

        if (!Files.exists(SAVE_PATH)) {
            return items;
        }

        try {
            List<String> lines = Files.readAllLines(SAVE_PATH, StandardCharsets.UTF_8);
            int lineNo = 0;
            for (String line : lines) {
                lineNo++;
                line = line.trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split("\\s*\\|\\s*");
                try {
                    String type = parts[0];
                    boolean done = "1".equals(parts[1]);

                    switch (type) {
                        case "T": {
                            String desc = parts[2];
                            Task t = new ToDoTask(desc);
                            t.setDone(done);
                            items.add(t);
                            break;
                        }
                        case "D": {
                            String desc = parts[2];
                            String byRaw = parts[3];
                            LocalDateTime by;
                            try {
                                by = LocalDateTime.parse(byRaw);
                            } catch (Exception e) {
                                by = DateTimeUtil.parseFlexible(byRaw);
                            }
                            Task d = new Deadlines(desc, by);
                            d.setDone(done);
                            items.add(d);
                            break;
                        }
                        case "E": {
                            String desc = parts[2];
                            String startRaw = parts[3];
                            String endRaw = parts[4];
                            LocalDateTime start;
                            LocalDateTime end;
                            try {
                                start = LocalDateTime.parse(startRaw);
                                end = LocalDateTime.parse(endRaw);
                            } catch (Exception e) {
                                start = DateTimeUtil.parseFlexible(startRaw);
                                end = DateTimeUtil.parseFlexible(endRaw);
                            }
                            Task e = new Event(desc, start, end);
                            e.setDone(done);
                            items.add(e);
                            break;
                        }
                        default:
                            System.err.println("Skipping unknown task type at line " + lineNo + ": " + type);
                    }
                } catch (Exception parseEx) {
                    System.err.println("Skipping corrupted line " + lineNo + ": " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Failed to read save file: " + e.getMessage());
        }
        return items;
    }

    /**
     * saves the list of tasks after each mutating command is called
     * @param items the current list after changes are made
     */
    public static void save(List<Task> items) {
        try {
            Files.createDirectories(SAVE_PATH.getParent());

            List<String> lines = new ArrayList<>();
            for (Task t : items) {
                lines.add(serialize(t));
            }

            Files.write(
                    SAVE_PATH,
                    lines,
                    StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING,
                    StandardOpenOption.WRITE
            );
        } catch (IOException e) {
            System.err.println("Failed to write save file: " + e.getMessage());
        }
    }

    /**
     * saves each task as a string that is saved in the txt file
     * @param t task
     * @return string format for each task
     */
    private static String serialize(Task t) {
        int done = t.isDone() ? 1 : 0;

        if (t instanceof ToDoTask) {
            return String.format("T | %d | %s", done, ((ToDoTask) t).getDesc());
        } else if (t instanceof Deadlines) {
            Deadlines d = (Deadlines) t;
            return String.format("D | %d | %s | %s", done, d.getDesc(), d.getBy().toString());
        } else if (t instanceof Event) {
            Event e = (Event) t;
            return String.format("E | %d | %s | %s | %s", done, e.getDesc(),
                    e.getStart().toString(), e.getEnd().toString());
        } else {
            return String.format("T | %d | %s", done, t.toString());
        }
    }
}

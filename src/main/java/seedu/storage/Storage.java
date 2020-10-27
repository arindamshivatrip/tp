package seedu.storage;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import seedu.data.TaskMap;
import seedu.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Scanner;

public class Storage {
    private static final String DIRECTORY_NAME = "data";
    private static final String FILE_NAME = "data.json";
    private final Gson gson = new Gson();

    /**
     * Write to file the latest data of tasks.
     *
     * @param tasks latest TaskList object after modification.
     */
    public void writeTasksToFile(TaskMap tasks) throws IOException {
        FileWriter file = new FileWriter(DIRECTORY_NAME + "/" + FILE_NAME);
        for (Task task : tasks.getValues()) {
            file.write(gson.toJson(task) + System.lineSeparator());
        }
        file.close();
    }

    /**
     * Load data from file and add tasks to TaskList.
     */
    public TaskMap loadTasks() throws IOException {
        // If both dir and file are newly created, return empty taskMap.
        if (!createDirectory()) {
            return readTasksFromFile();
        }
        return new TaskMap();
    }

    /**
     * Create a directory if the "data" directory does not exists.
     *
     * @return true if directory is created at the point of execution.
     */
    private boolean createDirectory() throws IOException {
        File directory = new File(DIRECTORY_NAME);

        if (!directory.exists()) {
            boolean directoryCreated = directory.mkdir();
            assert directoryCreated;
            boolean fileCreated = new File(DIRECTORY_NAME + "/" + FILE_NAME).createNewFile();
            assert fileCreated;
            return true;
        }
        return false;
    }

    /**
     * Read lines from file and process each line.
     */
    private TaskMap readTasksFromFile() throws FileNotFoundException {
        TaskMap tasks = new TaskMap();
        File file = new File(DIRECTORY_NAME + "/" + FILE_NAME);
        Scanner scanner = new Scanner(file);
        Type type = new TypeToken<Task>(){}.getType();
        while (scanner.hasNextLine()) {
            tasks.addTask(gson.fromJson(scanner.nextLine(), type));
        }
        return tasks;
    }
}

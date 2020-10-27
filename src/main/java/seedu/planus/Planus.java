package seedu.planus;

import seedu.commands.LogicManager;
import seedu.data.Model;
import seedu.storage.Storage;
import seedu.ui.Ui;

import java.io.IOException;


public class Planus {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    private Model model;
    private Ui ui;
    private Storage storage;
    private LogicManager logicManager;


    public static void main(String[] args) {
        new Planus().run();
    }

    private void run() {
        try {
            initProgram();
            logicManager.run();
            shutdownProgram();
        } catch (Exception e) {
            ui.showException(e);
        }
    }

    private void initProgram() throws IOException {
        storage = new Storage();
        model = new Model(storage.loadTasks());
        ui = new Ui();
        logicManager = new LogicManager(ui, model);
        ui.showWelcomeMessage(model.getTaskMap());
    }

    private void shutdownProgram() throws IOException {
        storage.writeTasksToFile(model.getTaskMap());
    }

}

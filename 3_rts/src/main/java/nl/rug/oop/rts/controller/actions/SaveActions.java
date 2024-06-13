package nl.rug.oop.rts.controller.actions;

import nl.rug.oop.rts.model.base.Graph;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Saving action handler. Saves to JSON.
 */
public class SaveActions {

    /**
     * Saves the current game state to JSON.
     *
     * @param graph The graph being saved.
     */
    public void saveGame(Graph graph) {
        File dir = createSaveFolderIfNotExists();
        JFileChooser fileChooser = new JFileChooser(dir);
        int val = fileChooser.showSaveDialog(null);

        if (val == JFileChooser.APPROVE_OPTION) {
            String json = graph.saveJson();

            try {
                FileOutputStream output = new FileOutputStream(fileChooser.getSelectedFile() + ".json");
                output.write(json.getBytes());
                output.close();
            } catch (IOException e) {
                System.out.println("Error saving file: " + e.getMessage());
            }
        }
    }

    private File createSaveFolderIfNotExists() {
        File saveFolder = new File("saves");
        if (!saveFolder.exists()) {
            saveFolder.mkdirs();
        }

        return saveFolder;
    }
}

package nl.rug.oop.rts.controller.actions;

import lombok.extern.slf4j.Slf4j;
import nl.rug.oop.rts.model.base.Graph;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Slf4j
public class SaveActions {
    public void saveGame(Graph graph) {
        log.info("Saving the game");

        File dir = createSaveFolderIfNotExists();
        JFileChooser fileChooser = new JFileChooser(dir);
        int val = fileChooser.showSaveDialog(null);

        if(val == JFileChooser.APPROVE_OPTION) {
            String json = graph.saveJson();

            try {
                FileOutputStream output = new FileOutputStream(fileChooser.getSelectedFile() + ".json");
                output.write(json.getBytes());
                output.close();
            } catch (IOException e) {
                log.error("Error saving the file: {}", e.getMessage());
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

package nl.rug.oop.rpg.game.util;

import nl.rug.oop.rpg.game.Game;

import java.io.*;
import java.util.regex.Pattern;

import static nl.rug.oop.rpg.game.util.SaveType.QUICKSAVE;

/**
 * A utility class for saving and loading.
 */
public class IOUtils implements Serializable {
    private final String SAVE_FOLDER = "savedgames/";
    private final Pattern VALID_FILENAME_PATTERN = Pattern.compile("^[a-zA-Z0-9_-]+$");
    private static final long serialVersionUID = 42L;

    /**
     * Creates a save folder if one does not exist.
     */
    public void createSaveFolderIfNotExists() {
        File folder = new File(SAVE_FOLDER);
        if (!folder.exists()) {
            if (folder.mkdirs()) {
                System.out.println("Save folder created successfully.");
            } else {
                System.out.println("Failed to create save folder.");
            }
        }
    }

    /**
     * Checks if a file has been named appropriately.
     *
     * @param name The filename.
     * @return True if the filename is valid, else false.
     */
    private boolean isValidFileName(String name) {
        return VALID_FILENAME_PATTERN.matcher(name).matches();
    }

    /**
     * Saves a game.
     *
     * @param game The game being saved.
     * @param type The quick-save type (Regular, Quick).
     */
    public void save(Game game, SaveType type) {
        createSaveFolderIfNotExists();

        String fileName = type == QUICKSAVE ? "quicksave.ser" : getFileNameFromUser();
        if (fileName == null) {
            System.out.println("Filename is null");
            return;
        }
        String filePath = SAVE_FOLDER + fileName;

        try (FileOutputStream fileOut = new FileOutputStream(filePath);
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
            objectOut.writeObject(game);
            System.out.println("Game saved successfully.");
        } catch (IOException e) {
            System.out.println("An error occured while saving: " + e.getMessage());
        }
    }

    /**
     * Gets a file name from the user.
     *
     * @return The file name;
     */
    private String getFileNameFromUser() {
        String fileName;
        do {
            System.out.print("Enter a file name: ");
            fileName = Scan.nextLine();
            if (isValidFileName(fileName)) {
                return fileName + ".ser";
            }
            System.out.println("Invalid file name. Only include letters, numbers, underscores, hyphens");
        } while (!isValidFileName(fileName));
        return null;
    }

    /**
     * Checks what kind of game to load, and loads it.
     * @param type The type of load (Regular, Quick).
     * @return The loaded game.
     */
    public Game load(SaveType type) {
        createSaveFolderIfNotExists();

        if (type == QUICKSAVE) {
            return loadQuickSave();
        } else {
            return loadRegularSave();
        }
    }

    /**
     * Logic for loading a quicksave.
     * @return The loaded game.
     */
    private Game loadQuickSave() {
        File file = new File(SAVE_FOLDER + "quicksave.ser");

        if (file.exists()) {
            return loadGameFromFile(file);
        } else {
            System.out.println("No quicksave found.");
            return null;
        }
    }

    /**
     * Logic for loading a regular save.
     * @return The loaded game.
     */
    private Game loadRegularSave() {
        File directory = new File(SAVE_FOLDER);
        File[] files = directory.listFiles();

        if (files == null || files.length == 0) {
            System.out.println("No saved files found.");
            return null;
        }

        System.out.println("Choose a file: (-1 : go back)");
        for (int i = 0; i < files.length; i++) {
            if (files[i].isFile()) {
                System.out.println("(" + i + ") " + files[i].getName());
            }
        }

        int input = Scan.nextInt();
        if (input == -1) {
            return null;
        }

        if (input >= 0 && input < files.length) {
            return loadGameFromFile(files[input]);
        } else {
            System.out.println("Invalid selection.");
            return null;
        }
    }

    /**
     * Loads a game from a file.
     * @param file The file.
     * @return The loaded game.
     */
    private Game loadGameFromFile(File file) {
        try (FileInputStream fileIn = new FileInputStream(file);
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
            Object loadedSave = objectIn.readObject();

            if (loadedSave instanceof Game) {
                System.out.println("Game loaded successfully.");
                return (Game) loadedSave;
            } else {
                System.out.println("Invalid game save format.");
                return null;
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("An error occurred while loading: " + e.getMessage());
            return null;
        }
    }
}



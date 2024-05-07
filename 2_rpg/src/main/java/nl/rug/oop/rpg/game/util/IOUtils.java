package nl.rug.oop.rpg.game.util;

import nl.rug.oop.rpg.game.Game;

import java.io.*;
import java.util.regex.Pattern;

import static nl.rug.oop.rpg.game.util.SaveType.QUICKSAVE;

public class IOUtils {
    private String SAVE_FOLDER = "savedgames/";
    private final Pattern VALID_FILENAME_PATTERN = Pattern.compile("^[a-zA-Z0-9_-]+$");

    public void createSaveFolderIfNotExists() {
        File folder = new File(SAVE_FOLDER);
        if (folder.exists() && !folder.mkdirs()) {
            System.out.println("Save folder created successfully.");
        } else {
            System.out.println("Failed to create save folder.");

        }
    }

    private boolean isValidFileName(String name) {
        return VALID_FILENAME_PATTERN.matcher(name).matches();
    }

    /**
     * @param game The game being saved.
     * @param type The quick-save type (Regular, QuickSave).
     */
    public void save(Game game, SaveType type) {
        createSaveFolderIfNotExists();

        String fileName = type == QUICKSAVE ? "quicksave.ser" : getFileNameFromUser();
        if(fileName == null) {
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

    public Game load(SaveType type) {
        createSaveFolderIfNotExists();

        if (type == QUICKSAVE) {
            File file = new File(SAVE_FOLDER + "quicksave.ser");

            if (file.exists()) {
                try {
                    FileInputStream fileInputStream = new FileInputStream(file);
                    ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

                    Object loadedSave = objectInputStream.readObject();

                    if (loadedSave instanceof Game) {
                        Game game = (Game) loadedSave;
                        System.out.println("Game loaded successfully.");
                        return game;
                    }
                } catch (IOException | ClassNotFoundException e) {
                    System.out.println("An error occured while quickloading: " + e.getMessage());
                }
            } else {
                System.out.println("No quicksave found.");
            }
            return null;
        } else {
            File directory = new File(SAVE_FOLDER);
            File[] files = directory.listFiles();

            if (files.length != 0) {
                System.out.println("Choose a file: (-1 : go back)");
                int i = 0;
                for (File file : files) {
                    if (file.isFile()) {
                        System.out.println("(" + i + ")" + file.getName());
                        i++;
                    }
                }
            } else {
                System.out.println("No saved files found.");
                return null;
            }

            int input = Scan.nextInt();
            if (input == -1) {
                return null;
            }

            try (FileInputStream fileIn = new FileInputStream(files[input]);
                 ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
                Game game = (Game) objectIn.readObject();
                System.out.println("Game loaded successfully.");
                return game;
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("An error occured while loading: " + e.getMessage());
                return null;
            }
        }
    }
}



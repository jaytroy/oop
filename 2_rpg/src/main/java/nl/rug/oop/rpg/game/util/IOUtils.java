package nl.rug.oop.rpg.game.util;

import nl.rug.oop.rpg.game.Game;

import java.io.*;
import java.util.regex.Pattern;

public class IOUtils {
    private static String SAVE_FOLDER = "savedgames/";
    private static final Pattern VALID_FILENAME_PATTERN = Pattern.compile("^[a-zA-Z0-9_-]+$");

    public static void createSaveFolderIfNotExists() {
        File folder = new File(SAVE_FOLDER);
        if (!folder.exists()) {
            if (folder.mkdirs()) {
                System.out.println("Save folder created successfully.");
            } else {
                System.out.println("Failed to create save folder.");
            }
        }
    }

    private static boolean isValidFileName(String name) {
        return VALID_FILENAME_PATTERN.matcher(name).matches();
    }

    /**
     * @param game The game being saved.
     * @param type 0 indicates quicksave, 1 indicates regular save
     */
    public static void save(Game game, int type) {
        createSaveFolderIfNotExists();
        String fileName;

        if (type == 0) {
            fileName = SAVE_FOLDER + "quicksave.ser";
            try (FileOutputStream fileOut = new FileOutputStream(fileName);
                 ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {

                objectOut.writeObject(game);
                System.out.println("Game saved successfully.");
            } catch (IOException e) {
                System.out.println("An error occured while quicksaving: " + e);
            }
        } else {
            //Check if filename matches pattern
            do {
                System.out.println("Enter a save file name: ");
                fileName = Scan.nextLine();
                if (isValidFileName(fileName)) {
                    break;
                }
                System.out.println("Invalid file name. Only include letters, numbers, underscores, hyphens");
            } while (!isValidFileName(fileName));

            String filePath = SAVE_FOLDER + fileName + ".ser";

            try (FileOutputStream fileOut = new FileOutputStream(filePath);
                 ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
                objectOut.writeObject(game);
                System.out.println("Game saved successfully.");
            } catch (IOException e) {
                System.out.println("An error occured while saving: " + e.getMessage());
            }
        }
    }

    public static Game load(int type) {
        if (type == 0) {
            File file = new File(SAVE_FOLDER + "quicksave.ser");

            if (file.exists()) {
                try {
                    FileInputStream fileInputStream = new FileInputStream(file);
                    ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

                    Object loadedSave = objectInputStream.readObject();

                    if(loadedSave instanceof Game) {
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



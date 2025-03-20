import java.io.*;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.*;

public class JsonStorage {
    private static final String STORAGE_FILE = "storagejson.json"; // Новое название файла
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    static void writeToFile() {
        try(Writer writer = new FileWriter(STORAGE_FILE))
        {
            gson.toJson(STORAGE_FILE, writer);
            System.out.println("Tasks written successfully!");
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    public static List<Note> loadTasks() {
        File file = new File(STORAGE_FILE);
        if (!file.exists()) {
            System.out.println("No saved tasks found.");
            return new java.util.ArrayList<>();
        }
        try (Reader reader = new FileReader(STORAGE_FILE)) {
            List<Note> tasks = gson.fromJson(reader, new TypeToken<List<Note>>() {}.getType());
            return tasks != null ? tasks : new java.util.ArrayList<>();
        } catch (IOException ex) {
            System.out.println("Error reading tasks: " + ex.getMessage());
            return new java.util.ArrayList<>();
        }
    }
}
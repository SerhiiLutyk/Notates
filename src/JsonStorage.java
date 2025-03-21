import java.io.*;
import java.util.List;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class JsonStorage {
    private static final String STORAGE_FILE = "storagejson.json";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static List<Note> tasks = new ArrayList<>(); // Ініціалізуємо список

    // Метод для додавання нотатки в список
    public static void addTask(Note note) {
        tasks.add(note);
    }

    // Метод для запису списку нотаток у JSON-файл
    public static void writeToFile() {
        try (Writer writer = new FileWriter(STORAGE_FILE)) {
            gson.toJson(tasks, writer); // Зберігаємо список нотаток у файл
            System.out.println("Tasks written successfully!");
        } catch (IOException ex) {
            System.out.println("Error writing tasks: " + ex.getMessage());
        }
    }

    public static List<Note> loadTasks() {
        File file = new File(STORAGE_FILE);
        if (!file.exists()) {
            System.out.println("No saved tasks found.");
            return new ArrayList<>();
        }
        try (Reader reader = new FileReader(STORAGE_FILE)) {
            tasks = gson.fromJson(reader, new TypeToken<List<Note>>() {}.getType());
            if (tasks == null) {
                tasks = new ArrayList<>();
            }
        } catch (IOException ex) {
            System.out.println("Error reading tasks: " + ex.getMessage());
        }
        return tasks;
    }


    public static List<Note> getTasks() {
        return tasks;
    }
}

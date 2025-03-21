import java.util.*;
import com.google.gson.Gson;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final TaskManager taskManager = new TaskManager();

    public static void main(String[] args) {
        taskManager.loadNotes();
        boolean running = true;
        while (running) {
            System.out.println("\nCommands: create, delete, read, update, search, sort, exit");
            System.out.print("Enter command: ");
            String command = scanner.nextLine().trim().toLowerCase();

            switch (command) {
                case "create":
                    System.out.print("Enter title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter content: ");
                    String content = scanner.nextLine();
                    taskManager.createNote(title, content);
                    break;
                case "delete":
                    System.out.print("Enter title of note to delete: ");
                    title = scanner.nextLine();
                    taskManager.deleteNote(title);
                    break;
                case "read":
                    taskManager.readNotes();
                    break;
                case "update":
                    System.out.print("Enter title of note to update: ");
                    title = scanner.nextLine();
                    System.out.print("Enter new content: ");
                    content = scanner.nextLine();
                    taskManager.updateNote(title, content);
                    break;
                case "search":
                    System.out.print("Search by 'title', 'content', or 'both'?: ");
                    String criteria = scanner.nextLine().trim().toLowerCase();
                    System.out.print("Enter keyword: ");
                    String keyword = scanner.nextLine();
                    taskManager.searchNotes(criteria, keyword);
                    break;
                case "sort":
                    System.out.print("Sort by 'date' or 'title'?: ");
                    criteria = scanner.nextLine().trim().toLowerCase();
                    taskManager.sortNotes(criteria);
                    break;
                case "exit":
                    taskManager.saveNotes();
                    System.out.println("Application closed");
                    running = false;
                    break;
                default:
                    System.out.println("Command not recognized!");
                    break;
            }
        }
    }
}

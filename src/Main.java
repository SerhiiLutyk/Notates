import java.util.*;

class Note {
    private String title;
    private String content;
    private Date createdAt;

    public Note(String title, String content) {
        this.title = title;
        this.content = content;
        this.createdAt = new Date();
    }

    public String getTitle() { return title; }
    public String getContent() { return content; }
    public Date getCreatedAt() { return createdAt; }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Title: " + title + "\nContent: " + content + "\nCreated At: " + createdAt;
    }
}

public class Main {
    private static List<Note> notes = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            System.out.println("\nCommands: create, delete, read, update, search, sort, exit");
            System.out.print("Enter command: ");
            String command = scanner.nextLine().trim().toLowerCase();

            switch (command) {
                case "create":
                    createNote();
                    break;
                case "delete":
                    deleteNote();
                    break;
                case "read":
                    readNotes();
                    break;
                case "update":
                    updateNote();
                    break;
                case "search":
                    searchNotes();
                    break;
                case "sort":
                    sortNotes();
                    break;
                case "exit":
                    System.out.println("Application closed");
                    running = false;
                    break;
                default:
                    System.out.println("Command not recognized!");
                    break;
            }
        }
    }

    private static void createNote() {
        System.out.print("Enter title: ");
        String title = scanner.nextLine();
        System.out.print("Enter content: ");
        String content = scanner.nextLine();
        notes.add(new Note(title, content));
        System.out.println("Note created.");
    }

    private static void deleteNote() {
        System.out.print("Enter title of note to delete: ");
        String title = scanner.nextLine();
        notes.removeIf(note -> note.getTitle().equalsIgnoreCase(title));
        System.out.println("Note deleted (if existed).");
    }

    private static void readNotes() {
        if (notes.isEmpty()) {
            System.out.println("No notes available.");
        } else {
            notes.forEach(System.out::println);
        }
    }

    private static void updateNote() {
        System.out.print("Enter title of note to update: ");
        String title = scanner.nextLine();
        for (Note note : notes) {
            if (note.getTitle().equalsIgnoreCase(title)) {
                System.out.print("Enter new content: ");
                note.setContent(scanner.nextLine());
                System.out.println("Note updated.");
                return;
            }
        }
        System.out.println("Note not found.");
    }

    private static void searchNotes() {
        System.out.print("Enter keyword: ");
        String keyword = scanner.nextLine().toLowerCase();
        for (Note note : notes) {
            if (note.getTitle().toLowerCase().contains(keyword) || note.getContent().toLowerCase().contains(keyword)) {
                System.out.println(note);
            }
        }
    }

    private static void sortNotes() {
        notes.sort(Comparator.comparing(Note::getCreatedAt));
        System.out.println("Notes sorted by date.");
    }
}

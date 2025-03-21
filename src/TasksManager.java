import java.util.*;
import com.google.gson.Gson;

class TaskManager {
    private List<Note> notes = new ArrayList<>();

    public void loadNotes() {
        notes = JsonStorage.loadTasks();
    }

    public void saveNotes() {
        JsonStorage.writeToFile();
    }

    public void createNote(String title, String content) {
        notes.add(new Note(title, content));
        System.out.println("Note created.");
    }

    public void deleteNote(String title) {
        notes.removeIf(note -> note.getTitle().equalsIgnoreCase(title));
        System.out.println("Note deleted (if existed).");
    }

    public void readNotes() {
        if (notes.isEmpty()) {
            System.out.println("No notes available.");
        } else {
            notes.forEach(System.out::println);
        }
    }

    public void updateNote(String title, String content) {
        for (Note note : notes) {
            if (note.getTitle().equalsIgnoreCase(title)) {
                note.setContent(content);
                System.out.println("Note updated.");
                return;
            }
        }
        System.out.println("Note not found.");
    }

    public void searchNotes(String criteria, String keyword) {
        List<Note> foundNotes = new ArrayList<>();
        keyword = keyword.toLowerCase();
        for (Note note : notes) {
            boolean match = switch (criteria) {
                case "title" -> note.getTitle().toLowerCase().contains(keyword);
                case "content" -> note.getContent().toLowerCase().contains(keyword);
                case "both" -> note.getTitle().toLowerCase().contains(keyword) || note.getContent().toLowerCase().contains(keyword);
                default -> false;
            };
            if (match) foundNotes.add(note);
        }

        if (foundNotes.isEmpty()) {
            System.out.println("No notes found.");
        } else {
            System.out.println("Search results:");
            foundNotes.forEach(System.out::println);
        }
    }

    public void sortNotes(String criteria) {
        switch (criteria) {
            case "date" -> notes.sort(Comparator.comparing(Note::getCreatedAt));
            case "title" -> notes.sort(Comparator.comparing(Note::getTitle, String.CASE_INSENSITIVE_ORDER));
            default -> {
                System.out.println("Invalid criteria. Sorting by date.");
                notes.sort(Comparator.comparing(Note::getCreatedAt));
            }
        }
        System.out.println("Notes sorted by " + criteria + ":");
        readNotes();
    }
}
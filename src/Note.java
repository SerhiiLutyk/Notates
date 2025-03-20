import java.util.Date;

public class Note {
     String title;
     String content;
     Date createdAt;

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

    public String toString() {
        return "Title: " + title + "\nContent: " + content + "\nCreated At: " + createdAt;
    }
}

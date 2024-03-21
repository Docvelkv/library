package docvel.library.entities;

import lombok.Data;

@Data
public class Book {

    private static long genId;

    private final long id;

    private final String author;

    private final String title;

    public Book(String author, String title) {
        this.id = genId++;
        this.author = author;
        this.title = title;
    }

    public Book(long id, String author, String title) {
        this.id = id;
        this.author = author;
        this.title = title;
    }
}

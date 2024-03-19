package docvel.library.controllers.book;

import lombok.Data;

@Data
public class BookRequest {

    private long id;
    private String author;
    private String title;
}

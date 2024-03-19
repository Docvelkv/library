package docvel.library.entities;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
public class Issue {

    private static long genId;

    @Setter(AccessLevel.NONE)
    private final long id;

    @Setter(AccessLevel.NONE)
    private final long readerId;

    @Setter(AccessLevel.NONE)
    private final long bookId;

    @Setter(AccessLevel.NONE)
    private final LocalDateTime dateOfIssuance;

    public Issue(long readerId, long bookId) {
        this.id = genId++;
        this.readerId = readerId;
        this.bookId = bookId;
        this.dateOfIssuance = LocalDateTime.now();
    }
}

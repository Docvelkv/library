package docvel.library.entities;

import lombok.Data;
import lombok.Setter;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Data
public class Issue {

    private static long genId;

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    private final long id;

    private final long readerId;

    private final long bookId;

    private final LocalDate dateOfIssuance;

    @Setter
    private LocalDate dateOfReturn;

    public Issue (long readerId, long bookId) {
        this.id = genId++;
        this.readerId = readerId;
        this.bookId = bookId;
        this.dateOfIssuance = LocalDate.now();
        this.dateOfReturn = null;
    }

}

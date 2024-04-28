package docvel.issueService.owner;

import docvel.issueService.providers.Book;
import docvel.issueService.providers.Reader;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "issues")
public class Issue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "reader")
    private Reader reader;

    @Column(name = "book")
    private Book book;

    @Column(name = "dateOfIssue")
    private LocalDate dateOfIssue = LocalDate.now();

    @Column(name = "dateOfReturn")
    private LocalDate dateOfReturn;
}

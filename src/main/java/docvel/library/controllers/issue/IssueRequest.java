package docvel.library.controllers.issue;

import lombok.Data;

import java.time.LocalDate;

@Data
public class IssueRequest {

    private long issueId;
    private long readerId;
    private long bookId;
    private LocalDate dateOfIssuance;
    private LocalDate dateOfReturn;
}

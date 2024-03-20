package docvel.library.repositories;

import docvel.library.entities.Issue;
import lombok.Data;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Repository
public class IssueRepository {

    private List<Issue> issues = new ArrayList<>();

    public void addNewIssue(Issue issue) {
        issues.add(issue);
    }

    public Issue findById(long id) {
        return issues.stream()
                .filter(issue -> issue.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public int countingNumOfBooks(long idReader){
        return issues.stream()
                .filter(issue -> issue.getReaderId() == idReader)
                .filter(issue -> issue.getDateOfReturn() == null)
                .toList().size();
    }

    public void returnOfBook(long idIssue){
        Issue issue = findById(idIssue);
        issue.setDateOfReturn(LocalDate.now());
    }
}

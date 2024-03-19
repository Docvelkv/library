package docvel.library.repositories;

import docvel.library.entities.Issue;
import lombok.Data;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Data
@Repository
public class IssueRepository {

    private List<Issue> issueRepository = new ArrayList<>();

    public void createIssue(Issue issue) {
        issueRepository.add(issue);
    }

    public Issue findById(long id) {
        return issueRepository.stream()
                .filter(issue -> issue.getId() == id)
                .findFirst()
                .orElse(null);
    }
}

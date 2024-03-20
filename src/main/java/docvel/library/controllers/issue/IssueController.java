package docvel.library.controllers.issue;

import docvel.library.entities.Issue;
import docvel.library.repositories.IssueRepository;
import docvel.library.services.IssueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("issue")
@RequiredArgsConstructor
public class IssueController {

    private final IssueService service;
    private final IssueRepository issues;

    @GetMapping
    public List<Issue> showAllIssues(){
        return issues.getIssues();
    }

    @GetMapping("{id}")
    public ResponseEntity<Issue> showIssue(@PathVariable long id){
        return ResponseEntity.ok(issues.findById(id));
    }

    @GetMapping("reader/{idReader}")
    public List<Issue> showIssuesForReader(@PathVariable long idReader){
        return issues.getIssues().stream()
                .filter(issue -> issue.getReaderId() == idReader)
                .collect(Collectors.toList());
    }

    @PostMapping("new")
    public ResponseEntity<Issue> createIssue(@RequestBody IssueRequest request){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.createIssue(request));
    }

    @PutMapping("return")
    public ResponseEntity<Issue> returnOfBook(@RequestBody IssueRequest request){
        return ResponseEntity.status(HttpStatus.OK).body(service.returnOfBook(request));
    }
}

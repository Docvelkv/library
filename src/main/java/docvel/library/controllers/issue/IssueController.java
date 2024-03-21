package docvel.library.controllers.issue;

import docvel.library.entities.Issue;
import docvel.library.services.IssueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("issue")
@RequiredArgsConstructor
public class IssueController {

    private final IssueService service;

    @GetMapping
    public ResponseEntity<List<Issue>> showAllIssues(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.showAllIssues());
    }

    @GetMapping("{id}")
    public ResponseEntity<Issue> showIssueById(@PathVariable long id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.showIssueById(id));
    }

    @GetMapping("reader/{idReader}")
    public ResponseEntity<List<Issue>> showIssuesByReaderId(@PathVariable long idReader){
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.showIssuesForReader(idReader));
    }

    @GetMapping("book/{idBook}")
    public ResponseEntity<List<Issue>> showIssuesByBookId(@PathVariable long idBook){
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.showIssuesForBook(idBook));
    }

    @PostMapping("new")
    public ResponseEntity<Issue> createIssue(@RequestBody IssueRequest request){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.createIssue(request));
    }

    @PutMapping("return")
    public ResponseEntity<Issue> returnOfBook(@RequestBody IssueRequest request){
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.returnOfBook(request));
    }
}

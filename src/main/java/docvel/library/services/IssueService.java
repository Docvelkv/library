package docvel.library.services;

import docvel.library.controllers.issue.IssueRequest;
import docvel.library.entities.Issue;
import docvel.library.repositories.BookRepository;
import docvel.library.repositories.IssueRepository;
import docvel.library.repositories.ReaderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class IssueService {
    private final ReaderRepository readerRepository;
    private final BookRepository bookRepository;
    private final IssueRepository issueRepository;

    public Issue createIssue(IssueRequest request) {
        if(readerRepository.findById(request.getReaderId())== null){
            log.info("Не найден читатель с id {}", request.getReaderId());
        }
        if(bookRepository.findById(request.getBookId())== null){
            log.info("Не найдена книга с id {}", request.getBookId());
        }

        Issue issue = new Issue(request.getReaderId(), request.getBookId());
        issueRepository.createIssue(issue);
        return issue;
    }
}

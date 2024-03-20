package docvel.library.services;

import docvel.library.controllers.issue.IssueRequest;
import docvel.library.entities.Issue;
import docvel.library.repositories.BookRepository;
import docvel.library.repositories.IssueRepository;
import docvel.library.repositories.ReaderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class IssueService {

    private final ReaderRepository readers;
    private final BookRepository books;
    private final IssueRepository issues;

    @Value(value = "${spring.application.maxAllowedBooks}")
    private int maxAllowedBooks;

    public Issue createIssue(IssueRequest request) {
        if(readers.findById(request.getReaderId())== null){
            log.info("Не найден читатель с id {}", request.getReaderId());
        }

        if(books.findById(request.getBookId())== null){
            log.info("Не найдена книга с id {}", request.getBookId());
        }

        if(issues.countingNumOfBooks(request.getReaderId()) <= maxAllowedBooks) {
            Issue issue = new Issue(request.getReaderId(), request.getBookId());
            issues.addNewIssue(issue);
            log.info("Читателю с id {} выдана книга с id {}",
                    request.getReaderId(),
                    request.getBookId());
            return issue;
        }

        log.info("У читателя с id {} - есть книги на руках. Выдача новых запрещена",
                request.getReaderId());
        return null;
    }

    public Issue returnOfBook(IssueRequest request){
        log.info("Читателем с id {} возвращена книга с id {}",
                request.getReaderId(),
                request.getBookId());
        Issue issue = issues.findById(request.getIssueId());
        issues.returnOfBook(request.getIssueId());
        return issue;
    }
}

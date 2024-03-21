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

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class IssueService {

    private final ReaderRepository readers;
    private final BookRepository books;
    private final IssueRepository issues;

    @Value("${spring.application.noBook}")
    private String noBook;

    @Value("${spring.application.noReader}")
    private String noReader;

    @Value(value = "${spring.application.maxAllowedBooks}")
    private int maxAllowedBooks;

    public List<Issue> showAllIssues(){
        log.info("Показаны все выдачи");
        return issues.getIssues();
    }

    public Issue showIssueById(long id){
        if(issues.findById(id) != null){
            log.info("Показана выдача с id {}", id);
            return issues.findById(id);
        }
        log.info("Выдача с id {} не найдена", id);
        return null;
    }

    public List<Issue> showIssuesForReader(long readerId){
        if(readers.findById(readerId) != null){
            log.info("Показаны все выдачи книг для читателя с id {}", readerId);
            return issues.getIssues().stream()
                    .filter(issue -> issue.getReaderId() == readerId)
                    .toList();
        }
        log.info(noReader, readerId);
        return null;
    }

    public List<Issue> showIssuesForBook(long bookId){
        if(books.findById(bookId) != null){
            log.info("Показаны все выдачи для книги с id {}", bookId);
            return issues.getIssues().stream()
                    .filter(issue -> issue.getBookId() == bookId)
                    .toList();
        }
        log.info(noBook, bookId);
        return null;
    }

    public Issue createIssue(IssueRequest request) {
        if(readers.findById(request.getReaderId())== null){
            log.info(noReader, request.getReaderId());
        }

        if(books.findById(request.getBookId())== null){
            log.info(noBook, request.getBookId());
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

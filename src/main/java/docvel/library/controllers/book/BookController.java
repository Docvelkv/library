package docvel.library.controllers.book;

import docvel.library.entities.Book;
import docvel.library.repositories.BookRepository;
import docvel.library.services.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("book")
public class BookController {

    private final BookRepository books;
    private final BookService service;

    @GetMapping
    public List<Book> getBooks(){
        return books.getBooks();
    }

    @GetMapping("{id}")
    public Book getBookById(@PathVariable long id){
        return books.findById(id);
    }

    @DeleteMapping("{id}")
    public void deleteBookById(@RequestBody BookRequest request){
        service.deleteBook(request);
    }

    @PostMapping("newBook")
    public ResponseEntity<Book> addNewBook(@RequestBody BookRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.newBook(request));
    }

    @PutMapping("updateBook")
    public ResponseEntity<Book> updateBook(@RequestBody BookRequest request){
        return ResponseEntity.status(HttpStatus.OK).body(service.updateBook(request));
    }
}

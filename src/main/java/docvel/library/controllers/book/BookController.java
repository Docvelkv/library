package docvel.library.controllers.book;

import docvel.library.entities.Book;
import docvel.library.repositories.BookRepository;
import docvel.library.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("book")
public class BookController {

    private final BookRepository books;
    private final BookService service;

    @GetMapping
    public ResponseEntity<List<Book>> getBooks(){
        return ResponseEntity.status(HttpStatus.OK).body(books.getBooks());
    }

    @GetMapping("{id}")
    public ResponseEntity<Book> getBookById(@PathVariable long id){
        return ResponseEntity.status(HttpStatus.OK).body(books.findById(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Book> deleteBookById(@PathVariable long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.deleteBook(id));
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

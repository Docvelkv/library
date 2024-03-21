package docvel.library.services;

import docvel.library.controllers.book.BookRequest;
import docvel.library.entities.Book;
import docvel.library.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class BookService {

    private final BookRepository books;

    @Value(value = "${spring.application.noBook}")
    private String noBook;

    public List<Book> showAllBooks(){
        log.info("Показаны все книги");
        return books.getBooks();
    }

    public Book showBookById(long id){
        if(books.findById(id) != null) {
            log.info("Показана книга с id {}", id);
            return books.findById(id);
        }
        log.info(noBook, id);
        return null;
    }

    public Book newBook(BookRequest request){
        Book book = new Book(request.getAuthor(), request.getTitle());
        books.addNewBook(book);
        log.info("Добавлена новая книга: автор - {}, название - {}",
                book.getAuthor(),
                book.getTitle());
        return book;
    }

    public Book updateBook(BookRequest request){
        Book book = books.findById(request.getId());
        if(book != null){
            Book newBook = new Book(request.getId(), request.getAuthor(), request.getTitle());
            books.deleteById(book.getId());
            books.addNewBook(newBook);
            log.info("Книга с id {} изменена. Новая книга: автор - {}, название - {}",
                    newBook.getId(),
                    newBook.getAuthor(),
                    newBook.getTitle());
            return newBook;
        }
        log.info(noBook, request.getId());
        return null;
    }

    public Book deleteBook(long id){
        Book book = books.findById(id);
        if(book != null){
            log.info("Удалена книга: автор - {}, название - {}",
                    book.getAuthor(),
                    book.getTitle());
            books.deleteById(book.getId());
        }
        log.info(noBook, id);
        return book;
    }
}

package docvel.library.services;

import docvel.library.controllers.book.BookRequest;
import docvel.library.entities.Book;
import docvel.library.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class BookService {

    private final BookRepository books;

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
        } else {
            log.info("Не удалось найти книгу с id {}", request.getId());
        }
        return null;
    }

    public Book deleteBook(BookRequest request){
        Book book = books.findById(request.getId());
        if(book != null){
            log.info("Удалена книга: автор - {}, название - {}",
                    book.getAuthor(),
                    book.getTitle());
            books.deleteById(book.getId());
        } else {
            log.info("Не удалось найти книгу с id {}", request.getId());
        }
        return book;
    }
}

package docvel.library.repositories;

import docvel.library.entities.Book;
import lombok.Data;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Data
@Repository
public class BookRepository {

    private final List<Book> books = new ArrayList<>();

    public BookRepository() {
        books.add(new Book("Айтматов Чингиз", "Плаха"));
        books.add(new Book("Ахматова Анна", "Реквием"));
        books.add(new Book("Оноре де Бальзак", "Утраченные иллюзии"));
        books.add(new Book("Берггольц Ольга", "Ленинградский дневник"));
        books.add(new Book("Блок Александр", "Двенадцать"));
        books.add(new Book("Богомолов Владимир", "В августе 44-го"));
        books.add(new Book("Боккаччо Джованни", "Декамерон"));
        books.add(new Book("Булгаков Михаил", "Мастер и Маргарита"));
        books.add(new Book("Бунин Иван", "Тёмные аллеи"));
        books.add(new Book("Гоголь Николай", "Мёртвые души"));
        books.add(new Book("Гомер", "Одиссея"));
    }

    public Book findById(long id) {
        return books.stream()
                .filter(b -> b.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void deleteById(long id) {
        books.removeIf(b -> b.getId() == id);
    }

    public void addNewBook(Book book) {
        books.add(book);
    }
}

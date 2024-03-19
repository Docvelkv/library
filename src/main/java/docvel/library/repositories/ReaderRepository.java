package docvel.library.repositories;

import docvel.library.entities.Reader;
import lombok.Data;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Data
@Repository
public class ReaderRepository {

    private final List<Reader> readers = new ArrayList<>();

    public ReaderRepository() {
        readers.add(new Reader("Константин"));
        readers.add(new Reader("Николай"));
        readers.add(new Reader("Евгений"));
        readers.add(new Reader("Пётр"));
        readers.add(new Reader("Иван"));
    }

    public Reader findById(long id) {
        return readers.stream()
                .filter(r -> r.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void deleteById(long id) {
        readers.removeIf(r -> r.getId() == id);
    }

    public void addNewReader(Reader reader) {
        readers.add(reader);
    }
}

package docvel.library.services;

import docvel.library.controllers.reader.ReaderRequest;
import docvel.library.entities.Reader;
import docvel.library.repositories.ReaderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReaderService {

    private final ReaderRepository readers;

    @Value(value = "${spring.application.noReader}")
    private String noReader;

    public List<Reader> showAllReaders() {
        log.info("Показаны все читатели");
        return readers.getReaders();
    }

    public Reader showReaderById(long id) {
        if (readers.findById(id) != null) {
            log.info("Показан читатель с id {}", id);
            return readers.findById(id);
        }
        log.info(noReader, id);
        return null;
    }

    public Reader newReader(ReaderRequest request) {
        Reader reader = new Reader(request.getName());
        readers.addNewReader(reader);
        log.info("Добавлен новый читатель {}", reader.getName());
        return reader;
    }

    public Reader updateReader(ReaderRequest request) {
        Reader reader = readers.findById(request.getId());
        if(reader != null){
            Reader newReader = new Reader(request.getId(), request.getName());
            readers.deleteById(reader.getId());
            readers.addNewReader(newReader);
            log.info("Читатель с id {} изменён. Нового читателя зовут {}",
                    newReader.getId(),
                    newReader.getName());
            return newReader;
        }else{
            log.info(noReader, request.getId());
        }
        return null;
    }

    public Reader deleteReader(long id) {
        Reader reader = readers.findById(id);
        if(reader != null){
            log.info("Удалён читатель с id {}", reader.getId());
            readers.deleteById(reader.getId());
        }
        log.info(noReader, id);
        return reader;
    }
}

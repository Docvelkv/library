package docvel.library.services;

import docvel.library.controllers.reader.ReaderRequest;
import docvel.library.entities.Reader;
import docvel.library.repositories.ReaderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReaderService {

    private final ReaderRepository readers;

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
            log.info("Читатель с id {} не найден", request.getId());
        }
        return null;
    }

    public Reader deleteReader(ReaderRequest request) {
        Reader reader = readers.findById(request.getId());
        if(reader != null){
            log.info("Удалён читатель с id {}", reader.getId());
            readers.deleteById(reader.getId());
        }else {
            log.info("Читатель с id {} не найден", request.getId());
        }
        return reader;
    }
}

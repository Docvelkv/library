package docvel.library.controllers.reader;

import docvel.library.entities.Reader;
import docvel.library.repositories.ReaderRepository;
import docvel.library.services.ReaderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("reader")
public class ReaderController {

    private final ReaderRepository readers;
    private final ReaderService service;

    @GetMapping
    public List<Reader> getReaders(){
        return readers.getReaders();
    }

    @GetMapping("{id}")
    public Reader getReaderById(@PathVariable long id){
        return readers.findById(id);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Reader> deleteReaderById(@PathVariable long id){
        return ResponseEntity.ok(service.deleteReader(id));
    }

    @PostMapping("newReader")
    public ResponseEntity<Reader> addNewReader(@RequestBody ReaderRequest request){
        return ResponseEntity.ok(service.newReader(request));
    }

    @PutMapping("updateReader")
    public ResponseEntity<Reader> updateReader(@RequestBody ReaderRequest request){
        return ResponseEntity.ok(service.updateReader(request));
    }
}

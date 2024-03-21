package docvel.library.controllers.reader;

import docvel.library.entities.Reader;
import docvel.library.services.ReaderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("reader")
public class ReaderController {

    private final ReaderService service;

    @GetMapping
    public ResponseEntity<List<Reader>> getReaders(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.showAllReaders());
    }

    @GetMapping("{id}")
    public ResponseEntity<Reader> getReaderById(@PathVariable long id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.showReaderById(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Reader> deleteReaderById(@PathVariable long id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.deleteReader(id));
    }

    @PostMapping("newReader")
    public ResponseEntity<Reader> addNewReader(@RequestBody ReaderRequest request){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.newReader(request));
    }

    @PutMapping("updateReader")
    public ResponseEntity<Reader> updateReader(@RequestBody ReaderRequest request){
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.updateReader(request));
    }
}

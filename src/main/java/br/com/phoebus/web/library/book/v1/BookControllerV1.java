package br.com.phoebus.web.library.book.v1;

import br.com.phoebus.web.library.book.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/books", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class BookControllerV1 {

    private final CreateBookService createBookService;
    private final ListBookService listBookService;
    private final DeleteBookService deleteBookService;
    private final UpdateBookService updateBookService;
    private final GetBookService getBookService;

    @PostMapping
    public BookDtoV1 create(@RequestBody BookDtoV1 bookDtoV1) throws Exception {
        return createBookService.create(bookDtoV1);
    }

    @PutMapping
    public void update(@RequestBody BookDtoV1 bookDtoV1) throws Exception {
        updateBookService.update(bookDtoV1);
    }

    @DeleteMapping
    public void delete(@RequestBody BookDtoV1 bookDtoV1) throws Exception {
        deleteBookService.delete(bookDtoV1);
    }

    @GetMapping(value = "/{id}")
    public BookDtoV1 get(@PathVariable("id") Long id) {
        return getBookService.get(id);
    }

    @GetMapping
    public List<BookDtoV1> listAll() {
        return listBookService.listAll();
    }
}















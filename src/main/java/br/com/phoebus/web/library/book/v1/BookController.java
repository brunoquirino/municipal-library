package br.com.phoebus.web.library.book.v1;

import br.com.phoebus.web.library.book.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/books", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class BookController {

    private final CreateBookService createBookService;
    private final ListBookService listBookService;
    private final DeleteBookService deleteBookService;
    private final UpdateBookService updateBookService;
    private final GetBookService getBookService;

    @PostMapping
    public BookDTO create(@RequestBody BookDTO bookDTO) throws Exception {
        return createBookService.create(bookDTO);
    }

    @GetMapping
    public List<BookDTO> listAll() {
        return listBookService.listAll();
    }

    @GetMapping(value = "/{id}")
    public BookDTO get(@PathVariable("id") Long id) {
        return getBookService.get(id);
    }

    @Transactional
    @DeleteMapping
    public void delete(@RequestBody BookDTO bookDTO) throws Exception {
        deleteBookService.delete(bookDTO);
    }

    @Transactional
    @PutMapping
    public void update(@RequestBody BookDTO bookDTO) throws Exception {
        updateBookService.update(bookDTO);
    }
}















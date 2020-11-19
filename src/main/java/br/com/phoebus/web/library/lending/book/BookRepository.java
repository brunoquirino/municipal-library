package br.com.phoebus.web.library.lending.book;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("municipal-library-book")
public interface BookRepository {

    @GetMapping(value = "/v1/books/{uuid}", consumes = MediaType.APPLICATION_JSON_VALUE)
    BookDto findById(@PathVariable("uuid") String bookID);

    @PutMapping(value = "/v1/books", consumes = MediaType.APPLICATION_JSON_VALUE)
    void update(@RequestBody BookDto book);

    @GetMapping(value = "/v1/books/lending/{uuid}", consumes = MediaType.APPLICATION_JSON_VALUE)
    List<BookDto> findAllByLendingID(@PathVariable("uuid") String uuid);
}

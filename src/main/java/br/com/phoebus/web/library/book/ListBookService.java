package br.com.phoebus.web.library.book;

import br.com.phoebus.web.library.book.v1.BookDtoV1;

import java.util.List;

@FunctionalInterface
public interface ListBookService {

    List<BookDtoV1> listAll();
}

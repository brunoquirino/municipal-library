package br.com.phoebus.web.library.book;

import br.com.phoebus.web.library.book.v1.BookDTO;

import java.util.List;

@FunctionalInterface
public interface ListBookService {

    List<BookDTO> listAll();
}

package br.com.phoebus.web.library.book;

import br.com.phoebus.web.library.book.v1.BookDTO;

@FunctionalInterface
public interface GetBookService {

    BookDTO get(Long id);
}

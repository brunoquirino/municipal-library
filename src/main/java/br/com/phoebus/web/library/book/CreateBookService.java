package br.com.phoebus.web.library.book;

import br.com.phoebus.web.library.book.v1.BookDtoV1;

@FunctionalInterface
public interface CreateBookService {

    BookDtoV1 create(BookDtoV1 book) throws Exception;
}

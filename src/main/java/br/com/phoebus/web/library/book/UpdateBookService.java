package br.com.phoebus.web.library.book;

import br.com.phoebus.web.library.book.v1.BookDtoV1;

@FunctionalInterface
public interface UpdateBookService {

    void update(BookDtoV1 bookDtoV1) throws Exception;
}

package br.com.phoebus.web.library.book;

import br.com.phoebus.web.library.book.v1.BookDtoV1;

@FunctionalInterface
public interface GetBookService {

    BookDtoV1 get(Long id);
}

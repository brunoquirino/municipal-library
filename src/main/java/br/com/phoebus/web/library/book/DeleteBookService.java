package br.com.phoebus.web.library.book;

import br.com.phoebus.web.library.book.v1.BookDTO;

@FunctionalInterface
public interface DeleteBookService {

    void delete(BookDTO bookDTO) throws Exception;
}

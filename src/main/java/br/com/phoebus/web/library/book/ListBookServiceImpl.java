package br.com.phoebus.web.library.book;

import br.com.phoebus.web.library.book.v1.BookDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ListBookServiceImpl implements ListBookService {

    private final BookRepository repository;

    @Override
    public List<BookDTO> listAll() {
        List<Book> books = (List<Book>) repository.findAll();
        List<BookDTO> booksTo = new ArrayList<BookDTO>();
        for (Book book : books) {
            BookDTO bookDTO = new BookDTO(book);

            booksTo.add(bookDTO);
        }

        return booksTo;
    }
}

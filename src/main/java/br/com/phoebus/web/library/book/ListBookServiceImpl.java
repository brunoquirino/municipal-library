package br.com.phoebus.web.library.book;

import br.com.phoebus.web.library.book.v1.BookDtoV1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ListBookServiceImpl implements ListBookService {

    private final BookRepository repository;

    @Override
    public List<BookDtoV1> listAll() {
        List<Book> books = (List<Book>) repository.findAll();
        List<BookDtoV1> booksTo = new ArrayList<BookDtoV1>();
        for (Book book : books) {
            BookDtoV1 bookDtoV1 = BookDtoV1.from(book);

            booksTo.add(bookDtoV1);
        }

        return booksTo;
    }
}

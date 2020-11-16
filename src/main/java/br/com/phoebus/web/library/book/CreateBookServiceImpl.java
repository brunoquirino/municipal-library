package br.com.phoebus.web.library.book;

import br.com.phoebus.web.library.book.v1.BookDtoV1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateBookServiceImpl implements CreateBookService {

    private final BookRepository bookRepository;

    @Override
    @Transactional
    public BookDtoV1 create(BookDtoV1 bookDtoV1) throws Exception {
        Book book = new Book();
        book.setTitle(bookDtoV1.getTitle());
        book.setSummary(bookDtoV1.getSummary());
        book.setIsbn(bookDtoV1.getIsbn());
        book.setAuthor(bookDtoV1.getAuthor());
        book.setYear(bookDtoV1.getYear());

        book = bookRepository.save(book);
        bookDtoV1.setId(book.getId());

        return bookDtoV1;
    }
}

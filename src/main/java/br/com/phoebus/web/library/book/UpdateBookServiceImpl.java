package br.com.phoebus.web.library.book;

import br.com.phoebus.web.library.book.v1.BookDtoV1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateBookServiceImpl implements UpdateBookService {

    private final BookRepository bookRepository;

    @Transactional
    @Override
    public void update(BookDtoV1 bookDtoV1) throws Exception {
        Book book = bookRepository.findById(bookDtoV1.getId()).orElse(new Book());
        book.setTitle(bookDtoV1.getTitle());
        book.setSummary(bookDtoV1.getSummary());
        book.setIsbn(bookDtoV1.getIsbn());
        book.setAuthor(bookDtoV1.getAuthor());
        book.setYear(bookDtoV1.getYear());

        bookRepository.save(book);
    }
}

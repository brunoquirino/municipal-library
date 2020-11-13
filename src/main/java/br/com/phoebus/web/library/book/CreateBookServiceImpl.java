package br.com.phoebus.web.library.book;

import br.com.phoebus.web.library.book.v1.BookDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateBookServiceImpl implements CreateBookService {

    private final BookRepository bookRepository;

    @Override
    @Transactional
    public BookDTO create(BookDTO bookDTO) throws Exception {
        Book book = new Book();
        book.setTitle(bookDTO.getTitle());
        book.setSummary(bookDTO.getSummary());
        book.setIsbn(bookDTO.getIsbn());
        book.setAuthor(bookDTO.getAuthor());
        book.setYear(bookDTO.getYear());

        book = bookRepository.save(book);
        bookDTO.setId(book.getId());

        return bookDTO;
    }
}

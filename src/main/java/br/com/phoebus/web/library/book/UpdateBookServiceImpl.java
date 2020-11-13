package br.com.phoebus.web.library.book;

import br.com.phoebus.web.library.book.v1.BookDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateBookServiceImpl implements UpdateBookService {

    private final BookRepository bookRepository;

    @Transactional
    @Override
    public void update(BookDTO bookDTO) throws Exception {
        Book book = bookRepository.findById(bookDTO.getId()).orElse(new Book());
        book.setTitle(bookDTO.getTitle());
        book.setSummary(bookDTO.getSummary());
        book.setIsbn(bookDTO.getIsbn());
        book.setAuthor(bookDTO.getAuthor());
        book.setYear(bookDTO.getYear());

        bookRepository.save(book);
    }
}

package br.com.phoebus.web.library.book;

import br.com.phoebus.web.library.book.v1.BookDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteBookServiceImpl implements DeleteBookService {

    private final BookRepository bookRepository;

    @Transactional
    @Override
    public void delete(BookDTO bookDTO) throws Exception {
        Book book = new Book();
        book.setId(bookDTO.getId());

        bookRepository.delete(book);
    }
}

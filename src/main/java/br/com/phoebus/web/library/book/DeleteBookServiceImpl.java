package br.com.phoebus.web.library.book;

import br.com.phoebus.web.library.book.v1.BookDtoV1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteBookServiceImpl implements DeleteBookService {

    private final BookRepository bookRepository;

    @Transactional
    @Override
    public void delete(BookDtoV1 bookDtoV1) throws Exception {
        Book book = new Book();
        book.setId(bookDtoV1.getId());

        bookRepository.delete(book);
    }
}

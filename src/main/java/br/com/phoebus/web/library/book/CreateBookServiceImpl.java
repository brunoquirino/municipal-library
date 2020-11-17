package br.com.phoebus.web.library.book;

import br.com.phoebus.web.library.book.v1.BookDtoV1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class CreateBookServiceImpl implements CreateBookService {

    private final BookRepository bookRepository;

    @Override
    public BookDtoV1 create(BookDtoV1 bookDtoV1) throws Exception {
        Book book = bookDtoV1.to();

        bookRepository.save(book);

        return BookDtoV1.from(book);
    }
}


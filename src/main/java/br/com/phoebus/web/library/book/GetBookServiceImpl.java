package br.com.phoebus.web.library.book;

import br.com.phoebus.web.library.book.v1.BookDtoV1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetBookServiceImpl implements GetBookService {

    private final BookRepository bookRepository;

    @Override
    public BookDtoV1 get(Long id) {
        Book book = bookRepository.findById(id).orElse(new Book());

        return BookDtoV1.from(book);
    }
}

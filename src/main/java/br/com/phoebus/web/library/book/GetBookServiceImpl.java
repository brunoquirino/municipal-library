package br.com.phoebus.web.library.book;

import br.com.phoebus.web.library.book.v1.BookDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetBookServiceImpl implements GetBookService {

    private final BookRepository bookRepository;

    @Override
    public BookDTO get(Long id) {
        Book book = bookRepository.findById(id).orElse(new Book());

        return new BookDTO(book);
    }
}

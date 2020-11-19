package br.com.phoebus.web.library.lending.lending;

import br.com.phoebus.web.library.lending.book.BookDto;
import br.com.phoebus.web.library.lending.book.BookRepository;
import br.com.phoebus.web.library.lending.lending.v1.LendingDtoV1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetLendingServiceImpl implements GetLendingService {

    private final LendingRepository repository;

    private final BookRepository bookRepository;

    @Override
    public LendingDtoV1 get(Long id) {
        Lending lending = repository.findById(id).orElse(new Lending());

        List<BookDto> books = bookRepository.findAllByLendingID(lending.getUuid().toString());
        lending.setBooks(books);

        return LendingDtoV1.from(lending);
    }
}

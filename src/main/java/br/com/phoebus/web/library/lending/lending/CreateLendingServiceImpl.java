package br.com.phoebus.web.library.lending.lending;

import br.com.phoebus.web.library.lending.book.BookDto;
import br.com.phoebus.web.library.lending.book.BookRepository;
import br.com.phoebus.web.library.lending.lending.v1.LendingDtoV1;
import br.com.phoebus.web.library.lending.user.UserDto;
import br.com.phoebus.web.library.lending.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class CreateLendingServiceImpl implements CreateLendingService {

    private final LendingRepository lendingRepository;

    private final UserRepository userRepository;

    private final BookRepository bookRepository;

    @Override
    public LendingDtoV1 create(LendingDtoV1 lendingDtoV1) throws Exception {
        Lending lending = new Lending();
        lending.setUuid(UUID.randomUUID());
        lending.setDays(lendingDtoV1.getDays());
        lending.setUserID(lendingDtoV1.getUserID());
        lending.setDateDelivery(LocalDate.now());
        lending.setBooks(lendingDtoV1.getBooks());

        UserDto user = userRepository.findById(lendingDtoV1.getUserID());
        if (user == null) {
            throw new Exception(String.format("Usuário %s não encontrado!", lendingDtoV1.getUserID()));
        }

        lendingRepository.save(lending);

        for (BookDto bookDtoV1 : lendingDtoV1.getBooks()) {
            BookDto book = bookRepository.findById(bookDtoV1.getUuid());
            if (book == null) {
                throw new Exception("Livro não encontrado!");
            } else {
                book.setLendingID(lending.getUuid().toString());
                bookRepository.update(book);
            }
        }


        return LendingDtoV1.from(lending);
    }
}

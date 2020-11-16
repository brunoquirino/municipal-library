package br.com.phoebus.web.library.lending;

import br.com.phoebus.web.library.book.Book;
import br.com.phoebus.web.library.book.BookRepository;
import br.com.phoebus.web.library.book.v1.BookDtoV1;
import br.com.phoebus.web.library.lending.v1.LendingDtoV1;
import br.com.phoebus.web.library.user.User;
import br.com.phoebus.web.library.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CreateLendingServiceImpl implements CreateLendingService {

    private final LendingRepository lendingRepository;

    private final UserRepository userRepository;

    private final BookRepository bookRepository;

    @Transactional
    @Override
    public LendingDtoV1 create(LendingDtoV1 lendingDtoV1) throws Exception {
        Lending lending = new Lending();
        lending.setDays(lendingDtoV1.getDays());
        lending.setUserID(lendingDtoV1.getUserID());
        lending.setDateDelivery(LocalDate.now());

        User user = userRepository.findById(lendingDtoV1.getUserID()).orElse(null);
        if (user != null) {
            lending.setUser(user);
        } else {
            throw new Exception(String.format("Usuário %d não encontrado!", lendingDtoV1.getUserID()));
        }

        List<Book> books = new ArrayList<Book>();
        for (BookDtoV1 bookDtoV1 : lendingDtoV1.getBooks()) {
            Book book = bookRepository.findById(bookDtoV1.getId()).orElse(null);
            if (book != null) {
                book.setLending(lending);
                books.add(book);
            }
        }
        lending.setBooks(books);

        lending = lendingRepository.save(lending);

        return LendingDtoV1.from(lending);
    }
}

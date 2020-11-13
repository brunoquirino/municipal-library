package br.com.phoebus.web.library.lending;

import br.com.phoebus.web.library.book.Book;
import br.com.phoebus.web.library.book.BookRepository;
import br.com.phoebus.web.library.book.v1.BookDTO;
import br.com.phoebus.web.library.lending.v1.LendingDTO;
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
    public LendingDTO create(LendingDTO lendingDTO) throws Exception {
        Lending lending = new Lending();
        lending.setDays(lendingDTO.getDays());
        lending.setUserID(lendingDTO.getUserID());
        lending.setDateDelivery(LocalDate.now());

        User user = userRepository.findById(lendingDTO.getUserID()).orElse(null);
        if (user != null) {
            lending.setUser(user);
        } else {
            throw new Exception(String.format("Usuário %d não encontrado!", lendingDTO.getUserID()));
        }

        List<Book> books = new ArrayList<Book>();
        for (BookDTO bookDTO : lendingDTO.getBooks()) {
            Book book = bookRepository.findById(bookDTO.getId()).orElse(null);
            if (book != null) {
                book.setLending(lending);
                books.add(book);
            }
        }
        lending.setBooks(books);

        lending = lendingRepository.save(lending);

        return new LendingDTO(lending);
    }
}

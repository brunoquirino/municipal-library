package br.com.phoebus.web.library.lending;

import br.com.phoebus.web.library.book.Book;
import br.com.phoebus.web.library.book.BookRepository;
import br.com.phoebus.web.library.book.v1.BookDtoV1;
import br.com.phoebus.web.library.lending.v1.LendingDtoV1;
import br.com.phoebus.web.library.user.User;
import br.com.phoebus.web.library.user.UserRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateLendingServiceTest {

    @Mock
    private LendingRepository lendingRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private BookRepository bookRepository;

    private CreateLendingService service;

    @BeforeEach
    public void setUp() {
        this.service = new CreateLendingServiceImpl(lendingRepository, userRepository, bookRepository);
    }

    @Test
    @DisplayName("Deve incluir um emprestimo")
    void create() throws Exception {
        BookDtoV1 book = new BookDtoV1();
        book.setId(1l);
        LendingDtoV1 newLending = LendingDtoV1.builder()
                .id(1l)
                .userID(1l)
                .days(7)
                .books(Arrays.asList(book))
                .build();

        when(userRepository.findById(newLending.getUserID())).thenReturn(Optional.of(new User()));
        when(bookRepository.findById(book.getId())).thenReturn(Optional.of(new Book()));

        service.create(newLending);

        ArgumentCaptor<Lending> lending = ArgumentCaptor.forClass(Lending.class);
        verify(lendingRepository).save(lending.capture());

        Lending saved = lending.getValue();
        assertAll("lending",
                () -> assertThat(saved.getUserID(), is(1l)),
                () -> assertThat(saved.getDays(), is(7)),
                () -> assertThat(saved.getBooks().size(), is(1)));
    }

    @Test
    @DisplayName("Deve retornar falha ao incluir um emprestimo com usuario inexistente")
    void createFail() throws Exception {
        LendingDtoV1 newLending = LendingDtoV1.builder()
                .id(1l)
                .userID(1l)
                .days(7)
                .build();

        Exception ex = assertThrows(Exception.class, () -> service.create(newLending));
        assertThat(ex.getMessage(), is("Usuário 1 não encontrado!"));
    }
}

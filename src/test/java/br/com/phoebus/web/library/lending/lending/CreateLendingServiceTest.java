package br.com.phoebus.web.library.lending.lending;

import br.com.phoebus.web.library.lending.book.BookDto;
import br.com.phoebus.web.library.lending.book.BookRepository;
import br.com.phoebus.web.library.lending.lending.v1.LendingDtoV1;
import br.com.phoebus.web.library.lending.user.UserDto;
import br.com.phoebus.web.library.lending.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

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
        BookDto bookDto = new BookDto();
        BookDto book = new BookDto();
        book.setUuid(UUID.randomUUID().toString());

        LendingDtoV1 newLending = LendingDtoV1.builder()
                .id(1l)
                .days(7)
                .userID(UUID.randomUUID().toString())
                .books(Arrays.asList(book))
                .build();

        when(userRepository.findById(newLending.getUserID())).thenReturn(new UserDto());
        when(bookRepository.findById(book.getUuid())).thenReturn(bookDto);

        service.create(newLending);

        ArgumentCaptor<Lending> lending = ArgumentCaptor.forClass(Lending.class);
        verify(lendingRepository).save(lending.capture());
        Lending saved = lending.getValue();

        bookDto.setLendingID(saved.getUuid().toString());
        verify(bookRepository).update(bookDto);

        assertAll("lending",
                () -> assertThat(saved.getUserID(), is(newLending.getUserID())),
                () -> assertThat(saved.getDays(), is(7)),
                () -> assertThat(saved.getBooks().size(), is(1)));
    }

    @Test
    @DisplayName("Deve retornar falha ao incluir um emprestimo com usuario inexistente")
    void createFail() throws Exception {
        String userID = UUID.randomUUID().toString();
        LendingDtoV1 newLending = LendingDtoV1.builder()
                .id(1l)
                .userID(userID)
                .days(7)
                .build();

        Exception ex = assertThrows(Exception.class, () -> service.create(newLending));
        assertThat(ex.getMessage(), is(String.format("Usuário %s não encontrado!", userID)));
    }
}

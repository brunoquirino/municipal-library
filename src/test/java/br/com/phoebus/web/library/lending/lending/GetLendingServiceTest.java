package br.com.phoebus.web.library.lending.lending;

import br.com.phoebus.web.library.lending.book.BookDto;
import br.com.phoebus.web.library.lending.book.BookRepository;
import br.com.phoebus.web.library.lending.lending.v1.LendingDtoV1;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetLendingServiceTest {

    @Mock
    private LendingRepository lendingRepository;

    @Mock
    private BookRepository bookRepository;

    private GetLendingService service;

    @BeforeEach
    public void setUp() {
        this.service = new GetLendingServiceImpl(lendingRepository, bookRepository);
    }

    @Test
    @DisplayName("Deve obter um emprestimo por id")
    void get() throws Exception {
        BookDto book = new BookDto();
        book.setUuid(UUID.randomUUID().toString());
        String userID = UUID.randomUUID().toString();
        List<BookDto> books = Arrays.asList(book);
        LendingDtoV1 lendingDtoV1 = LendingDtoV1.builder()
                .id(1l)
                .uuid(UUID.randomUUID())
                .days(7)
                .userID(userID)
                .dateDelivery(LocalDate.now())
                .books(books)
                .build();

        when(lendingRepository.findById(1l)).thenReturn(Optional.of(lendingDtoV1.to()));
        when(bookRepository.findAllByLendingID(lendingDtoV1.getUuid().toString())).thenReturn(books);

        LendingDtoV1 find = service.get(lendingDtoV1.getId());
        assertAll("lending",
                () -> assertThat(find.getUserID(), is(userID)),
                () -> assertThat(find.getDays(), is(7)),
                () -> assertThat(find.getDateDelivery(), is(LocalDate.now())),
                () -> assertThat(find.getBooks().size(), is(1)));
    }
}

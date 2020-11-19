package br.com.phoebus.web.library.lending.lending;

import br.com.phoebus.web.library.lending.book.BookDto;
import br.com.phoebus.web.library.lending.lending.v1.LendingDtoV1;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ListLendingServiceTest {

    @Mock
    private LendingRepository lendingRepository;

    private ListLendingService service;

    @BeforeEach
    public void setUp() {
        this.service = new ListLendingServiceImpl(lendingRepository);
    }

    @Test
    @DisplayName("Deve obter um emprestimo por id")
    void create() throws Exception {
        BookDto book = new BookDto();
        book.setUuid(UUID.randomUUID().toString());
        String userID = UUID.randomUUID().toString();
        LendingDtoV1 lendingDtoV1 = LendingDtoV1.builder()
                .id(1l)
                .days(7)
                .userID(userID)
                .dateDelivery(LocalDate.now())
                .books(Arrays.asList(book))
                .build();

        when(lendingRepository.findAll()).thenReturn(Arrays.asList(lendingDtoV1.to()));

        List<LendingDtoV1> lendings = service.listAll();
        LendingDtoV1 find = lendings.get(0);
        assertAll("lendings",
                () -> assertThat(lendings.size(), is(1)),
                () -> assertThat(find.getUserID(), is(userID)),
                () -> assertThat(find.getDays(), is(7)),
                () -> assertThat(find.getDateDelivery(), is(LocalDate.now())),
                () -> assertThat(find.getBooks().size(), is(1)));
    }
}

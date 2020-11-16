package br.com.phoebus.web.library.lending;

import br.com.phoebus.web.library.book.v1.BookDtoV1;
import br.com.phoebus.web.library.lending.v1.LendingDtoV1;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class GetLendingServiceTest {

    @Mock
    private GetLendingService getLendingService;

    @Test
    @DisplayName("Deve obter um emprestimo por id")
    void create() throws Exception {
        LendingDtoV1 lendingDtoV1 = getLendingDTO();
        lendingDtoV1.setId(1l);

        when(getLendingService.get(1l)).thenReturn(lendingDtoV1);

        LendingDtoV1 lendingTO = getLendingService.get(1l);

        assertAll("lending",
                () -> assertThat(lendingTO.getId(), is(lendingDtoV1.getId())),
                () -> assertThat(lendingTO.getUserID(), is(lendingDtoV1.getUserID())),
                () -> assertThat(lendingTO.getDays(), is(lendingDtoV1.getDays())),
                () -> assertThat(lendingTO.getBooks().size(), is(lendingDtoV1.getBooks().size())));
    }

    private LendingDtoV1 getLendingDTO() {
        LendingDtoV1 lendingDtoV1 = new LendingDtoV1();
        lendingDtoV1.setUserID(1l);
        lendingDtoV1.setDays(7);
        BookDtoV1 bookDtoV1 = new BookDtoV1();
        bookDtoV1.setId(1l);
        lendingDtoV1.setBooks(Arrays.asList(bookDtoV1));

        return lendingDtoV1;
    }
}

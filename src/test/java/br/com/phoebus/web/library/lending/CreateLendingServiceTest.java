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
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class CreateLendingServiceTest {

    @Mock
    private CreateLendingService createLendingService;

    @Test
    @DisplayName("Deve incluir um emprestimo")
    void create() throws Exception {
        LendingDtoV1 lendingDtoV1 = getLendingDTO();
        LendingDtoV1 lendingReturn = getLendingDTO();
        lendingReturn.setId(1l);

        when(createLendingService.create(lendingDtoV1)).thenReturn(lendingReturn);

        LendingDtoV1 lending = createLendingService.create(lendingDtoV1);
        assertAll("lending",
                () -> assertThat(lending.getId(), is(lendingReturn.getId())),
                () -> assertThat(lending.getUserID(), is(lendingReturn.getUserID())),
                () -> assertThat(lending.getDays(), is(lendingReturn.getDays())),
                () -> assertThat(lending.getBooks().size(), is(lendingReturn.getBooks().size())));
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

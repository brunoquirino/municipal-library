package br.com.phoebus.web.library.lending;

import br.com.phoebus.web.library.book.v1.BookDtoV1;
import br.com.phoebus.web.library.lending.v1.LendingDtoV1;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class ListLendingServiceTest {

    @Mock
    private ListLendingService listLendingService;

    @Test
    @DisplayName("Deve obter um emprestimo por id")
    void create() throws Exception {
        List<LendingDtoV1> list = Arrays.asList(getLendingDTO());
        when(listLendingService.listAll()).thenReturn(list);

        final List<LendingDtoV1> lendings = listLendingService.listAll();
        assertAll("lendings", () -> assertThat(lendings.size(), is(1)));
    }

    private LendingDtoV1 getLendingDTO() {
        LendingDtoV1 lendingDtoV1 = new LendingDtoV1();
        lendingDtoV1.setId(1l);
        lendingDtoV1.setUserID(1l);
        lendingDtoV1.setDays(7);
        BookDtoV1 bookDtoV1 = new BookDtoV1();
        bookDtoV1.setId(1l);
        lendingDtoV1.setBooks(Arrays.asList(bookDtoV1));

        return lendingDtoV1;
    }
}

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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class UpdateLendingServiceTest {

    @Mock
    private UpdateLendingService updateLendingService;

    @Mock
    private GetLendingService getLendingService;

    @Test
    @DisplayName("Deve alterar um emprestimo")
    void update() throws Exception {
        LendingDtoV1 lendingDtoV1 = new LendingDtoV1();
        lendingDtoV1.setId(1l);
        lendingDtoV1.setUserID(1l);
        lendingDtoV1.setDays(7);
        BookDtoV1 bookDtoV1 = new BookDtoV1();
        bookDtoV1.setId(1l);
        lendingDtoV1.setBooks(Arrays.asList(bookDtoV1));

        assertNotNull(this.updateLendingService);
        when(this.getLendingService.get(1l)).thenReturn(lendingDtoV1);
        doNothing().when(this.updateLendingService).update(lendingDtoV1);
    }
}

package br.com.phoebus.web.library.lending;

import br.com.phoebus.web.library.book.v1.BookDTO;
import br.com.phoebus.web.library.lending.v1.LendingDTO;
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
        LendingDTO lendingDTO = new LendingDTO();
        lendingDTO.setId(1l);
        lendingDTO.setUserID(1l);
        lendingDTO.setDays(7);
        lendingDTO.setBooks(Arrays.asList(new BookDTO(1l)));

        assertNotNull(this.updateLendingService);
        when(this.getLendingService.get(1l)).thenReturn(lendingDTO);
        doNothing().when(this.updateLendingService).update(lendingDTO);
    }
}

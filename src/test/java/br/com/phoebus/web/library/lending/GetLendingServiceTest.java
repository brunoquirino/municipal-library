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
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class GetLendingServiceTest {

    @Mock
    private GetLendingService getLendingService;

    @Test
    @DisplayName("Deve obter um emprestimo por id")
    void create() throws Exception {
        LendingDTO lendingDTO = getLendingDTO();
        lendingDTO.setId(1l);

        when(getLendingService.get(1l)).thenReturn(lendingDTO);

        LendingDTO lendingTO = getLendingService.get(1l);

        assertAll("lending",
                () -> assertThat(lendingTO.getId(), is(lendingDTO.getId())),
                () -> assertThat(lendingTO.getUserID(), is(lendingDTO.getUserID())),
                () -> assertThat(lendingTO.getDays(), is(lendingDTO.getDays())),
                () -> assertThat(lendingTO.getBooks().size(), is(lendingDTO.getBooks().size())));
    }

    private LendingDTO getLendingDTO() {
        LendingDTO lendingDTO = new LendingDTO();
        lendingDTO.setUserID(1l);
        lendingDTO.setDays(7);
        lendingDTO.setBooks(Arrays.asList(new BookDTO(1l)));

        return lendingDTO;
    }
}

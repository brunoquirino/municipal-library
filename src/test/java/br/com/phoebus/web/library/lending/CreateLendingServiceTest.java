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
        LendingDTO lendingDTO = getLendingDTO();
        LendingDTO lendingReturn = getLendingDTO();
        lendingReturn.setId(1l);

        when(createLendingService.create(lendingDTO)).thenReturn(lendingReturn);

        LendingDTO lending = createLendingService.create(lendingDTO);
        assertAll("lending",
                () -> assertThat(lending.getId(), is(lendingReturn.getId())),
                () -> assertThat(lending.getUserID(), is(lendingReturn.getUserID())),
                () -> assertThat(lending.getDays(), is(lendingReturn.getDays())),
                () -> assertThat(lending.getBooks().size(), is(lendingReturn.getBooks().size())));
    }

    private LendingDTO getLendingDTO() {
        LendingDTO lendingDTO = new LendingDTO();
        lendingDTO.setUserID(1l);
        lendingDTO.setDays(7);
        lendingDTO.setBooks(Arrays.asList(new BookDTO(1l)));

        return lendingDTO;
    }
}

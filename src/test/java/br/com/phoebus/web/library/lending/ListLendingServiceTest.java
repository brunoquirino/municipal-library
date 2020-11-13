package br.com.phoebus.web.library.lending;

import br.com.phoebus.web.library.book.v1.BookDTO;
import br.com.phoebus.web.library.lending.v1.LendingDTO;
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
        List<LendingDTO> list = Arrays.asList(getLendingDTO());
        when(listLendingService.listAll()).thenReturn(list);

        final List<LendingDTO> lendings = listLendingService.listAll();
        assertAll("lendings", () -> assertThat(lendings.size(), is(1)));
    }

    private LendingDTO getLendingDTO() {
        LendingDTO lendingDTO = new LendingDTO(1l);
        lendingDTO.setUserID(1l);
        lendingDTO.setDays(7);
        lendingDTO.setBooks(Arrays.asList(new BookDTO(1l)));

        return lendingDTO;
    }
}

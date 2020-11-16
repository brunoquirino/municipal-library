package br.com.phoebus.web.library.lending;

import br.com.phoebus.web.library.lending.v1.LendingDtoV1;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.doNothing;

@SpringBootTest
@AutoConfigureMockMvc
public class DeleteLendingServiceTest {

    @Mock
    private DeleteLendingService deleteLendingService;

    @Mock
    private GetLendingService getLendingService;

    @Test
    @DisplayName("Deve deletar um emprestimo")
    void delete() throws Exception {
        LendingDtoV1 lendingDtoV1 = new LendingDtoV1();
        lendingDtoV1.setId(1l);

        doNothing().when(this.deleteLendingService).delete(lendingDtoV1);
    }
}

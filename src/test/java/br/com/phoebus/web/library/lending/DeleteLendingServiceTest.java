package br.com.phoebus.web.library.lending;

import br.com.phoebus.web.library.lending.v1.LendingDtoV1;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class DeleteLendingServiceTest {

    @Mock
    private LendingRepository lendingRepository;

    private DeleteLendingService service;

    @BeforeEach
    public void setUp() {
        this.service = new DeleteLendingServiceImpl(lendingRepository);
    }

    @Test
    @DisplayName("Deve deletar um emprestimo")
    void delete() throws Exception {
        LendingDtoV1 lendingDtoV1 = LendingDtoV1.builder()
                .id(1l)
                .build();

        service.delete(lendingDtoV1);

        Lending lending = new Lending();
        lending.setId(lendingDtoV1.getId());
        verify(lendingRepository).delete(lending);
    }
}

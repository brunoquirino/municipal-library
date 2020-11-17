package br.com.phoebus.web.library.lending;

import br.com.phoebus.web.library.lending.v1.LendingDtoV1;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UpdateLendingServiceTest {

    @Mock
    private LendingRepository lendingRepository;

    private UpdateLendingService service;

    @BeforeEach
    public void setUp() {
        this.service = new UpdateLendingServiceImpl(lendingRepository);
    }

    @Test
    @DisplayName("Deve alterar um emprestimo")
    void update() throws Exception {
        LendingDtoV1 lendingDtoV1 = LendingDtoV1.builder()
                .id(1l)
                .dateDelivery(LocalDate.now())
                .build();

        Lending lending = new Lending();
        when(lendingRepository.findById(lendingDtoV1.getId())).thenReturn(Optional.of(lending));

        service.update(lendingDtoV1);

        verify(lendingRepository).save(lending);
    }

    @Test
    @DisplayName("Deve retonar um erro ao alterar um emprestimo inexistente")
    void updateFail() throws Exception {
        LendingDtoV1 lendingDtoV1 = LendingDtoV1.builder()
                .dateDelivery(LocalDate.now())
                .build();

        Exception ex = assertThrows(Exception.class, () -> service.update(lendingDtoV1));
        assertThat(ex.getMessage(), is("Empréstimo não encontrado!"));
    }
}

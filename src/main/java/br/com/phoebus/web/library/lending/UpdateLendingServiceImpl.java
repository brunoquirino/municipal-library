package br.com.phoebus.web.library.lending;

import br.com.phoebus.web.library.lending.v1.LendingDtoV1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UpdateLendingServiceImpl implements UpdateLendingService {

    private final LendingRepository lendingRepository;

    @Override
    public void update(LendingDtoV1 lendingDtoV1) throws Exception {
        Lending lending = lendingRepository.findById(lendingDtoV1.getId()).orElse(null);
        if (lending == null) {
            throw new Exception("Empréstimo não encontrado!");
        }

        lending.setDateDevolution(lendingDtoV1.getDateDevolution());

        lendingRepository.save(lending);
    }
}

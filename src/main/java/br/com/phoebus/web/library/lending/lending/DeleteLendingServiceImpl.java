package br.com.phoebus.web.library.lending.lending;

import br.com.phoebus.web.library.lending.lending.v1.LendingDtoV1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class DeleteLendingServiceImpl implements DeleteLendingService {

    private final LendingRepository lendingRepository;

    @Override
    public void delete(LendingDtoV1 lendingDtoV1) throws Exception {
        Lending lending = new Lending();
        lending.setId(lendingDtoV1.getId());

        lendingRepository.delete(lending);
    }
}

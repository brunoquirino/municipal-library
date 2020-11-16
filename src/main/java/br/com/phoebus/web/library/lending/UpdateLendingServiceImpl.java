package br.com.phoebus.web.library.lending;

import br.com.phoebus.web.library.lending.v1.LendingDtoV1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateLendingServiceImpl implements UpdateLendingService {

    private final LendingRepository lendingRepository;

    @Transactional
    @Override
    public void update(LendingDtoV1 lendingDtoV1) throws Exception {
        Lending lending = new Lending();
        lending.setId(lendingDtoV1.getId());

        lendingRepository.save(lending);
    }
}

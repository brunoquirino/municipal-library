package br.com.phoebus.web.library.lending;

import br.com.phoebus.web.library.lending.v1.LendingDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteLendingServiceImpl implements DeleteLendingService {

    private final LendingRepository lendingRepository;

    @Transactional
    @Override
    public void delete(LendingDTO lendingDTO) throws Exception {
        Lending lending = new Lending();
        lending.setId(lendingDTO.getId());

        lendingRepository.delete(lending);
    }
}

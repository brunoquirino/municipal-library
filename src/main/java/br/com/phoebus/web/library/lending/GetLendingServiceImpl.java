package br.com.phoebus.web.library.lending;

import br.com.phoebus.web.library.lending.v1.LendingDtoV1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetLendingServiceImpl implements GetLendingService {

    private final LendingRepository repository;

    @Override
    public LendingDtoV1 get(Long id) {
        Lending lending = repository.findById(id).orElse(new Lending());

        return LendingDtoV1.from(lending);
    }
}

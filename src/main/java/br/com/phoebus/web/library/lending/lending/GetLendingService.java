package br.com.phoebus.web.library.lending.lending;

import br.com.phoebus.web.library.lending.lending.v1.LendingDtoV1;

@FunctionalInterface
public interface GetLendingService {
    LendingDtoV1 get(Long id);
}

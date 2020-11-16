package br.com.phoebus.web.library.lending;

import br.com.phoebus.web.library.lending.v1.LendingDtoV1;

@FunctionalInterface
public interface GetLendingService {
    LendingDtoV1 get(Long id);
}

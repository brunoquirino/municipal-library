package br.com.phoebus.web.library.lending.lending;

import br.com.phoebus.web.library.lending.lending.v1.LendingDtoV1;

@FunctionalInterface
public interface CreateLendingService {
    LendingDtoV1 create(LendingDtoV1 lendingDtoV1) throws Exception;
}

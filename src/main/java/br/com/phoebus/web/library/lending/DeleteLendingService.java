package br.com.phoebus.web.library.lending;

import br.com.phoebus.web.library.lending.v1.LendingDtoV1;

@FunctionalInterface
public interface DeleteLendingService {
    void delete(LendingDtoV1 lendingDtoV1) throws Exception;
}

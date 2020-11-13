package br.com.phoebus.web.library.lending;

import br.com.phoebus.web.library.lending.v1.LendingDTO;

@FunctionalInterface
public interface GetLendingService {
    LendingDTO get(Long id);
}

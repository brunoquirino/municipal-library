package br.com.phoebus.web.library.lending;

import br.com.phoebus.web.library.lending.v1.LendingDTO;

@FunctionalInterface
public interface CreateLendingService {
    LendingDTO create(LendingDTO lendingDTO) throws Exception;
}

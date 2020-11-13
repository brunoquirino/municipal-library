package br.com.phoebus.web.library.lending;

import br.com.phoebus.web.library.lending.v1.LendingDTO;

import java.util.List;

@FunctionalInterface
public interface ListLendingService {

    List<LendingDTO> listAll();
}

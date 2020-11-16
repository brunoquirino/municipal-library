package br.com.phoebus.web.library.lending;

import br.com.phoebus.web.library.lending.v1.LendingDtoV1;

import java.util.List;

@FunctionalInterface
public interface ListLendingService {

    List<LendingDtoV1> listAll();
}

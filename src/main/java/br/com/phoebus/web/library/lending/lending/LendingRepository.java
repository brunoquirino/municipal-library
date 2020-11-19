package br.com.phoebus.web.library.lending.lending;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LendingRepository extends CrudRepository<Lending, Long> {

}

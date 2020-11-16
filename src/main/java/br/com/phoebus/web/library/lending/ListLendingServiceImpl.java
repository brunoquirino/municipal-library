package br.com.phoebus.web.library.lending;

import br.com.phoebus.web.library.lending.v1.LendingDtoV1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ListLendingServiceImpl implements ListLendingService {

    private final LendingRepository lendingRepository;

    @Override
    public List<LendingDtoV1> listAll() {
        List<Lending> lendings = (List<Lending>) lendingRepository.findAll();
        List<LendingDtoV1> list = new ArrayList<>();
        for (Lending lending : lendings) {
            LendingDtoV1 lendingDtoV1 = LendingDtoV1.from(lending);
            list.add(lendingDtoV1);
        }

        return list;
    }
}

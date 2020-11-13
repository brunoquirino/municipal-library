package br.com.phoebus.web.library.lending;

import br.com.phoebus.web.library.lending.v1.LendingDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ListLendingServiceImpl implements ListLendingService {

    private final LendingRepository lendingRepository;

    @Override
    public List<LendingDTO> listAll() {
        List<Lending> lendings = (List<Lending>) lendingRepository.findAll();
        List<LendingDTO> list = new ArrayList<>();
        for (Lending lending : lendings) {
            LendingDTO lendingDTO = new LendingDTO(lending);
            list.add(lendingDTO);
        }

        return list;
    }
}

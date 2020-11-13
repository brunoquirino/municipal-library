package br.com.phoebus.web.library.lending.v1;

import br.com.phoebus.web.library.lending.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/lendings", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class LendingController {

    private final CreateLendingService createLedingService;
    private final UpdateLendingService updateLedingService;
    private final DeleteLendingService deleteLedingService;
    private final GetLendingService getLedingService;
    private final ListLendingService listLedingService;

    @GetMapping
    public List<LendingDTO> listAll() {
        return listLedingService.listAll();
    }

    @GetMapping(value = "/{id}")
    public LendingDTO get(@PathVariable("id") Long id) {
        return getLedingService.get(id);
    }

    @PostMapping
    public LendingDTO create(@RequestBody LendingDTO lendingDTO) throws Exception {
        return createLedingService.create(lendingDTO);
    }

    @DeleteMapping
    public void delete(@RequestBody LendingDTO lendingDTO) throws Exception {
        deleteLedingService.delete(lendingDTO);
    }

    @PutMapping
    public void update(@RequestBody LendingDTO lendingDTO) throws Exception {
        updateLedingService.update(lendingDTO);
    }
}

package br.com.phoebus.web.library.lending.lending.v1;

import br.com.phoebus.web.library.lending.lending.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/lendings", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class LendingControllerV1 {

    private final CreateLendingService createLedingService;
    private final UpdateLendingService updateLedingService;
    private final DeleteLendingService deleteLedingService;
    private final GetLendingService getLedingService;
    private final ListLendingService listLedingService;

    @GetMapping
    public List<LendingDtoV1> listAll() {
        return listLedingService.listAll();
    }

    @GetMapping(value = "/{id}")
    public LendingDtoV1 get(@PathVariable("id") Long id) {
        return getLedingService.get(id);
    }

    @PostMapping
    public LendingDtoV1 create(@RequestBody LendingDtoV1 lendingDtoV1) throws Exception {
        return createLedingService.create(lendingDtoV1);
    }

    @DeleteMapping
    public void delete(@RequestBody LendingDtoV1 lendingDtoV1) throws Exception {
        deleteLedingService.delete(lendingDtoV1);
    }

    @PutMapping
    public void update(@RequestBody LendingDtoV1 lendingDtoV1) throws Exception {
        updateLedingService.update(lendingDtoV1);
    }
}

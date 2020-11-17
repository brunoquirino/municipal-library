package br.com.phoebus.web.library.user.v1;

import br.com.phoebus.web.library.user.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/users", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserControllerV1 {

    private final CreateUserService createUserService;
    private final ListUserService listUserService;
    private final DeleteUserService deleteUserService;
    private final UpdateUserService updateUserService;
    private final GetUserService getUserService;

    @GetMapping
    public List<UserDtoV1> listAll() {
        return listUserService.listAll();
    }

    @GetMapping(value = "/{id}")
    public UserDtoV1 get(@PathVariable("id") Long id) {
        return getUserService.get(id);
    }

    @PostMapping
    public UserDtoV1 create(@RequestBody UserDtoV1 userDtoV1) throws Exception {
        return createUserService.create(userDtoV1);
    }

    @DeleteMapping
    public void delete(@RequestBody UserDtoV1 userDtoV1) throws Exception {
        deleteUserService.delete(userDtoV1);
    }

    @PutMapping
    public void update(@RequestBody UserDtoV1 userDtoV1) throws Exception {
        updateUserService.update(userDtoV1);
    }
}

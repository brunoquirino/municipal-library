package br.com.phoebus.web.library.user.v1;

import br.com.phoebus.web.library.user.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private final CreateUserService createUserService;
    private final ListUserService listUserService;
    private final DeleteUserService deleteUserService;
    private final UpdateUserService updateUserService;
    private final GetUserService getUserService;

    @GetMapping
    public List<UserDTO> listAll() {
        return listUserService.listAll();
    }

    @GetMapping(value = "/{id}")
    public UserDTO get(@PathVariable("id") Long id) {
        return getUserService.get(id);
    }

    @PostMapping
    public UserDTO create(@RequestBody UserDTO userDTO) throws Exception {
        return createUserService.create(userDTO);
    }

    @DeleteMapping
    public void delete(@RequestBody UserDTO userDTO) throws Exception {
        deleteUserService.delete(userDTO);
    }

    @PutMapping
    public void update(@RequestBody UserDTO userDTO) throws Exception {
        updateUserService.update(userDTO);
    }
}

package br.com.phoebus.web.library.user;

import br.com.phoebus.web.library.user.v1.UserDTO;

@FunctionalInterface
public interface CreateUserService {

    UserDTO create(UserDTO userDTO) throws Exception;
}

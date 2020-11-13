package br.com.phoebus.web.library.user;

import br.com.phoebus.web.library.user.v1.UserDTO;

@FunctionalInterface
public interface GetUserService {

    UserDTO get(Long id);
}

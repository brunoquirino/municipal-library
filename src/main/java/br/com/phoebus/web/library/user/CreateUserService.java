package br.com.phoebus.web.library.user;

import br.com.phoebus.web.library.user.v1.UserDtoV1;

@FunctionalInterface
public interface CreateUserService {

    UserDtoV1 create(UserDtoV1 userDtoV1) throws Exception;
}

package br.com.phoebus.web.library.user;

import br.com.phoebus.web.library.user.v1.UserDtoV1;

@FunctionalInterface
public interface DeleteUserService {

    void delete(UserDtoV1 userDtoV1) throws Exception;
}

package br.com.phoebus.web.library.user;

import br.com.phoebus.web.library.user.v1.UserDtoV1;

@FunctionalInterface
public interface UpdateUserService {

    void update(UserDtoV1 userDtoV1) throws Exception;
}

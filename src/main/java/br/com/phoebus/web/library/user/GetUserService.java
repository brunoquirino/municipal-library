package br.com.phoebus.web.library.user;

import br.com.phoebus.web.library.user.v1.UserDtoV1;

@FunctionalInterface
public interface GetUserService {

    UserDtoV1 get(Long id);
}

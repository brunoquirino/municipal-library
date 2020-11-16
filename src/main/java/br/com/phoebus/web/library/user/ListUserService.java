package br.com.phoebus.web.library.user;

import br.com.phoebus.web.library.user.v1.UserDtoV1;
import java.util.List;

@FunctionalInterface
public interface ListUserService {

    List<UserDtoV1> listAll();
}

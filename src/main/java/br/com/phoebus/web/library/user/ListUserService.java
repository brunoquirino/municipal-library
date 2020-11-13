package br.com.phoebus.web.library.user;

import br.com.phoebus.web.library.user.v1.UserDTO;
import java.util.List;

@FunctionalInterface
public interface ListUserService {

    List<UserDTO> listAll();
}

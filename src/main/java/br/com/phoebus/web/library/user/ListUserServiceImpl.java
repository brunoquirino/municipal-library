package br.com.phoebus.web.library.user;

import br.com.phoebus.web.library.user.v1.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ListUserServiceImpl implements ListUserService {

    private final UserRepository userRepository;

    @Override
    public List<UserDTO> listAll() {
        List<User> users = (List<User>) userRepository.findAll();
        List<UserDTO> list = new ArrayList<>();
        for (User user : users) {
            UserDTO userDTO = new UserDTO(user);
            list.add(userDTO);
        }

        return list;
    }
}

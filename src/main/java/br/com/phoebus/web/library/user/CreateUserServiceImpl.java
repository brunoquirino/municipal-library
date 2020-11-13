package br.com.phoebus.web.library.user;

import br.com.phoebus.web.library.user.v1.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateUserServiceImpl implements CreateUserService {

    private final UserRepository userRepository;

    @Transactional
    @Override
    public UserDTO create(UserDTO userDTO) throws Exception {
        User user = new User();
        user.setName(userDTO.getName());
        user.setAge(userDTO.getAge());
        user.setPhone(userDTO.getPhone());

        user = userRepository.save(user);
        return new UserDTO(user);
    }
}

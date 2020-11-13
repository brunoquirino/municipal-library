package br.com.phoebus.web.library.user;

import br.com.phoebus.web.library.user.v1.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateUserServiceImpl implements UpdateUserService {

    private final UserRepository userRepository;

    @Transactional
    @Override
    public void update(UserDTO userDTO) throws Exception {
        User user = new User();
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setAge(userDTO.getAge());
        user.setPhone(userDTO.getPhone());

        userRepository.save(user);
    }
}

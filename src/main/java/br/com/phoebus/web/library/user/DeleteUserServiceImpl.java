package br.com.phoebus.web.library.user;

import br.com.phoebus.web.library.user.v1.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteUserServiceImpl implements DeleteUserService {

    private final UserRepository userRepository;

    @Transactional
    @Override
    public void delete(UserDTO userDTO) throws Exception {
        User user = new User();
        user.setId(userDTO.getId());

        userRepository.delete(user);
    }
}

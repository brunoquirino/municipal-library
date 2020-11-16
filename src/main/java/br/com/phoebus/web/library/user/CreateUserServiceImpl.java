package br.com.phoebus.web.library.user;

import br.com.phoebus.web.library.user.v1.UserDtoV1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateUserServiceImpl implements CreateUserService {

    private final UserRepository userRepository;

    @Transactional
    @Override
    public UserDtoV1 create(UserDtoV1 userDtoV1) throws Exception {
        User user = new User();
        user.setName(userDtoV1.getName());
        user.setAge(userDtoV1.getAge());
        user.setPhone(userDtoV1.getPhone());

        user = userRepository.save(user);
        return UserDtoV1.from(user);
    }
}

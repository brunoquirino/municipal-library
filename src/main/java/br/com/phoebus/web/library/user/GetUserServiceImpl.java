package br.com.phoebus.web.library.user;

import br.com.phoebus.web.library.user.v1.UserDtoV1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetUserServiceImpl implements GetUserService {

    private final UserRepository userRepository;

    @Override
    public UserDtoV1 get(Long id) {
        User user = userRepository.findById(id).orElse(new User());

        return UserDtoV1.from(user);
    }
}

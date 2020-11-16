package br.com.phoebus.web.library.user;

import br.com.phoebus.web.library.user.v1.UserDtoV1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ListUserServiceImpl implements ListUserService {

    private final UserRepository userRepository;

    @Override
    public List<UserDtoV1> listAll() {
        List<User> users = (List<User>) userRepository.findAll();
        List<UserDtoV1> list = new ArrayList<>();
        for (User user : users) {
            UserDtoV1 userDtoV1 = UserDtoV1.from(user);
            list.add(userDtoV1);
        }

        return list;
    }
}

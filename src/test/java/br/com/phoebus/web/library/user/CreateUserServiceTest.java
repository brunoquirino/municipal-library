package br.com.phoebus.web.library.user;

import br.com.phoebus.web.library.user.v1.UserDtoV1;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CreateUserServiceTest {

    @Mock
    private UserRepository userRepository;

    private CreateUserService service;

    @BeforeEach
    public void setUp() {
        this.service = new CreateUserServiceImpl(userRepository);
    }

    @Test
    @DisplayName("Deve incluir um user")
    void create() throws Exception {
        UserDtoV1 newUser = UserDtoV1.builder()
                .name("Teste")
                .age(30)
                .phone("88888888888888")
                .build();

        service.create(newUser);

        ArgumentCaptor<User> user = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(user.capture());
        User saved = user.getValue();

        assertAll("user",
                () -> assertThat(saved.getName(), is(newUser.getName())),
                () -> assertThat(saved.getAge(), is(newUser.getAge())),
                () -> assertThat(saved.getPhone(), is(newUser.getPhone())
                ));
    }
}

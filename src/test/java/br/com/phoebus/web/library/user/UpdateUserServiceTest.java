package br.com.phoebus.web.library.user;

import br.com.phoebus.web.library.user.v1.UserDtoV1;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UpdateUserServiceTest {

    @Mock
    private UserRepository userRepository;

    private UpdateUserService service;

    @BeforeEach
    public void setUp() {
        this.service = new UpdateUserServiceImpl(userRepository);
    }

    @Test
    @DisplayName("Deve alterar um user")
    void update() throws Exception {
        UserDtoV1 userDtoV1 = UserDtoV1.builder()
                .id(1l)
                .name("Teste")
                .age(30)
                .phone("88888888888888").build();

        when(userRepository.findById(1l)).thenReturn(Optional.of(userDtoV1.to()));

        service.update(userDtoV1);

        ArgumentCaptor<User> user = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(user.capture());

        User saved = user.getValue();
        assertAll("user",
                () -> assertThat(saved.getName(), is("Teste")),
                () -> assertThat(saved.getPhone(), is("88888888888888")),
                () -> assertThat(saved.getAge(), is(30)));
    }
}

package br.com.phoebus.web.library.user;

import br.com.phoebus.web.library.user.v1.UserDtoV1;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetUserServiceTest {

    @Mock
    private UserRepository userRepository;

    private GetUserService service;

    @BeforeEach
    public void setUp() {
        this.service = new GetUserServiceImpl(userRepository);
    }

    @Test
    @DisplayName("Deve retornar um user por id")
    void getUser() throws Exception {
        UserDtoV1 userDtoV1 = UserDtoV1.builder()
                .id(1l)
                .name("Teste")
                .phone("88888888888888")
                .age(30).build();

        when(userRepository.findById(1l)).thenReturn(Optional.of(userDtoV1.to()));

        UserDtoV1 user = service.get(1l);
        assertAll("user",
                () -> assertThat(userDtoV1.getId(), is(user.getId())),
                () -> assertThat(userDtoV1.getName(), is(user.getName())),
                () -> assertThat(userDtoV1.getAge(), is(user.getAge())),
                () -> assertThat(userDtoV1.getPhone(), is(user.getPhone())));

    }
}

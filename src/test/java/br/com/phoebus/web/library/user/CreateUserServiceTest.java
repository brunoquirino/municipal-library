package br.com.phoebus.web.library.user;

import br.com.phoebus.web.library.user.v1.UserDtoV1;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class CreateUserServiceTest {

    @Mock
    private CreateUserService createUserService;

    @Test
    @DisplayName("Deve incluir um user")
    void create() throws Exception {
        UserDtoV1 userDtoV1 = getUserDTO();
        UserDtoV1 userDtoV1Return = getUserDTO();
        userDtoV1Return.setId(1l);

        when(createUserService.create(userDtoV1)).thenReturn(userDtoV1Return);

        UserDtoV1 user = createUserService.create(userDtoV1);
        assertAll("user",
                () -> assertThat(user.getId(), is(userDtoV1Return.getId())),
                () -> assertThat(user.getName(), is(userDtoV1Return.getName())),
                () -> assertThat(user.getAge(), is(userDtoV1Return.getAge())),
                () -> assertThat(user.getPhone(), is(userDtoV1Return.getPhone())));
    }

    private UserDtoV1 getUserDTO() {
        UserDtoV1 user = new UserDtoV1();
        user.setName("Teste");
        user.setAge(30);
        user.setPhone("88888888888888");
        return user;
    }
}

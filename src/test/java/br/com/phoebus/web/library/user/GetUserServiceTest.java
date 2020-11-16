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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class GetUserServiceTest {

    @Mock
    private GetUserService getUserService;

    @Test
    @DisplayName("Deve retornar um user por id")
    void getUser() throws Exception {
        UserDtoV1 user = new UserDtoV1();
        user.setId(1l);
        user.setName("Teste");
        user.setAge(30);
        user.setPhone("88888888888888");

        assertNotNull(getUserService);
        when(getUserService.get(1l)).thenReturn(user);

        final UserDtoV1 userDtoV1 = getUserService.get(1l);
        assertAll("user",
                () -> assertThat(userDtoV1.getId(), is(user.getId())),
                () -> assertThat(userDtoV1.getName(), is(user.getName())),
                () -> assertThat(userDtoV1.getAge(), is(user.getAge())),
                () -> assertThat(userDtoV1.getPhone(), is(user.getPhone())));
    }
}

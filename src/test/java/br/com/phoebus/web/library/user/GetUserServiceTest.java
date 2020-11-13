package br.com.phoebus.web.library.user;

import br.com.phoebus.web.library.user.v1.UserDTO;
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
        UserDTO user = new UserDTO();
        user.setId(1l);
        user.setName("Teste");
        user.setAge(30);
        user.setPhone("88888888888888");

        assertNotNull(getUserService);
        when(getUserService.get(1l)).thenReturn(user);

        final UserDTO userDTO = getUserService.get(1l);
        assertAll("user",
                () -> assertThat(userDTO.getId(), is(user.getId())),
                () -> assertThat(userDTO.getName(), is(user.getName())),
                () -> assertThat(userDTO.getAge(), is(user.getAge())),
                () -> assertThat(userDTO.getPhone(), is(user.getPhone())));
    }
}

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
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class CreateUserServiceTest {

    @Mock
    private CreateUserService createUserService;

    @Test
    @DisplayName("Deve incluir um user")
    void create() throws Exception {
        UserDTO userDTO = getUserDTO();
        UserDTO userDTOReturn = getUserDTO();
        userDTOReturn.setId(1l);

        when(createUserService.create(userDTO)).thenReturn(userDTOReturn);

        UserDTO user = createUserService.create(userDTO);
        assertAll("user",
                () -> assertThat(user.getId(), is(userDTOReturn.getId())),
                () -> assertThat(user.getName(), is(userDTOReturn.getName())),
                () -> assertThat(user.getAge(), is(userDTOReturn.getAge())),
                () -> assertThat(user.getPhone(), is(userDTOReturn.getPhone())));
    }

    private UserDTO getUserDTO() {
        UserDTO user = new UserDTO();
        user.setName("Teste");
        user.setAge(30);
        user.setPhone("88888888888888");
        return user;
    }
}

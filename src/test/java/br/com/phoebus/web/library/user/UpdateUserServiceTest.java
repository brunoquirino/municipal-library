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
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class UpdateUserServiceTest {

    @Mock
    private UpdateUserService updateUserService;

    @Test
    @DisplayName("Deve alterar um user")
    void update() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1l);
        UserDTO user = new UserDTO();
        user.setName("Teste");
        user.setAge(30);
        user.setPhone("88888888888888");

        assertNotNull(this.updateUserService);
        doNothing().when(this.updateUserService).update(userDTO);

        this.updateUserService.update(userDTO);
    }
}

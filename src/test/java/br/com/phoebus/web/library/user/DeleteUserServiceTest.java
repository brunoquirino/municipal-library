package br.com.phoebus.web.library.user;

import br.com.phoebus.web.library.user.v1.UserDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;

@SpringBootTest
@AutoConfigureMockMvc
public class DeleteUserServiceTest {

    @Mock
    private DeleteUserService deleteUserService;

    @Test
    @DisplayName("Deve deletar um user")
    void delete() throws Exception {
        UserDTO userDTO = new UserDTO(1l);

        assertNotNull(this.deleteUserService);
        doNothing().when(this.deleteUserService).delete(userDTO);

        this.deleteUserService.delete(userDTO);
    }
}

package br.com.phoebus.web.library.user.v1;

import br.com.phoebus.web.library.user.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerV1Test {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreateUserService createUserService;

    @MockBean
    private UpdateUserService updateUserService;

    @MockBean
    private DeleteUserService deleteUserService;

    @MockBean
    private GetUserService getUserService;

    @MockBean
    private ListUserService listUserService;

    public static String readJson(String file) throws Exception {
        byte[] bytes = Files.readAllBytes(Paths.get("src/test/resources/json/" + file).toAbsolutePath());
        return new String(bytes);
    }

    @Test
    @DisplayName("Deve incluir um usuário")
    void create() throws Exception {
        UserDtoV1 userDtoV1 = getUserDTO();

        this.mockMvc.perform(post("/v1/users")
                .flashAttr("user", userDtoV1)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(readJson("user.json")))
                .andExpect(status().isOk());

        verify(createUserService).create(any(UserDtoV1.class));
    }

    @Test
    @DisplayName("Deve listar todos os usuários")
    void listAll() throws Exception {
        UserDtoV1 user = getUserDTO();
        List<UserDtoV1> users = Arrays.asList(user);

        when(listUserService.listAll()).thenReturn(users);

        mockMvc.perform(
                get("/v1/users")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .flashAttr("users", user)
        ).andExpect(status().isOk());
    }

    @Test
    @DisplayName("Deve retornar um user por ID")
    void getUser() throws Exception {
        UserDtoV1 user = getUserDTO();

        when(getUserService.get(1l)).thenReturn(user);

        mockMvc.perform(
                get("/v1/users/{id}", 1l)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .flashAttr("users", user)
        ).andExpect(status().isOk());
    }

    @Test
    @DisplayName("Deve deletar um user")
    void deleteUser() throws Exception {
        UserDtoV1 userDtoV1 = getUserDTO();

        this.mockMvc.perform(delete("/v1/users")
                .flashAttr("user", userDtoV1)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(readJson("user.json")))
                .andExpect(status().isOk());

        verify(deleteUserService).delete(any(UserDtoV1.class));
    }

    @Test
    @DisplayName("Deve alterar um user")
    void update() throws Exception {
        UserDtoV1 userDtoV1 = getUserDTO();

        this.mockMvc.perform(put("/v1/users")
                .flashAttr("user", userDtoV1)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(readJson("user.json")))
                .andExpect(status().isOk());

        verify(updateUserService).update(any(UserDtoV1.class));
    }

    private UserDtoV1 getUserDTO() {
        UserDtoV1 user = UserDtoV1.builder()
                .name("Teste")
                .age(30)
                .phone("88888888888888").build();

        return user;
    }
}

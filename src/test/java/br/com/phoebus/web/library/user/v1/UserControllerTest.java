package br.com.phoebus.web.library.user.v1;

import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UserController userController;

    @BeforeEach
    public void setUp() {
    }

    @Test
    @DisplayName("Deve incluir um usuário")
    void create() throws Exception {
        UserDTO userDTO = getUserDTO();
        UserDTO userDTOReturn = getUserDTO();
        userDTOReturn.setId(1l);

        assertNotNull(userController);
        when(userController.create(userDTO)).thenReturn(userDTOReturn);

        UserDTO user = userController.create(userDTO);
        assertAll("user",
                () -> assertThat(user.getId(), is(1l)),
                () -> assertThat(user.getName(), is("Teste")),
                () -> assertThat(user.getAge(), is(30)),
                () -> assertThat(user.getPhone(), is("88888888888888")));

        String content = new Gson().toJson(userDTO);
        this.mockMvc.perform(post("/users")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(content))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(content));
    }

    @Test
    @DisplayName("Deve listar todos os usuários")
    void listAll() throws Exception {
        assertNotNull(userController);

        when(userController.listAll()).thenReturn(new ArrayList<UserDTO>());

        final List<UserDTO> users = userController.listAll();
        assertAll("users", () -> assertThat(users.size(), is(0)));

        this.mockMvc.perform(get("/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Deve retornar um user por ID")
    void getUser() throws  Exception {
        UserDTO user = getUserDTO();
        user.setId(1l);

        assertNotNull(userController);
        when(userController.get(1l)).thenReturn(user);

        final UserDTO userDTO = userController.get(1l);
        assertAll("user",
                () -> assertThat(userDTO.getId(), is(1l)),
                () -> assertThat(userDTO.getName(), is("Teste")),
                () -> assertThat(userDTO.getAge(), is(30)));

        String content = new Gson().toJson(new UserDTO());
        this.mockMvc.perform(get("/users/{id}", 1l)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(content));
    }

    @Test
    @DisplayName("Deve deletar um user")
    void deleteUser() throws Exception {
        UserDTO userDTO = new UserDTO(1l);

        assertNotNull(this.userController);
        when(this.userController.get(anyLong())).thenReturn(userDTO);
        doNothing().when(this.userController).delete(userDTO);

        this.mockMvc.perform(delete("/users")
                .content(new Gson().toJson(userDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        this.userController.delete(userDTO);
    }

    @Test
    @DisplayName("Deve alterar um user")
    void update() throws Exception {
        UserDTO userDTO = getUserDTO();
        userDTO.setId(1l);

        assertNotNull(this.userController);
        when(this.userController.get(1l)).thenReturn(new UserDTO(1l));
        doNothing().when(this.userController).update(userDTO);

        this.userController.update(userDTO);

        this.mockMvc.perform(put("/users")
                .content(new Gson().toJson(userDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    private UserDTO getUserDTO() {
        UserDTO user = new UserDTO();
        user.setName("Teste");
        user.setAge(30);
        user.setPhone("88888888888888");
        return user;
    }
}

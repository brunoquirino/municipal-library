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
public class UserControllerV1Test {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UserControllerV1 userControllerV1;

    @BeforeEach
    public void setUp() {
    }

    @Test
    @DisplayName("Deve incluir um usuário")
    void create() throws Exception {
        UserDtoV1 userDtoV1 = getUserDTO();
        UserDtoV1 userDtoV1Return = getUserDTO();
        userDtoV1Return.setId(1l);

        assertNotNull(userControllerV1);
        when(userControllerV1.create(userDtoV1)).thenReturn(userDtoV1Return);

        UserDtoV1 user = userControllerV1.create(userDtoV1);
        assertAll("user",
                () -> assertThat(user.getId(), is(1l)),
                () -> assertThat(user.getName(), is("Teste")),
                () -> assertThat(user.getAge(), is(30)),
                () -> assertThat(user.getPhone(), is("88888888888888")));

        String content = new Gson().toJson(userDtoV1);
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
        assertNotNull(userControllerV1);

        when(userControllerV1.listAll()).thenReturn(new ArrayList<UserDtoV1>());

        final List<UserDtoV1> users = userControllerV1.listAll();
        assertAll("users", () -> assertThat(users.size(), is(0)));

        this.mockMvc.perform(get("/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Deve retornar um user por ID")
    void getUser() throws  Exception {
        UserDtoV1 user = getUserDTO();
        user.setId(1l);

        assertNotNull(userControllerV1);
        when(userControllerV1.get(1l)).thenReturn(user);

        final UserDtoV1 userDtoV1 = userControllerV1.get(1l);
        assertAll("user",
                () -> assertThat(userDtoV1.getId(), is(1l)),
                () -> assertThat(userDtoV1.getName(), is("Teste")),
                () -> assertThat(userDtoV1.getAge(), is(30)));

        String content = new Gson().toJson(new UserDtoV1());
        this.mockMvc.perform(get("/users/{id}", 1l)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(content));
    }

    @Test
    @DisplayName("Deve deletar um user")
    void deleteUser() throws Exception {
        UserDtoV1 userDtoV1 = new UserDtoV1();
        userDtoV1.setId(1l);

        assertNotNull(this.userControllerV1);
        when(this.userControllerV1.get(anyLong())).thenReturn(userDtoV1);
        doNothing().when(this.userControllerV1).delete(userDtoV1);

        this.mockMvc.perform(delete("/users")
                .content(new Gson().toJson(userDtoV1))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        this.userControllerV1.delete(userDtoV1);
    }

    @Test
    @DisplayName("Deve alterar um user")
    void update() throws Exception {
        UserDtoV1 userDtoV1 = getUserDTO();
        userDtoV1.setId(1l);

        assertNotNull(this.userControllerV1);
        UserDtoV1 value = new UserDtoV1();
        value.setId(1l);
        when(this.userControllerV1.get(1l)).thenReturn(value);
        doNothing().when(this.userControllerV1).update(userDtoV1);

        this.userControllerV1.update(userDtoV1);

        this.mockMvc.perform(put("/users")
                .content(new Gson().toJson(userDtoV1))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    private UserDtoV1 getUserDTO() {
        UserDtoV1 user = new UserDtoV1();
        user.setName("Teste");
        user.setAge(30);
        user.setPhone("88888888888888");
        return user;
    }
}

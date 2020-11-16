package br.com.phoebus.web.library.user;

import br.com.phoebus.web.library.user.v1.UserDtoV1;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class ListUserServiceTest {

    @Mock
    private ListUserService listUserService;

    @Test
    @DisplayName("Deve retornar uma lista de user")
    void list() throws Exception {
        when(listUserService.listAll()).thenReturn(new ArrayList<UserDtoV1>());

        final List<UserDtoV1> users = listUserService.listAll();
        assertAll("users", () -> assertThat(users.size(), is(0)));
    }
}

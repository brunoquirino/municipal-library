package br.com.phoebus.web.library.user;

import br.com.phoebus.web.library.user.v1.UserDtoV1;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ListUserServiceTest {

    @Mock
    private UserRepository userRepository;

    private ListUserService service;
    
    @BeforeEach
    public void setUp() {
        this.service = new ListUserServiceImpl(userRepository);
    }

    @Test
    @DisplayName("Deve retornar uma lista de user")
    void list() throws Exception {
        UserDtoV1 userDtoV1 = UserDtoV1.builder()
                .id(1l)
                .name("Teste")
                .phone("88888888888888")
                .age(30).build();

        when(userRepository.findAll()).thenReturn(Arrays.asList(userDtoV1.to()));

        List<UserDtoV1> users = service.listAll();
        UserDtoV1 userDtoV11 = users.get(0);
        assertAll("users",
                () -> assertThat(users.size(), is(1)),
                () -> assertThat(userDtoV1.getName(), is("Teste")),
                () -> assertThat(userDtoV1.getPhone(), is("88888888888888")),
                () -> assertThat(userDtoV1.getAge(), is(30)));
    }
}

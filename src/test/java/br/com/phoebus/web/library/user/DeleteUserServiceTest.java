package br.com.phoebus.web.library.user;

import br.com.phoebus.web.library.user.v1.UserDtoV1;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class DeleteUserServiceTest {

    @Mock
    private UserRepository userRepository;

    private DeleteUserService service;
    
    @BeforeEach
    public void setUp() {
        this.service = new DeleteUserServiceImpl(userRepository);
    }

    @Test
    @DisplayName("Deve deletar um user")
    void delete() throws Exception {
        UserDtoV1 userDtoV1 = new UserDtoV1();
        userDtoV1.setId(1l);

        service.delete(userDtoV1);
        verify(userRepository).delete(userDtoV1.to());
    }
}

package br.com.phoebus.web.library.lending.v1;

import br.com.phoebus.web.library.book.v1.BookDTO;
import br.com.phoebus.web.library.lending.GetLendingService;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class LendingControllerTest {

    @Autowired
    private MockMvc mock;

    @MockBean
    private LendingController lendingController;

    @Mock
    private GetLendingService getLendingServiceMock;

    @BeforeEach
    public void setUp() {
    }

    @Test
    @DisplayName("Deve incluir um emprestimo")
    void create() throws Exception {
        LendingDTO lendingDTO = getLendingDTO();
        LendingDTO lendingReturn = getLendingDTO();
        lendingReturn.setId(1l);

        assertNotNull(lendingController);
        when(lendingController.create(lendingDTO)).thenReturn(lendingReturn);
        LendingDTO lending = lendingController.create(lendingDTO);
        assertAll("lending",
                () -> assertThat(lending.getId(), is(1l)),
                () -> assertThat(lending.getUserID(), is(1l)),
                () -> assertThat(lending.getDays(), is(7)));

        String jsonContent = new Gson().toJson(getLendingDTO());
        this.mock.perform(post("/lendings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Deve Listar todos os emprestimos")
    void listAll() throws Exception {
        assertNotNull(lendingController);
        when(lendingController.listAll()).thenReturn(new ArrayList<LendingDTO>());

        final List<LendingDTO> lendings = lendingController.listAll();
        assertAll("lendings", () -> assertThat(lendings.size(), is(0)));

        String jsonContent = new Gson().toJson(lendings);
        this.mock.perform(get("/lendings")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(jsonContent));
    }

    @Test
    @DisplayName("Deve obter emprestimo por id")
    void getLending() throws Exception {
        assertNotNull(lendingController);
        LendingDTO lendingDTO = getLendingDTO();
        lendingDTO.setId(1l);

        when(lendingController.get(1l)).thenReturn(lendingDTO);

        LendingDTO lendingTO = lendingController.get(1l);
        assertAll("lending",
                () -> assertThat(lendingTO.getId(), is(1l)),
                () -> assertThat(lendingTO.getDays(), is(7)),
                () -> assertThat(lendingTO.getUserID(), is(1l)),
                () -> assertThat(lendingTO.getBooks().size(), is(1)));

        String jsonContent = new Gson().toJson(lendingDTO);
        this.mock.perform(get("/lendings/{1}", 1l)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(jsonContent));
    }

    @Test
    @DisplayName("Deve deletar um emprestimo")
    void deleteLending() throws Exception {
        LendingDTO lendingDTO = new LendingDTO(1l);

        assertNotNull(this.lendingController);
        when(this.getLendingServiceMock.get(anyLong())).thenReturn(lendingDTO);
        doNothing().when(this.lendingController).delete(lendingDTO);

        String content = new Gson().toJson(lendingDTO);
        this.mock.perform(delete("/lendings")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        this.lendingController.delete(lendingDTO);
    }

    @Test
    @DisplayName("Deve alterar um emprestimo")
    void update() throws Exception {
        LendingDTO lendingDTO = getLendingDTO();
        lendingDTO.setId(1l);

        assertNotNull(this.lendingController);
        when(this.lendingController.get(1l)).thenReturn(lendingDTO);
        doNothing().when(this.lendingController).update(lendingDTO);

        String content = new Gson().toJson(lendingDTO);
        this.mock.perform(put("/lendings")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        this.lendingController.update(lendingDTO);
    }

    private LendingDTO getLendingDTO() {
        LendingDTO lendingDTO = new LendingDTO();
        lendingDTO.setUserID(1l);
        lendingDTO.setDays(7);
        lendingDTO.setBooks(Arrays.asList(new BookDTO(1l)));

        return lendingDTO;
    }

}

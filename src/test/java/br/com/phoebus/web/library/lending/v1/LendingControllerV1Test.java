package br.com.phoebus.web.library.lending.v1;

import br.com.phoebus.web.library.book.v1.BookDtoV1;
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
public class LendingControllerV1Test {

    @Autowired
    private MockMvc mock;

    @MockBean
    private LendingControllerV1 lendingControllerV1;

    @Mock
    private GetLendingService getLendingServiceMock;

    @BeforeEach
    public void setUp() {
    }

    @Test
    @DisplayName("Deve incluir um emprestimo")
    void create() throws Exception {
        LendingDtoV1 lendingDtoV1 = getLendingDTO();
        LendingDtoV1 lendingReturn = getLendingDTO();
        lendingReturn.setId(1l);

        assertNotNull(lendingControllerV1);
        when(lendingControllerV1.create(lendingDtoV1)).thenReturn(lendingReturn);
        LendingDtoV1 lending = lendingControllerV1.create(lendingDtoV1);
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
        assertNotNull(lendingControllerV1);
        when(lendingControllerV1.listAll()).thenReturn(new ArrayList<LendingDtoV1>());

        final List<LendingDtoV1> lendings = lendingControllerV1.listAll();
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
        assertNotNull(lendingControllerV1);
        LendingDtoV1 lendingDtoV1 = getLendingDTO();
        lendingDtoV1.setId(1l);

        when(lendingControllerV1.get(1l)).thenReturn(lendingDtoV1);

        LendingDtoV1 lendingTO = lendingControllerV1.get(1l);
        assertAll("lending",
                () -> assertThat(lendingTO.getId(), is(1l)),
                () -> assertThat(lendingTO.getDays(), is(7)),
                () -> assertThat(lendingTO.getUserID(), is(1l)),
                () -> assertThat(lendingTO.getBooks().size(), is(1)));

        String jsonContent = new Gson().toJson(lendingDtoV1);
        this.mock.perform(get("/lendings/{1}", 1l)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(jsonContent));
    }

    @Test
    @DisplayName("Deve deletar um emprestimo")
    void deleteLending() throws Exception {
        LendingDtoV1 lendingDtoV1 = new LendingDtoV1();
        lendingDtoV1.setId(1l);

        assertNotNull(this.lendingControllerV1);
        when(this.getLendingServiceMock.get(anyLong())).thenReturn(lendingDtoV1);
        doNothing().when(this.lendingControllerV1).delete(lendingDtoV1);

        String content = new Gson().toJson(lendingDtoV1);
        this.mock.perform(delete("/lendings")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        this.lendingControllerV1.delete(lendingDtoV1);
    }

    @Test
    @DisplayName("Deve alterar um emprestimo")
    void update() throws Exception {
        LendingDtoV1 lendingDtoV1 = getLendingDTO();
        lendingDtoV1.setId(1l);

        assertNotNull(this.lendingControllerV1);
        when(this.lendingControllerV1.get(1l)).thenReturn(lendingDtoV1);
        doNothing().when(this.lendingControllerV1).update(lendingDtoV1);

        String content = new Gson().toJson(lendingDtoV1);
        this.mock.perform(put("/lendings")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        this.lendingControllerV1.update(lendingDtoV1);
    }

    private LendingDtoV1 getLendingDTO() {
        LendingDtoV1 lendingDtoV1 = new LendingDtoV1();
        lendingDtoV1.setUserID(1l);
        lendingDtoV1.setDays(7);
        BookDtoV1 bookDtoV1 = new BookDtoV1();
        bookDtoV1.setId(1l);
        lendingDtoV1.setBooks(Arrays.asList(bookDtoV1));

        return lendingDtoV1;
    }

}

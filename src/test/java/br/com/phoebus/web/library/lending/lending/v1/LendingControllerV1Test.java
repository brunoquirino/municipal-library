package br.com.phoebus.web.library.lending.lending.v1;

import br.com.phoebus.web.library.lending.book.BookDto;
import br.com.phoebus.web.library.lending.lending.*;
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
import java.util.UUID;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class LendingControllerV1Test {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreateLendingService createLendingService;

    @MockBean
    private UpdateLendingService updateLendingService;

    @MockBean
    private DeleteLendingService deleteLendingService;

    @MockBean
    private GetLendingService getLendingService;

    @MockBean
    private ListLendingService listLendingService;

    public static String readJson(String file) throws Exception {
        byte[] bytes = Files.readAllBytes(Paths.get("src/test/resources/json/" + file).toAbsolutePath());
        return new String(bytes);
    }

    @Test
    @DisplayName("Deve incluir um emprestimo")
    void create() throws Exception {
        LendingDtoV1 lendingDtoV1 = getLendingDTO();

        when(createLendingService.create(any())).thenReturn(lendingDtoV1);

        this.mockMvc.perform(post("/v1/lendings")
                .flashAttr("lending", lendingDtoV1)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(readJson("lending.json")))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.days", is(7)))
                .andExpect(jsonPath("$.userID", is(lendingDtoV1.getUserID())))
                .andExpect(jsonPath("$.books", hasSize(1)));

        verify(createLendingService).create(any(LendingDtoV1.class));
    }

    @Test
    @DisplayName("Deve Listar todos os emprestimos")
    void listAll() throws Exception {
        LendingDtoV1 lendingDtoV1 = getLendingDTO();
        List<LendingDtoV1> lendings = Arrays.asList(lendingDtoV1);

        when(listLendingService.listAll()).thenReturn(lendings);

        mockMvc.perform(get("/v1/lendings")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .flashAttr("lendings", lendingDtoV1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].days", is(7)))
                .andExpect(jsonPath("$[0].userID", is(lendingDtoV1.getUserID())))
                .andExpect(jsonPath("$[0].books", hasSize(1)));
    }

    @Test
    @DisplayName("Deve obter emprestimo por id")
    void getLending() throws Exception {
        LendingDtoV1 lending = getLendingDTO();

        when(getLendingService.get(1l)).thenReturn(lending);

        mockMvc.perform(get("/v1/lendings/{id}", 1l)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .flashAttr("lendings", lending))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.days", is(7)))
                .andExpect(jsonPath("$.userID", is(lending.getUserID())))
                .andExpect(jsonPath("$.books", hasSize(1)));
    }

    @Test
    @DisplayName("Deve deletar um emprestimo")
    void deleteLending() throws Exception {
        LendingDtoV1 lendingDtoV1 = getLendingDTO();

        this.mockMvc.perform(delete("/v1/lendings")
                .flashAttr("lending", lendingDtoV1)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(readJson("lending.json")))
                .andExpect(status().isOk());

        verify(deleteLendingService).delete(any(LendingDtoV1.class));
    }

    @Test
    @DisplayName("Deve alterar um emprestimo")
    void update() throws Exception {
        LendingDtoV1 lendingDtoV1 = getLendingDTO();

        this.mockMvc.perform(put("/v1/lendings")
                .flashAttr("lending", lendingDtoV1)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(readJson("lending.json")))
                .andExpect(status().isOk());

        verify(updateLendingService).update(any(LendingDtoV1.class));
    }

    private LendingDtoV1 getLendingDTO() {
        BookDto bookDtoV1 = new BookDto();
        bookDtoV1.setUuid(UUID.randomUUID().toString());

        LendingDtoV1 lendingDtoV1 = LendingDtoV1.builder()
                .id(1l)
                .days(7)
                .userID(UUID.randomUUID().toString())
                .books(Arrays.asList(bookDtoV1))
                .build();

        return lendingDtoV1;
    }

}

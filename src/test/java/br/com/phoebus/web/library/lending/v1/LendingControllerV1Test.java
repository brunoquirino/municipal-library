package br.com.phoebus.web.library.lending.v1;

import br.com.phoebus.web.library.book.v1.BookDtoV1;
import br.com.phoebus.web.library.lending.*;
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

        this.mockMvc.perform(post("/v1/lendings")
                .flashAttr("lending", lendingDtoV1)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(readJson("lending.json")))
                .andExpect(status().isOk());

        verify(createLendingService).create(any(LendingDtoV1.class));
    }

    @Test
    @DisplayName("Deve Listar todos os emprestimos")
    void listAll() throws Exception {
        LendingDtoV1 lendingDtoV1 = getLendingDTO();
        List<LendingDtoV1> lendings = Arrays.asList(lendingDtoV1);

        when(listLendingService.listAll()).thenReturn(lendings);

        mockMvc.perform(
                get("/v1/lendings")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .flashAttr("lendings", lendingDtoV1)
        ).andExpect(status().isOk());
    }

    @Test
    @DisplayName("Deve obter emprestimo por id")
    void getLending() throws Exception {
        LendingDtoV1 lending = getLendingDTO();

        when(getLendingService.get(1l)).thenReturn(lending);

        mockMvc.perform(
                get("/v1/lendings/{id}", 1l)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .flashAttr("lendings", lending)
        ).andExpect(status().isOk());
    }

    @Test
    @DisplayName("Deve deletar um emprestimo")
    void deleteLending() throws Exception {
        LendingDtoV1 lendingDtoV1 = getLendingDTO();

        this.mockMvc.perform(delete("/v1/lendings")
                .flashAttr("lending", lendingDtoV1)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
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
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(readJson("lending.json")))
                .andExpect(status().isOk());

        verify(updateLendingService).update(any(LendingDtoV1.class));
        this.updateLendingService.update(lendingDtoV1);
    }

    private LendingDtoV1 getLendingDTO() {
        BookDtoV1 bookDtoV1 = new BookDtoV1();
        bookDtoV1.setId(1l);
        LendingDtoV1 lendingDtoV1 = LendingDtoV1.builder()
                .id(1l)
                .userID(1l)
                .days(7)
                .books(Arrays.asList(bookDtoV1))
                .build();

        return lendingDtoV1;
    }

}

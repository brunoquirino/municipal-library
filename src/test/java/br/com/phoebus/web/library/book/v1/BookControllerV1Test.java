package br.com.phoebus.web.library.book.v1;

import br.com.phoebus.web.library.book.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
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

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerV1Test {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreateBookService createBookService;

    @MockBean
    private UpdateBookService updateBookService;

    @MockBean
    private DeleteBookService deleteBookService;

    @MockBean
    private ListBookService listBookService;

    @MockBean
    private GetBookService getBookService;

    public static String readJson(String file) throws Exception {
        byte[] bytes = Files.readAllBytes(Paths.get("src/test/resources/json/" + file).toAbsolutePath());
        return new String(bytes);
    }

    @Test
    @DisplayName("Deve incluir um livro")
    void create() throws Exception {
        BookDtoV1 bookDtoV1 = getBookDTO();

        this.mockMvc.perform(post("/v1/books")
                .flashAttr("book", bookDtoV1)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(readJson("book.json")))
                .andExpect(status().isOk());

        verify(createBookService).create(any(BookDtoV1.class));
    }

    @Test
    @DisplayName("Deve alterar um book")
    void update() throws Exception {
        BookDtoV1 bookDtoV1 = getBookDTO();

        this.mockMvc.perform(put("/v1/books")
                .flashAttr("book", bookDtoV1)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(readJson("book.json")))
                .andExpect(status().isOk());

        verify(updateBookService).update(any(BookDtoV1.class));
    }

    @Test
    @DisplayName("Deve deletar um book")
    void deleteBook() throws Exception {
        BookDtoV1 bookDtoV1 = getBookDTO();

        this.mockMvc.perform(delete("/v1/books")
                .flashAttr("book", bookDtoV1)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(readJson("book.json")))
                .andExpect(status().isOk());

        verify(deleteBookService).delete(any(BookDtoV1.class));
    }

    @Test
    @DisplayName("Deve Listar todos os livros")
    void listAll() throws Exception {
        BookDtoV1 book = getBookDTO();
        List<BookDtoV1> books = Arrays.asList(book);

        when(listBookService.listAll()).thenReturn(books);

        mockMvc.perform(
                get("/v1/books")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .flashAttr("books", book)
        ).andExpect(status().isOk());
    }

    @Test
    @DisplayName("Deve obter book por id")
    void getBook() throws Exception {
        BookDtoV1 book = getBookDTO();

        when(getBookService.get(1l)).thenReturn(book);

        mockMvc.perform(
                get("/v1/books/{id}", 1l)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .flashAttr("books", book)
        ).andExpect(status().isOk());
    }

    private BookDtoV1 getBookDTO() {
        BookDtoV1 bookTO = BookDtoV1.builder()
                .id(1l)
                .title("A Pedra do Reino")
                .summary("Romance d'A Pedra do Reino e o Príncipe do Sangue ...")
                .author("Ariano Suassuna")
                .isbn("ABC123456")
                .year(1950).build();

        return bookTO;
    }
}


package br.com.phoebus.web.library.book.v1;

import br.com.phoebus.web.library.book.Book;
import br.com.phoebus.web.library.book.DeleteBookService;
import br.com.phoebus.web.library.book.GetBookService;
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
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTest {

    @Autowired
    private MockMvc mock;

    @Mock
    private BookController bookController;

    @Mock
    private GetBookService getBookServiceMock;

    @BeforeEach
    public void setUp() {
    }

    @Test
    @DisplayName("Deve incluir um livro")
    void create() throws Exception {
        BookDTO bookTO = getBookDTO();
        BookDTO bookDTOReturn = getBookDTO();

        assertNotNull(bookController);
        when(bookController.create(bookTO)).thenReturn(bookDTOReturn);

        BookDTO book = bookController.create(bookTO);
        assertAll("book",
                () -> assertThat(book.getId(), is(1l)),
                () -> assertThat(book.getTitle(), is("A Pedra do Reino")),
                () -> assertThat(book.getSummary(), is("Romance d'A Pedra do Reino e o Príncipe do Sangue ...")),
                () -> assertThat(book.getAuthor(), is("Ariano Suassuna")),
                () -> assertThat(book.getIsbn(), is("ABC123456")),
                () -> assertThat(book.getYear(), is(1950)));

        String content = new Gson().toJson(bookTO);
        this.mock.perform(post("/books")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(content))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(content));
    }

    @Test
    @DisplayName("Deve Listar todos os livros")
    void listAll() throws Exception {
        assertNotNull(bookController);

        when(bookController.listAll()).thenReturn(new ArrayList<BookDTO>());

        final List<BookDTO> books = bookController.listAll();
        assertAll("books", () -> assertThat(books.size(), is(0)));

        this.mock.perform(get("/books")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(new Gson().toJson(Arrays.asList(new BookDTO()))));
    }

    @Test
    @DisplayName("Deve obter book por id")
    void getBook() throws Exception {
        assertNotNull(bookController);

        when(bookController.get(1l)).thenReturn(getBookDTO());

        BookDTO bookTO = bookController.get(1l);
        assertAll("book",
                () -> assertThat(bookTO.getId(), is(1l)),
                () -> assertThat(bookTO.getTitle(), is("A Pedra do Reino")),
                () -> assertThat(bookTO.getSummary(), is("Romance d'A Pedra do Reino e o Príncipe do Sangue ...")),
                () -> assertThat(bookTO.getAuthor(), is("Ariano Suassuna")),
                () -> assertThat(bookTO.getIsbn(), is("ABC123456")),
                () -> assertThat(bookTO.getYear(), is(1950)));

        this.mock.perform(get("/books/{1}", 1l)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(new Gson().toJson(new BookDTO())));
    }

    @Test
    @DisplayName("Deve deletar um book")
    void deleteBook() throws Exception {
        BookDTO bookDTO = new BookDTO(1l);

        assertNotNull(this.bookController);
        when(this.getBookServiceMock.get(anyLong())).thenReturn(bookDTO);
        doNothing().when(this.bookController).delete(bookDTO);

        String content = new Gson().toJson(bookDTO);
        this.mock.perform(delete("/books")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        this.bookController.delete(bookDTO);
    }

    @Test
    @DisplayName("Deve alterar um book")
    void update() throws Exception {
        BookDTO bookDTO = getBookDTO();
        bookDTO.setId(1l);

        assertNotNull(this.bookController);
        when(this.bookController.get(1l)).thenReturn(bookDTO);
        doNothing().when(this.bookController).update(bookDTO);

        String content = new Gson().toJson(bookDTO);
        this.mock.perform(put("/books")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        this.bookController.update(bookDTO);
    }

    private BookDTO getBookDTO() {
        BookDTO bookTO = new BookDTO(1l);
        bookTO.setTitle("A Pedra do Reino");
        bookTO.setSummary("Romance d'A Pedra do Reino e o Príncipe do Sangue ...");
        bookTO.setAuthor("Ariano Suassuna");
        bookTO.setIsbn("ABC123456");
        bookTO.setYear(1950);

        return bookTO;
    }
}


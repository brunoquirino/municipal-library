package br.com.phoebus.web.library.book.v1;

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
public class BookControllerV1Test {

    @Autowired
    private MockMvc mock;

    @Mock
    private BookControllerV1 bookControllerV1;

    @Mock
    private GetBookService getBookServiceMock;

    @BeforeEach
    public void setUp() {
    }

    @Test
    @DisplayName("Deve incluir um livro")
    void create() throws Exception {
        BookDtoV1 bookTO = getBookDTO();
        BookDtoV1 bookDtoV1Return = getBookDTO();

        assertNotNull(bookControllerV1);
        when(bookControllerV1.create(bookTO)).thenReturn(bookDtoV1Return);

        BookDtoV1 book = bookControllerV1.create(bookTO);
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
        assertNotNull(bookControllerV1);

        when(bookControllerV1.listAll()).thenReturn(new ArrayList<BookDtoV1>());

        final List<BookDtoV1> books = bookControllerV1.listAll();
        assertAll("books", () -> assertThat(books.size(), is(0)));

        this.mock.perform(get("/books")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(new Gson().toJson(Arrays.asList(new BookDtoV1()))));
    }

    @Test
    @DisplayName("Deve obter book por id")
    void getBook() throws Exception {
        assertNotNull(bookControllerV1);

        when(bookControllerV1.get(1l)).thenReturn(getBookDTO());

        BookDtoV1 bookTO = bookControllerV1.get(1l);
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
                .andExpect(content().json(new Gson().toJson(new BookDtoV1())));
    }

    @Test
    @DisplayName("Deve deletar um book")
    void deleteBook() throws Exception {
        BookDtoV1 bookDtoV1 = new BookDtoV1();
        bookDtoV1.setId(1l);

        assertNotNull(this.bookControllerV1);
        when(this.getBookServiceMock.get(anyLong())).thenReturn(bookDtoV1);
        doNothing().when(this.bookControllerV1).delete(bookDtoV1);

        String content = new Gson().toJson(bookDtoV1);
        this.mock.perform(delete("/books")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        this.bookControllerV1.delete(bookDtoV1);
    }

    @Test
    @DisplayName("Deve alterar um book")
    void update() throws Exception {
        BookDtoV1 bookDtoV1 = getBookDTO();
        bookDtoV1.setId(1l);

        assertNotNull(this.bookControllerV1);
        when(this.bookControllerV1.get(1l)).thenReturn(bookDtoV1);
        doNothing().when(this.bookControllerV1).update(bookDtoV1);

        String content = new Gson().toJson(bookDtoV1);
        this.mock.perform(put("/books")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        this.bookControllerV1.update(bookDtoV1);
    }

    private BookDtoV1 getBookDTO() {
        BookDtoV1 bookTO = new BookDtoV1();
        bookTO.setId(1l);
        bookTO.setTitle("A Pedra do Reino");
        bookTO.setSummary("Romance d'A Pedra do Reino e o Príncipe do Sangue ...");
        bookTO.setAuthor("Ariano Suassuna");
        bookTO.setIsbn("ABC123456");
        bookTO.setYear(1950);

        return bookTO;
    }
}


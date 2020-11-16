package br.com.phoebus.web.library.book;

import br.com.phoebus.web.library.book.v1.BookDtoV1;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class ListBookServiceTest {

    @Mock
    private ListBookService listBookService;

    @Test
    @DisplayName("Deve retornar uma lista de books")
    void list() throws Exception {
        List<BookDtoV1> lista = Arrays.asList(getBookDTO());

        when(listBookService.listAll()).thenReturn(lista);
        List<BookDtoV1> books = listBookService.listAll();
        assertAll("books", () -> assertThat(books.size(), is(1)));
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

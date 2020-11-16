package br.com.phoebus.web.library.book;

import br.com.phoebus.web.library.book.v1.BookDtoV1;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class GetBookServiceTest {

    @Mock
    private GetBookService getBookService;

    @Test
    @DisplayName("Deve retornar um book por id")
    void get() throws Exception {
        BookDtoV1 bookDtoV1 = getBookDTO();
        when(getBookService.get(1l)).thenReturn(bookDtoV1);

        BookDtoV1 book = getBookService.get(1l);
        assertAll("book",
                () -> assertThat(book.getId(), is(bookDtoV1.getId())),
                () -> assertThat(book.getTitle(), is(bookDtoV1.getTitle())),
                () -> assertThat(book.getSummary(), is(bookDtoV1.getSummary())),
                () -> assertThat(book.getAuthor(), is(bookDtoV1.getAuthor())),
                () -> assertThat(book.getIsbn(), is(bookDtoV1.getIsbn())),
                () -> assertThat(book.getYear(), is(bookDtoV1.getYear())));
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

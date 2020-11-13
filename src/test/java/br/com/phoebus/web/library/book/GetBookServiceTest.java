package br.com.phoebus.web.library.book;

import br.com.phoebus.web.library.book.v1.BookDTO;
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
        BookDTO bookDTO = getBookDTO();
        when(getBookService.get(1l)).thenReturn(bookDTO);

        BookDTO book = getBookService.get(1l);
        assertAll("book",
                () -> assertThat(book.getId(), is(bookDTO.getId())),
                () -> assertThat(book.getTitle(), is(bookDTO.getTitle())),
                () -> assertThat(book.getSummary(), is(bookDTO.getSummary())),
                () -> assertThat(book.getAuthor(), is(bookDTO.getAuthor())),
                () -> assertThat(book.getIsbn(), is(bookDTO.getIsbn())),
                () -> assertThat(book.getYear(), is(bookDTO.getYear())));
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

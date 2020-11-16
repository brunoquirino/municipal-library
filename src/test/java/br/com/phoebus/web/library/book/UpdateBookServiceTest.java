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
import static org.mockito.Mockito.doNothing;

@SpringBootTest
@AutoConfigureMockMvc
public class UpdateBookServiceTest {

    @Mock
    private UpdateBookService updateBookService;

    @Test
    @DisplayName("Deve alterar um book")
    void create() throws Exception {
        BookDtoV1 bookDtoV1 = new BookDtoV1();
        bookDtoV1.setId(1l);
        bookDtoV1.setTitle("A Pedra do Reino");
        bookDtoV1.setSummary("Romance d'A Pedra do Reino e o Príncipe do Sangue ...");
        bookDtoV1.setAuthor("Ariano Suassuna");
        bookDtoV1.setIsbn("ABC123456");
        bookDtoV1.setYear(1950);

        doNothing().when(updateBookService).update(bookDtoV1);

        updateBookService.update(bookDtoV1);
    }
}

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
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class UpdateBookServiceTest {

    @Mock
    private UpdateBookService updateBookService;

    @Test
    @DisplayName("Deve alterar um book")
    void create() throws Exception {
        BookDTO bookDTO = new BookDTO(1l);
        bookDTO.setTitle("A Pedra do Reino");
        bookDTO.setSummary("Romance d'A Pedra do Reino e o Príncipe do Sangue ...");
        bookDTO.setAuthor("Ariano Suassuna");
        bookDTO.setIsbn("ABC123456");
        bookDTO.setYear(1950);

        doNothing().when(updateBookService).update(bookDTO);

        updateBookService.update(bookDTO);
    }
}

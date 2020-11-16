package br.com.phoebus.web.library.book;

import br.com.phoebus.web.library.book.v1.BookDtoV1;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.doNothing;

@SpringBootTest
@AutoConfigureMockMvc
public class DeleteBookServiceTest {

    @Mock
    private DeleteBookService deleteBookService;

    @Test
    @DisplayName("Deve deletar um book")
    void delete() throws Exception {
        BookDtoV1 bookDtoV1 = new BookDtoV1();
        bookDtoV1.setId(1l);

        doNothing().when(deleteBookService).delete(bookDtoV1);

        deleteBookService.delete(bookDtoV1);
    }
}

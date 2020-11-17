package br.com.phoebus.web.library.book;

import br.com.phoebus.web.library.book.v1.BookDtoV1;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class DeleteBookServiceTest {

    @Mock
    private BookRepository bookRepository;

    private DeleteBookService service;

    @BeforeEach
    public void setUp() {
        this.service = new DeleteBookServiceImpl(bookRepository);
    }

    @Test
    @DisplayName("Deve deletar um book")
    void delete() throws Exception {
        BookDtoV1 bookDtoV1 = new BookDtoV1();
        bookDtoV1.setId(1l);

        service.delete(bookDtoV1);
        verify(bookRepository).delete(bookDtoV1.to());
    }
}

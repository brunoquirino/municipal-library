package br.com.phoebus.web.library.book;

import br.com.phoebus.web.library.book.v1.BookDtoV1;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UpdateBookServiceTest {

    @Mock
    private BookRepository bookRepository;

    private UpdateBookService service;

    @BeforeEach
    public void setUp() {
        this.service = new UpdateBookServiceImpl(bookRepository);
    }

    @Test
    @DisplayName("Deve alterar um book")
    void update() throws Exception {
        BookDtoV1 bookDtoV1 = BookDtoV1.builder()
                .id(1l)
                .title("A Pedra do Reino")
                .summary("Romance d'A Pedra do Reino e o Príncipe do Sangue ...")
                .author("Ariano Suassuna")
                .isbn("ABC123456")
                .year(1950).build();

        when(bookRepository.findById(bookDtoV1.getId())).thenReturn(Optional.of(new Book()));

        service.update(bookDtoV1);

        ArgumentCaptor<Book> book = ArgumentCaptor.forClass(Book.class);
        verify(bookRepository).save(book.capture());

        Book saved = book.getValue();
        assertAll("book",
                () -> assertThat(saved.getTitle(), is("A Pedra do Reino")),
                () -> assertThat(saved.getSummary(), is("Romance d'A Pedra do Reino e o Príncipe do Sangue ...")),
                () -> assertThat(saved.getAuthor(), is("Ariano Suassuna")),
                () -> assertThat(saved.getIsbn(), is("ABC123456")),
                () -> assertThat(saved.getYear(), is(1950)));
    }
}

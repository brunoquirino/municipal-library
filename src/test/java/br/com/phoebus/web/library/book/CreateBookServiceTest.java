package br.com.phoebus.web.library.book;

import br.com.phoebus.web.library.book.v1.BookDtoV1;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CreateBookServiceTest {

    @Mock
    private BookRepository bookRepository;

    private CreateBookService service;

    @BeforeEach
    public void setUp() {
        service = new CreateBookServiceImpl(bookRepository);
    }

    @Test
    @DisplayName("Deve criar um book")
    void create() throws Exception {
        BookDtoV1 newBook = BookDtoV1.builder()
                .title("A Pedra do Reino")
                .summary("Romance d'A Pedra do Reino e o Príncipe do Sangue ...")
                .author("Ariano Suassuna")
                .isbn("ABC123456")
                .year(1950)
                .build();

        service.create(newBook);

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

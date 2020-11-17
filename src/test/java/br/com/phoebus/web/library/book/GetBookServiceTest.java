package br.com.phoebus.web.library.book;

import br.com.phoebus.web.library.book.v1.BookDtoV1;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetBookServiceTest {

    @Mock
    private BookRepository bookRepository;

    private GetBookService service;

    @BeforeEach
    public void setUp() {
        this.service = new GetBookServiceImpl(bookRepository);
    }

    @Test
    @DisplayName("Deve retornar um book por id")
    void get() throws Exception {
        BookDtoV1 bookDtoV1 = BookDtoV1.builder()
                .id(1l)
                .title("A Pedra do Reino")
                .summary("Romance d'A Pedra do Reino e o Príncipe do Sangue ...")
                .author("Ariano Suassuna")
                .isbn("ABC123456")
                .year(1950).build();

        when(bookRepository.findById(1l)).thenReturn(Optional.of(bookDtoV1.to()));

        BookDtoV1 book = service.get(1l);
        assertAll("book",
                () -> assertThat(book.getTitle(), is("A Pedra do Reino")),
                () -> assertThat(book.getSummary(), is("Romance d'A Pedra do Reino e o Príncipe do Sangue ...")),
                () -> assertThat(book.getAuthor(), is("Ariano Suassuna")),
                () -> assertThat(book.getIsbn(), is("ABC123456")),
                () -> assertThat(book.getYear(), is(1950)));
    }
}

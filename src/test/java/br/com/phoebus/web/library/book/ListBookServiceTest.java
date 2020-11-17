package br.com.phoebus.web.library.book;

import br.com.phoebus.web.library.book.v1.BookDtoV1;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ListBookServiceTest {

    @Mock
    private BookRepository bookRepository;

    private ListBookService service;

    @BeforeEach
    public void setUp() {
        this.service = new ListBookServiceImpl(bookRepository);
    }

    @Test
    @DisplayName("Deve retornar uma lista de books")
    void list() throws Exception {
        BookDtoV1 bookDtoV1 = BookDtoV1.builder()
                .id(1l)
                .title("A Pedra do Reino")
                .summary("Romance d'A Pedra do Reino e o Príncipe do Sangue ...")
                .author("Ariano Suassuna")
                .isbn("ABC123456")
                .year(1950).build();

        when(bookRepository.findAll()).thenReturn(Arrays.asList(bookDtoV1.to()));

        List<BookDtoV1> books = service.listAll();
        BookDtoV1 bookDtoV11 = books.get(0);
        assertAll("books",
                () -> assertThat(books.size(), is(1)),
                () -> assertThat(bookDtoV1.getTitle(), is("A Pedra do Reino")),
                () -> assertThat(bookDtoV1.getSummary(), is("Romance d'A Pedra do Reino e o Príncipe do Sangue ...")),
                () -> assertThat(bookDtoV1.getAuthor(), is("Ariano Suassuna")),
                () -> assertThat(bookDtoV1.getIsbn(), is("ABC123456")),
                () -> assertThat(bookDtoV1.getYear(), is(1950))
        );
    }
}

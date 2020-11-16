package br.com.phoebus.web.library.book.v1;

import br.com.phoebus.web.library.book.Book;
import lombok.*;

import java.io.Serializable;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class BookDtoV1 implements Serializable {

    public static final long serialVersionUID = -2952735933715107252L;

    private Long id;

    private String title;

    private String summary;

    private String isbn;

    private String author;

    private Integer year;

    private Long lendingID;

    public static BookDtoV1 from(Book book) {
        if (book == null) {
            return null;
        }

        return BookDtoV1.builder()
                .id(book.getId())
                .title(book.getTitle())
                .summary(book.getSummary())
                .author(book.getAuthor())
                .isbn(book.getIsbn())
                .year(book.getYear())
                .lendingID(book.getLendingID())
                .build();
    }
}

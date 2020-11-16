package br.com.phoebus.web.library.lending.v1;

import br.com.phoebus.web.library.book.Book;
import br.com.phoebus.web.library.book.v1.BookDtoV1;
import br.com.phoebus.web.library.lending.Lending;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class LendingDtoV1 implements Serializable {

    public static final long serialVersionUID = -2952735933715107252L;

    private Long id;

    private Integer days;

    private LocalDate dateDelivery;

    private LocalDate dateDevolution;

    private List<BookDtoV1> books;

    private Long userID;

    public static LendingDtoV1 from(Lending lending) {
        if (lending == null) {
            return null;
        }

        List<BookDtoV1> books = new ArrayList<>();
        for (Book book : lending.getBooks()) {
            BookDtoV1 bookDtoV1 = BookDtoV1.from(book);
            books.add(bookDtoV1);
        }

        return LendingDtoV1.builder()
                .id(lending.getId())
                .days(lending.getDays())
                .dateDelivery(lending.getDateDelivery())
                .dateDevolution(lending.getDateDevolution())
                .userID(lending.getUserID())
                .books(books)
                .build();
    }
}

package br.com.phoebus.web.library.lending.lending.v1;

import br.com.phoebus.web.library.lending.book.BookDto;
import br.com.phoebus.web.library.lending.lending.Lending;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class LendingDtoV1 implements Serializable {

    public static final long serialVersionUID = -2952735933715107252L;

    private Long id;

    private UUID uuid;

    private Integer days;

    private String userID;

    private LocalDate dateDelivery;

    private LocalDate dateDevolution;

    private List<BookDto> books;

    public Lending to() {
        return Lending.builder()
                .id(id)
                .uuid(uuid)
                .dateDelivery(dateDelivery)
                .dateDevolution(dateDevolution)
                .userID(userID)
                .days(days)
                .books(books)
                .build();
    }

    public static LendingDtoV1 from(Lending lending) {
        if (lending == null) {
            return null;
        }

        return LendingDtoV1.builder()
                .id(lending.getId())
                .uuid(lending.getUuid())
                .days(lending.getDays())
                .dateDelivery(lending.getDateDelivery())
                .dateDevolution(lending.getDateDevolution())
                .userID(lending.getUserID())
                .books(lending.getBooks())
                .build();
    }
}

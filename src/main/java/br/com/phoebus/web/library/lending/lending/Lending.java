package br.com.phoebus.web.library.lending.lending;

import br.com.phoebus.web.library.lending.book.BookDto;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "lendings")
public class Lending extends AbstractPersistable<Long> {

    private static final long serialVersionUID = -2952735933715107252L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private UUID uuid;

    private Integer days;

    @Column(name = "user_id")
    private String userID;

    @Column(name = "date_delivery")
    private LocalDate dateDelivery;

    @Column(name = "date_devolution")
    private LocalDate dateDevolution;

    @Transient
    private List<BookDto> books;

}

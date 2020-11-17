package br.com.phoebus.web.library.lending;

import br.com.phoebus.web.library.book.Book;
import br.com.phoebus.web.library.user.User;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "lendings")
public class Lending extends AbstractPersistable<Long> {

    private static final long serialVersionUID = -2952735933715107252L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer days;

    @Column(name = "user_id")
    private Long userID;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    private User user;

    @Column(name = "date_delivery")
    private LocalDate dateDelivery;

    @Column(name = "date_devolution")
    private LocalDate dateDevolution;

    @OneToMany(mappedBy = "lending", fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<Book> books;

}

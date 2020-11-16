package br.com.phoebus.web.library.book;

import br.com.phoebus.web.library.lending.Lending;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "books")
public class Book extends AbstractPersistable<Long> {

    private static final long serialVersionUID = -2952735933715107252L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String summary;

    private String isbn;

    private String author;

    private Integer year;

    @Column(name = "lending_id", updatable = false, insertable = false)
    private Long lendingID;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "lending_id", referencedColumnName = "id")
    private Lending lending;

}

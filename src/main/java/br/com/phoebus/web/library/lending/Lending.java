package br.com.phoebus.web.library.lending;

import br.com.phoebus.web.library.book.Book;
import br.com.phoebus.web.library.user.User;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

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

    @OneToMany(mappedBy = "lending", fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    private List<Book> books;

    public Lending() {
        this(null);
    }

    public Lending(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDays() {
        return days;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public LocalDate getDateDelivery() {
        return dateDelivery;
    }

    public void setDateDelivery(LocalDate dateDelivery) {
        this.dateDelivery = dateDelivery;
    }

    public LocalDate getDateDevolution() {
        return dateDevolution;
    }

    public void setDateDevolution(LocalDate dateDevolution) {
        this.dateDevolution = dateDevolution;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Lending livro = (Lending) o;
        return Objects.equals(id, livro.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }

    @Override
    public String toString() {
        return "Leding{" +
                "id=" + id +
                ", dias=" + days +
                ", usuarioID=" + userID +
                ", usuario=" + user +
                ", dataEntrega=" + dateDelivery +
                ", dataDevolucao=" + dateDevolution +
                ", livros=" + books +
                '}';
    }
}

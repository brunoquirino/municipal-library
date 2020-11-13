package br.com.phoebus.web.library.lending.v1;

import br.com.phoebus.web.library.book.Book;
import br.com.phoebus.web.library.book.v1.BookDTO;
import br.com.phoebus.web.library.lending.Lending;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LendingDTO implements Serializable {

    public static final long serialVersionUID = -2952735933715107252L;

    private Long id;

    private Integer days;

    private LocalDate dateDelivery;

    private LocalDate dateDevolution;

    private List<BookDTO> books;

    private Long userID;

    public LendingDTO() {
        this.books = new ArrayList<BookDTO>();
    }

    public LendingDTO(Long id) {
        this();
        this.id = id;
    }

    public LendingDTO(Lending lending) {
        this();
        this.id = lending.getId();
        this.days = lending.getDays();
        this.dateDelivery = lending.getDateDelivery();
        this.dateDevolution = lending.getDateDevolution();
        this.userID = lending.getUserID();
        this.books = new ArrayList<BookDTO>();
        for (Book book : lending.getBooks()) {
            BookDTO bookDTO = new BookDTO(book);
            this.books.add(bookDTO);
        }
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

    public List<BookDTO> getBooks() {
        return books;
    }

    public void setBooks(List<BookDTO> books) {
        this.books = books;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        LendingDTO book = (LendingDTO) o;
        return Objects.equals(id, book.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }
}

package br.com.phoebus.web.library.book.v1;

import br.com.phoebus.web.library.book.Book;

import java.io.Serializable;
import java.util.Objects;

public class BookDTO implements Serializable {

    public static final long serialVersionUID = -2952735933715107252L;

    private Long id;

    private String title;

    private String summary;

    private String isbn;

    private String author;

    private Integer year;

    private Long lendingID;

    public BookDTO() {
    }

    public BookDTO(Long id) {
        this.id = id;
    }

    public BookDTO(Book book) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.summary = book.getSummary();
        this.author = book.getAuthor();
        this.isbn = book.getIsbn();
        this.year = book.getYear();
        this.lendingID = book.getLendingID();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Long getLendingID() {
        return lendingID;
    }

    public void setLendingID(Long lendingID) {
        this.lendingID = lendingID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        BookDTO livro = (BookDTO) o;
        return Objects.equals(id, livro.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }
}

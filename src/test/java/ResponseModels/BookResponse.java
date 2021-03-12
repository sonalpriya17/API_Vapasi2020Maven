package ResponseModels;


import com.fasterxml.jackson.annotation.JsonProperty;

public class BookResponse {
  @JsonProperty("book_name")
    private String bookName;
    private String isbn;
    private String aisle;
    private String author;

    public String getBookName() {
        return bookName;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getAisle() {
        return aisle;
    }

    public String getAuthor() {
        return author;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setAisle(String aisle) {
        this.aisle = aisle;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}

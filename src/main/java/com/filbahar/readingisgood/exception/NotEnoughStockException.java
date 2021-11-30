package com.filbahar.readingisgood.exception;

/**
 * @author filbahar
 * @created 29.11.2021
 */
public class NotEnoughStockException extends RuntimeException {

    private String bookId;
    private String bookName;

    public NotEnoughStockException( String bookId, String bookName) {
        super("There is not enough stock for bookId: " + bookId + " bookName: " + bookName);
        this.bookId = bookId;
        this.bookName = bookName;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
}

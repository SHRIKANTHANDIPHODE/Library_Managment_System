package com.webcook.libraryManagement;

public class Book {
    private String bookId;
    private String title;
    private String author;
    private int copies;
    private int availableCopies;

    public Book(String bookId, String title, String author, int copies) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.copies = copies;
        this.availableCopies = copies;
    }

    public String getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void borrowBook() {
        if (availableCopies > 0) {
            availableCopies--;
        } else {
            System.out.println("No  more books are  left to borrow.Frist add some books");
        }
    }

    public void returnBook() {
        if (availableCopies < copies) {
            availableCopies++;
        }
    }

    @Override
    public String toString() {
        return title + " by " + author + " (" + availableCopies + "/" + copies + " available)";
    }
}

package com.webcook.libraryManagement;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> books;
    private List<Member> members;
    private List<Transaction> transactions;

    public Library() {
        books = new ArrayList<>();
        members = new ArrayList<>();
        transactions = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void registerMember(Member member) {
        members.add(member);
    }

    public void issueBook(String bookId, String memberId, LocalDate issueDate) {
        Book book = findBookById(bookId);
        Member member = findMemberById(memberId);
        if (book != null && member != null && book.getAvailableCopies() > 0) {
            Transaction transaction = new Transaction(generateTransactionId(), book, member, issueDate);
            transactions.add(transaction);
            book.borrowBook();
            System.out.println("Book issued successfully.");
        } else {
            System.out.println("Cannot issue book. Either book or member not found, or no available copies.");
        }
    }

    public void returnBook(String transactionId, LocalDate returnDate) {
        Transaction transaction = findTransactionById(transactionId);
        if (transaction != null && !transaction.isReturned()) {
            transaction.returnBook(returnDate);
            System.out.println("Book returned successfully. Late fee: " + transaction.calculateLateFee());
        } else {
            System.out.println("Transaction not found or book already returned.");
        }
    }

    public List<Book> getAvailableBooks() {
        List<Book> availableBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.getAvailableCopies() > 0) {
                availableBooks.add(book);
            }
        }
        return availableBooks;
    }

    private Book findBookById(String bookId) {
        for (Book book : books) {
            if (book.getBookId().equals(bookId)) {
                return book;
            }
        }
        return null;
    }

    private Member findMemberById(String memberId) {
        for (Member member : members) {
            if (member.getMemberId().equals(memberId)) {
                return member;
            }
        }
        return null;
    }

    private Transaction findTransactionById(String transactionId) {
        for (Transaction transaction : transactions) {
            if (transaction.getTransactionId().equals(transactionId)) {
                return transaction;
            }
        }
        return null;
    }

    private String generateTransactionId() {
        return "T" + (transactions.size() + 1);
    }

    public static void main(String[] args) {
        Library library = new Library();

        // Add some books
        library.addBook(new Book("BOOK1", "Wings Of Fire", "APJ Abdul Kalam", 10));
        library.addBook(new Book("BOOK2", "12th Fail", "Anurag Pathak", 7));

        // Register some members
        library.registerMember(new Member("ME2024", "shrikant"));
        library.registerMember(new Member("ME2021", "vikas"));

        // Issue books
        library.issueBook("BOOK1", "ME2024", LocalDate.now());
        library.issueBook("BOOK2", "ME2021", LocalDate.now());

        // Return a book
        library.returnBook("T1", LocalDate.now().plusDays(16));

        // View available books
        System.out.println("Available books:");
        for (Book book : library.getAvailableBooks()) {
            System.out.println(book);
        }
    }
}

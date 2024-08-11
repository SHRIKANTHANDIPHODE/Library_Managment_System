package com.webcook.libraryManagement;

import java.time.LocalDate;

public class Transaction {
    private String transactionId;
    private Book book;
    private Member member;
    private LocalDate issueDate;
    private LocalDate returnDate;
    private LocalDate dueDate;
    private boolean isReturned;
    private static final int NO_OF_DAYS = 14;
    private static final double LATE_CHARGE = 1.0;

    public Transaction(String transactionId, Book book, Member member, LocalDate issueDate) {
        this.transactionId = transactionId;
        this.book = book;
        this.member = member;
        this.issueDate = issueDate;
        this.dueDate = issueDate.plusDays(NO_OF_DAYS);
        this.isReturned = false;
    }

    public void returnBook(LocalDate returnDate) {
        this.returnDate = returnDate;
        this.isReturned = true;
        book.returnBook();
    }

    public double calculateLateFee() {
        if (returnDate != null && returnDate.isAfter(dueDate)) {
            return LATE_CHARGE * (returnDate.toEpochDay() - dueDate.toEpochDay());
        }
        return 0.0;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId='" + transactionId + '\'' +
                ", book=" + book +
                ", member=" + member +
                ", issueDate=" + issueDate +
                ", returnDate=" + returnDate +
                ", dueDate=" + dueDate +
                ", isReturned=" + isReturned +
                ", lateFee=" + calculateLateFee() +
                '}';
    }

	public Object getTransactionId() {
		// TODO Auto-generated method stub
		return transactionId;
	}

	public boolean isReturned() {
		// TODO Auto-generated method stub
		return false;
	}
}

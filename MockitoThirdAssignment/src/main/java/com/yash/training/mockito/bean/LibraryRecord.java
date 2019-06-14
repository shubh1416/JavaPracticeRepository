package com.yash.training.mockito.bean;

import java.time.LocalDate;
import java.util.Date;

public class LibraryRecord {
	
	 private Long id;

	  private Book book;

	  private LocalDate borrowingDate;

	  private LocalDate returningDate;

	  public Long getId() {
	    return id;
	  }

	  public void setId(Long id) {
	    this.id = id;
	  }

	  public Book getBook() {
	    return book;
	  }

	  public void setBook(Book book) {
	    this.book = book;
	  }

	public LocalDate getBorrowingDate() {
		return borrowingDate;
	}

	public void setBorrowingDate(LocalDate borrowingDate) {
		this.borrowingDate = borrowingDate;
	}

	public LocalDate getReturningDate() {
		return returningDate;
	}

	public void setReturningDate(LocalDate returningDate) {
		this.returningDate = returningDate;
	}

	

}

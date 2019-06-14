package com.yash.training.mockito.bean;

import com.yash.training.mockito.bean.Book;
import com.yash.training.mockito.bean.LibraryRecord;

public interface LendingManager {
	
	public LibraryRecord borrowBook(Book book);

	public LibraryRecord returnBook(Book book);

}

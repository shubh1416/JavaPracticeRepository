package com.yash.training.mockito;

import java.util.List;

import com.yash.training.mockito.bean.Book;
import com.yash.training.mockito.bean.LibraryRecord;

public interface LibraryRecordDAO {
	
	 public List<LibraryRecord> findByBook(Book book);

	 public boolean save(LibraryRecord record);

}

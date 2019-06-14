package com.yash.training.mockito.libraryimpl;

import java.time.LocalDate;
import java.util.List;

import com.yash.training.mockito.LibraryRecordDAO;
//import javax.inject.Inject;
import com.yash.training.mockito.bean.Book;
import com.yash.training.mockito.bean.LibraryRecord;
import com.yash.training.mockito.library.LendingManager;

public class LendingManagerImpl implements LendingManager {

	private LibraryRecordDAO libraryRecordDAO;

	public void setLibraryRecordDAO(LibraryRecordDAO dao) {
		libraryRecordDAO = dao;
	}

	public LibraryRecord borrowBook(Book book) {
		List<LibraryRecord> records = checkRecordIntegrity(libraryRecordDAO.findByBook(book));
		for (LibraryRecord record : records) {
			if (record.getReturningDate() == null)
				throw new IllegalStateException("This book is not returned yet.");
		}

		LibraryRecord record = new LibraryRecord();
		record.setBook(book);
		record.setBorrowingDate(LocalDate.now());
		if (!libraryRecordDAO.save(record))
			throw new IllegalStateException("New library record can't be saved.");

		return record;
	}

	public LibraryRecord returnBook(Book book) {
		List<LibraryRecord> records = checkRecordIntegrity(libraryRecordDAO.findByBook(book));
		for (LibraryRecord record : records) {
			if (record.getReturningDate() == null) {
				record.setReturningDate(LocalDate.now());
				if (!libraryRecordDAO.save(record))
					throw new IllegalStateException("Library record can't be updated.");

				return record;
			}
		}
		throw new IllegalStateException("This book is not borrowed.");
	}

	private List<LibraryRecord> checkRecordIntegrity(List<LibraryRecord> records) {
		int emptyReturningDates = 0;
		
		for (LibraryRecord record : records) {
			if (record.getBorrowingDate() == null)
				throw new IllegalStateException("Empty borrowing date is found.");

			if (record.getReturningDate() == null)
				emptyReturningDates++;
		}

		if (emptyReturningDates > 1) 
			throw new IllegalStateException("Multiple returning dates are empty.");
		
		return records;
	}

}

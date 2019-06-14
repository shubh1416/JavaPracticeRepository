package com.yash.training.mockito.libraryimpl;

import static org.junit.Assert.assertEquals;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.runners.MockitoJUnitRunner;
import com.yash.training.mockito.LibraryRecordDAO;
import com.yash.training.mockito.bean.Book;
import com.yash.training.mockito.bean.LibraryRecord;

@RunWith(value = MockitoJUnitRunner.class)
public class LendingManagerImplTest {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@InjectMocks
	private LendingManagerImpl lendingManagerImpl;

	@Mock
	private LibraryRecordDAO libraryRecordDAO;

	@Test
	public void shouldInsertNewLibraryRecordWhenBookIsNotPresentInDB() {
		
		Book book=new Book();
		LibraryRecord expected = new LibraryRecord();
		expected.setBook(book);
		expected.setBorrowingDate(LocalDate.now());

		List<LibraryRecord> libraryRecordsList=new ArrayList<>();
		
		when(libraryRecordDAO.findByBook(book)).thenReturn(libraryRecordsList );
		when(libraryRecordDAO.save(any(LibraryRecord.class))).thenReturn(true);

		LibraryRecord actual=lendingManagerImpl.borrowBook(book);
		
		assertEquals(expected.getBook(), actual.getBook());
		assertEquals(expected.getBorrowingDate(), actual.getBorrowingDate());
	
	}
	@Test
	public void shouldThrowIllegalStateExceptionWhenBorrowingDateIsEmpty() {
		
		Book book = new Book();
		LibraryRecord libraryRecord = new LibraryRecord();
		libraryRecord.setBook(book);

		List<LibraryRecord> libraryRecordsList = new ArrayList<LibraryRecord>();
		libraryRecordsList.add(libraryRecord);

		when(libraryRecordDAO.findByBook(book)).thenReturn(libraryRecordsList);

		expectedException.expect(IllegalStateException.class);
		expectedException.expectMessage("Empty borrowing date is found.");

		lendingManagerImpl.returnBook(book);

	}
	
	@Test
	public void shouldThrowIllegalStateExceptionWhenMultipleReturningDatesAreEmpty() {
		
		Book book = new Book();
		LibraryRecord libraryRecord = new LibraryRecord();
		libraryRecord.setBook(book);
		libraryRecord.setBorrowingDate(LocalDate.of(2019, 03, 10));

		List<LibraryRecord> libraryRecordsList = new ArrayList<LibraryRecord>();
		libraryRecordsList.add(libraryRecord);
		libraryRecordsList.add(libraryRecord);

		when(libraryRecordDAO.findByBook(book)).thenReturn(libraryRecordsList);

		expectedException.expect(IllegalStateException.class);
		expectedException.expectMessage("Multiple returning dates are empty.");

		lendingManagerImpl.returnBook(book);
	}
	
	@Test
	public void shouldThrowIllegalStateExceptionWhenBookReturningDateIsEmpty() {
		
		Book book = new Book();
		LibraryRecord libraryRecord = new LibraryRecord();
		libraryRecord.setBook(book);
		libraryRecord.setBorrowingDate(LocalDate.of(2019, 03, 10));

		List<LibraryRecord> libraryRecordsList = new ArrayList<LibraryRecord>();
		libraryRecordsList.add(libraryRecord);

		when(libraryRecordDAO.findByBook(book)).thenReturn(libraryRecordsList);

		expectedException.expect(IllegalStateException.class);
		expectedException.expectMessage("This book is not returned yet.");

		lendingManagerImpl.borrowBook(book);
	}

	@Test
	public void shouldReturnLibraryRecordWhenValidBookIsPassed() {
		
		Book book = new Book();
		book.setId(1l);
		book.setName("Mathematics");

		LibraryRecord libraryRecord = new LibraryRecord();
		libraryRecord.setBook(book);
		libraryRecord.setBorrowingDate(LocalDate.of(2019, 03, 10));
		libraryRecord.setReturningDate(LocalDate.of(2019, 04, 10));

		List<LibraryRecord> libraryRecordsList = new ArrayList<LibraryRecord>();
		libraryRecordsList.add(libraryRecord);
		
		LibraryRecord expected = new LibraryRecord();
		expected.setBook(book);
		expected.setBorrowingDate(LocalDate.now());

		when(libraryRecordDAO.findByBook(book)).thenReturn(libraryRecordsList);
		when(libraryRecordDAO.save(any(LibraryRecord.class))).thenReturn(true);

		LibraryRecord actual = lendingManagerImpl.borrowBook(book);

		assertEquals(expected.getBook(), actual.getBook());
		assertEquals(expected.getBorrowingDate(), actual.getBorrowingDate());
	}
	
	@Test
	public void shouldThrowIllegalStateExceptionWhenSaveToDatabaseIsFailed() {
		
		Book book = new Book();
		LibraryRecord libraryRecord = new LibraryRecord();
		libraryRecord.setBook(book);
		libraryRecord.setBorrowingDate(LocalDate.of(2019, 03, 10));
		libraryRecord.setReturningDate(LocalDate.of(2019, 04, 10));

		List<LibraryRecord> libraryRecordsList = new ArrayList<LibraryRecord>();
		libraryRecordsList.add(libraryRecord);

		when(libraryRecordDAO.findByBook(book)).thenReturn(libraryRecordsList);
		when(libraryRecordDAO.save(any(LibraryRecord.class))).thenReturn(false);

		expectedException.expect(IllegalStateException.class);
		expectedException.expectMessage("New library record can't be saved.");

		lendingManagerImpl.borrowBook(book);

	}
	
	
	@Test
	public void shouldThrowIllegalStateExceptionWhenNoRecordFoundForBook() {
		
		Book book = new Book();
		List<LibraryRecord> libraryRecordsList = new ArrayList<LibraryRecord>();

		when(libraryRecordDAO.findByBook(book)).thenReturn(libraryRecordsList);

		expectedException.expect(IllegalStateException.class);
		expectedException.expectMessage("This book is not borrowed.");

		lendingManagerImpl.returnBook(book);
	}
	
	@Test
	public void shouldReturnReturningDateWhenValidBookIsPresent() {
		
		Book book = new Book();
		LibraryRecord libraryRecord = new LibraryRecord();
		libraryRecord.setBook(book);
		libraryRecord.setBorrowingDate(LocalDate.of(2019, 03, 10));

		List<LibraryRecord> libraryRecordsList = new ArrayList<LibraryRecord>();
		libraryRecordsList.add(libraryRecord);

		when(libraryRecordDAO.findByBook(book)).thenReturn(libraryRecordsList);
		when(libraryRecordDAO.save(any(LibraryRecord.class))).thenReturn(true);

		LibraryRecord libraryRecordofParticularBook = lendingManagerImpl.returnBook(book);

		LibraryRecord record = new LibraryRecord();
		record.setReturningDate(LocalDate.now());

		assertEquals(record.getReturningDate(), libraryRecordofParticularBook.getReturningDate());
	}
	
	@Test
	public void shouldThrowIllegalStateExceptionWhenLibraryRecordIsNotAbleToSaveInDB() {
		
		Book book = new Book();
		LibraryRecord libraryRecord = new LibraryRecord();
		libraryRecord.setBook(book);
		libraryRecord.setBorrowingDate(LocalDate.of(2019, 03, 10));

		List<LibraryRecord> libraryRecordsList = new ArrayList<LibraryRecord>();
		libraryRecordsList.add(libraryRecord);

		when(libraryRecordDAO.findByBook(book)).thenReturn(libraryRecordsList);
		when(libraryRecordDAO.save(any(LibraryRecord.class))).thenReturn(false);

		expectedException.expect(IllegalStateException.class);
		expectedException.expectMessage("Library record can't be updated.");

		lendingManagerImpl.returnBook(book);

	}

	

	

	
}

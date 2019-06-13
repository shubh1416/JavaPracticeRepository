import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.verify;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.apache.log4j.Appender;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggingEvent;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;
import com.yash.VendingMachineImpl.PerformOperation;

@RunWith(PowerMockRunner.class)
public class TeaCoffeeVendingMachineApplicationTest {

		@InjectMocks
		TeaCoffeeVendingMachineApplication teaCoffeeVendingMachineApplication;
		
		@Mock
		PerformOperation performOperation;
		
		@Mock
		  private Appender appenderMock;
		    
		@Before
		    public void setupAppender() {
		        appenderMock = Mockito.mock(Appender.class);
		        Logger.getRootLogger().addAppender(appenderMock);
		    }
		  
		 @After
		    public void removeAppender() {
		        Logger.getRootLogger().removeAppender(appenderMock);
		    }
	 
	@Test
	public void testTeaCoffeeVendingMachineApplicationMainMethod() {
		String input="6";
		InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	    
	    Mockito.when(performOperation.performVendingMachinOperations(Integer.parseInt(input))).thenReturn(2);  
	    TeaCoffeeVendingMachineApplication.main(new String [] {});
		verify(appenderMock,Mockito.times(6)).doAppend((LoggingEvent) anyObject());

	    
	}
}

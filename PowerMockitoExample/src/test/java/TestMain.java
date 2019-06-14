import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
public class TestMain {

	@InjectMocks
	PowerMockitoExample powerMockitoExample;
	
	@Mock
	SecondaryClass secondaryClass;

	@Mock
	Scanner sc;
 
@Test
public void testMainMethod() {
	String input="6";
	InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    
    Mockito.when(secondaryClass.addNumber(Integer.parseInt(input))).thenReturn(2);  
	PowerMockitoExample.main(new String [] {});
}
}
